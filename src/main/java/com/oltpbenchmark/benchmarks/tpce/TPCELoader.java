package com.oltpbenchmark.benchmarks.tpce;

import com.oltpbenchmark.api.Loader;
import com.oltpbenchmark.api.LoaderThread;
import com.oltpbenchmark.benchmarks.tpce.tablegenerator.AccountPermsGenerator;
import com.oltpbenchmark.benchmarks.tpce.tablegenerator.CompanyCompetitorsGenerator;
import com.oltpbenchmark.benchmarks.tpce.tablegenerator.CustomerGenerator;
import com.oltpbenchmark.benchmarks.tpce.utils.GeneratorUtils;
import com.oltpbenchmark.catalog.Table;
import com.oltpbenchmark.util.SQLUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class TPCELoader extends Loader<TPCEBenchmark> {
    private static final Logger LOG = Logger.getLogger(TPCELoader.class);
    protected final TPCEGenerator generator;
    private final TPCEConfiguration tpceConfiguration;

    /**
     * load tables
     *
     * @return threads
     * @throws SQLException
     */
    @Override
    public List<LoaderThread> createLoaderThreads() throws SQLException {
        List<LoaderThread> threads = new ArrayList<>();

        //load fixed tables
        try {
            for (String fixTableName : TPCEConstants.FIXED_TABLES) {
                LoaderThread thread = new LoaderThread(this.benchmark) {
                    @Override
                    public void load(Connection conn) throws SQLException {
                        synchronized (this) {
                            loadTable(conn, 1000, fixTableName);
                        }
                    }
                };
                threads.add(thread);
            }
        } catch (Exception e) {
            LOG.error("Failed to generate and load fixed-sized tables", e);
            System.exit(1);
        }

        //load scaling tables
        try {
            for (long start_idx = 0, cnt = this.generator.getTotalCustomers(); start_idx < cnt; start_idx += TPCEConstants.DEFAULT_LOAD_UNIT) {
                long finalStart_idx = start_idx;

                LoaderThread thread = new LoaderThread(this.benchmark) {
                    @Override
                    public void load(Connection conn) throws SQLException {
                        generator.changeSessionParams(TPCEConstants.DEFAULT_LOAD_UNIT, finalStart_idx + 1);
                        for (String scalingTableName : TPCEConstants.SCALING_TABLES) {
                            loadTable(conn, 100, scalingTableName);
                        }
                    }
                };
                threads.add(thread);
            }
        } catch (Exception e) {
            LOG.error("Failed to generate and load fixed-sized tables", e);
            System.exit(1);
        }

        //load growing tables
        try {
            for (long start_idx = 0, cnt = this.generator.getTotalCustomers(); start_idx < cnt; start_idx += TPCEConstants.DEFAULT_LOAD_UNIT) {
                long finalStart_idx = start_idx;
                LoaderThread thread = new LoaderThread(this.benchmark) {
                    @Override
                    public void load(Connection conn) throws SQLException {
                        generator.changeSessionParams(TPCEConstants.DEFAULT_LOAD_UNIT, finalStart_idx + 1);
                        loadGrowingTables(conn, 100);
                    }

                };
                threads.add(thread);
            }

        } catch (Exception e) {
            LOG.error("Failed to generate and load fixed-sized tables", e);
            System.exit(1);
        }
        return threads;
    }

    public TPCELoader(TPCEBenchmark tpceBenchmark) {
        super(tpceBenchmark);

        this.tpceConfiguration = new TPCEConfiguration(workConf);
        // Cannot work without flat TPC-E files
        if (tpceConfiguration.getLoadFiles() == null) {
            LOG.error("Unable to start benchmark. Missing 'TPCE_LOADER_FILES' parameter");
            System.exit(1);
        }
        File flatFilesPath = new File(tpceConfiguration.getLoadFiles() + File.separator);

        long total_customers = TPCEConstants.DEFAULT_NUM_CUSTOMERS;
        if (tpceConfiguration.getTotalCustomers() != null) {
            total_customers = Long.parseLong(tpceConfiguration.getTotalCustomers());
        }

        int scale_factor = TPCEConstants.DEFAULT_SCALE_FACTOR;
        if (tpceConfiguration.getScaleFactor() != null) {
            scale_factor = Integer.parseInt(tpceConfiguration.getScaleFactor());
        }

        int initial_days = TPCEConstants.DEFAULT_INITIAL_DAYS;
        if (tpceConfiguration.getInitialDays() != null) {
            initial_days = Integer.parseInt(tpceConfiguration.getInitialDays());
        }

        // validating parameters
        if (total_customers <= 0 || total_customers % TPCEConstants.DEFAULT_LOAD_UNIT != 0) {
            throw new IllegalArgumentException("The total number of customers must be a positive integer multiple of the load unit size");
        }

        /*
         * Completed trades in 8 hours must be a non-zero integral multiple of 100
         * so that exactly 1% extra trade ids can be assigned to simulate aborts.
         */
        if ((8 * 3600 * TPCEConstants.DEFAULT_LOAD_UNIT / scale_factor) % 100 != 0) { // 8 hours per work day
            throw new IllegalArgumentException("Wrong scale factor: 8 * 3600 * Load Unit Size (" +
                TPCEConstants.DEFAULT_LOAD_UNIT + ") / Scale Factor(" + scale_factor + ") must be " +
                "integral multiple of 100 to simulate trades");
        }

        if (initial_days <= 0) {
            throw new IllegalArgumentException("The number of initial trade days must be a positive integer");
        }

        LOG.info("TPC-E parameters are: flat_in = '" + flatFilesPath + "', total_customers = " + total_customers +
            ", scale_factor = " + scale_factor + ", initial_days = " + initial_days);


        this.generator = new TPCEGenerator(flatFilesPath, total_customers, scale_factor, initial_days);
        generator.parseInputFiles();
    }

    private PreparedStatement getInsertStatement(Connection conn, String tableName) throws SQLException {
        Table catalog_tbl = benchmark.getCatalog().getTable(tableName);
        String sql = SQLUtil.getInsertSQL(catalog_tbl, this.getDatabaseType());
        return conn.prepareStatement(sql);
    }



    /**
     * @param
     */
    private void loadTable(Connection conn, int batch_size, String tableName) {
        LOG.debug("Loading records for table " + tableName + " in batches of " + batch_size);
        try (PreparedStatement preparedStatement = getInsertStatement(conn, tableName)) {
            int index = 0;
            boolean debug = true;

            Iterator<Object[]> table_gen = this.generator.getTableGen(tableName);

            while (table_gen.hasNext()) {
                Object tuple[] = table_gen.next();

                if (debug) {
                    StringBuilder sb = new StringBuilder();
                    for (Object o : tuple) {
                        sb.append(o.toString());
                        sb.append('|');
                    }
                    LOG.trace("Table[" + tableName + "], Tuple[" + index + "]: " + sb);
                }

                //process the statement
                GeneratorUtils.addRow(preparedStatement, tuple, tableName);
                index++;
                if (index > 0 && index % batch_size == 0) {
                    LOG.debug("Storing batch of " + batch_size + " tuples for " + tableName + " [total=" + index + "]");
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                    index = 0;
                }
            }

            if (index > 0) {

                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }

        } catch (SQLException se) {
            if (TPCEConstants.FIXED_TABLES.contains(tableName)) {
                LOG.error("Failed to insert data in fixed-sized tables:" + tableName, se);
            } else {
                LOG.error("Failed to insert data in scale-sized tables:" + tableName, se);
            }

        }

    }


    private void loadGrowingTables(Connection conn, int batchSize) {
        LOG.debug("Loading records for growing tables in batches of " + batchSize);
        boolean debug = true;
        Map<String, Integer> growingTables = new HashMap<String, Integer>();
        Map<String, PreparedStatement> preparedStatementMap = new HashMap<String, PreparedStatement>();

        try {
            for (String tableName : TPCEConstants.GROWING_TABLES) {
                growingTables.put(tableName, 0);
                preparedStatementMap.put(tableName, getInsertStatement(conn, tableName));
            }

            TradeGenerator tableGen = new TradeGenerator(this.generator, this.benchmark);
            while (tableGen.hasNext()) {
                Object tuple[] = tableGen.next(); // tuple
                String tableName = tableGen.getCurrentTable(); // table name the tuple is for
                if (debug) {
                    StringBuilder sb = new StringBuilder();
                    for (Object o : tuple) {
                        sb.append(o.toString());
                        sb.append('|');
                    }
                }
                PreparedStatement preparedStatement = preparedStatementMap.get(tableName);
                if (preparedStatement == null) {
                    //todo add logs
                }

                growingTables.put(tableName, growingTables.getOrDefault(tableName, 0) + 1);
                GeneratorUtils.addRow(preparedStatement, tuple, tableName);


                if (growingTables.get(tableName) % batchSize == 0) {
                    LOG.debug("Storing batch of " + batchSize + " tuples for " + tableName + " [total=" + growingTables.get(tableName) + "]");
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }

            // loading remaining (out-of-batch) tuples
            for (Map.Entry<String, Integer> table : growingTables.entrySet()) {
                String tableName = table.getKey();
                int remainBatchs = table.getValue();


                if (remainBatchs > 0) {
                    PreparedStatement preparedStatement = preparedStatementMap.get(tableName);
                    preparedStatement.executeBatch();
                    preparedStatement.executeBatch();
                }
                LOG.debug("Finished loading " + remainBatchs + " final tuples for " + tableName);
            }
        } catch (Exception ex) {
            LOG.error("Failed to load growing tables", ex);
            System.exit(1);
        }


    }


}
