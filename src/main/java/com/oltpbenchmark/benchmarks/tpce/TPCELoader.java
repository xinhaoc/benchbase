package com.oltpbenchmark.benchmarks.tpce;

import com.oltpbenchmark.api.Loader;
import com.oltpbenchmark.api.LoaderThread;
import com.oltpbenchmark.benchmarks.tpcc.TPCCConstants;
import com.oltpbenchmark.catalog.Table;
import com.oltpbenchmark.util.SQLUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class TPCELoader extends Loader<TPCEBenchmark> {
    private static final Logger LOG = Logger.getLogger(TPCELoader.class);
    protected final TPCEGenerator generator;

    /**
     * load tables
     * @return threads
     * @throws SQLException
     */
    @Override
    public List<LoaderThread> createLoaderThreads() throws SQLException {
        List<LoaderThread> threads = new ArrayList<>();

        //load fixed tables
        try{
            for(String fixTableName : TPCEConstants.FIXED_TABLES){
                LoaderThread thread = new LoaderThread(this.benchmark) {
                    @Override
                    public void load(Connection conn) throws SQLException {
                        loadTable();
                    }
                };
                threads.add(thread);

            }
        }catch (Exception e){
            LOG.error("Failed to generate and load fixed-sized tables", e);
            System.exit(1);
        }

        //load growing tables
        try{
            for(String fixTableName : TPCEConstants.GROWING_TABLES){
                LoaderThread thread = new LoaderThread(this.benchmark) {
                    @Override
                    public void load(Connection conn) throws SQLException {
                        loadTable();
                    }
                };
                threads.add(thread);

            }
        }catch (Exception e){
            LOG.error("Failed to generate and load fixed-sized tables", e);
            System.exit(1);
        }

        //load scaling tables
        try{
            for(String fixTableName : TPCEConstants.SCALING_TABLES){
                LoaderThread thread = new LoaderThread(this.benchmark) {
                    @Override
                    public void load(Connection conn) throws SQLException {
                        loadTable();
                    }
                };
                threads.add(thread);
            }
        }catch (Exception e){
            LOG.error("Failed to generate and load fixed-sized tables", e);
            System.exit(1);
        }
        return threads;
    }

    public TPCELoader(TPCEBenchmark tpceBenchmark){
        super(tpceBenchmark);
        this.generator = new TPCEGenerator(flatFilesPath, total_customers, scale_factor, initial_days);
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
        try (PreparedStatement preparedStatement = getInsertStatement(conn, TPCCConstants.TABLENAME_ITEM)){
            int index = 0;
            boolean debug = true;

            Iterator <Object[]> table_gen = this.generator.getTableGen(tableName);
            while (table_gen.hasNext()) {
                Object tuple[] = table_gen.next();

                if (debug) {
                    StringBuilder sb = new StringBuilder();
                    for (Object o: tuple) {
                        sb.append(o.toString());
                        sb.append('|');
                    }
                    LOG.trace("Table[" + tableName + "], Tuple[" + index + "]: "+ sb);
                }

                //process the statement

                index ++;
                if (index > 0 && index % batch_size == 0) {
                    LOG.debug("Storing batch of " + batch_size + " tuples for " + tableName + " [total=" + index + "]");
                    preparedStatement.executeBatch();
                }
            }

            preparedStatement.executeBatch();
        }catch (SQLException se){
            LOG.error("Failed to insert data in fixed-sized tables", se);
        }

    }



    private void loadGrowingTables(Connection conn, Database catalog, int batchSize) {
        LOG.debug("Loading records for growing tables in batches of " + batchSize);
        boolean debug = true;
        Map<String, VoltTable> growingVoltTables = new HashMap<String, VoltTable>();

        for (String tableName: TPCEConstants.GROWING_TABLES) {
            VoltTable vt = CatalogUtil.getVoltTable(catalog.getTables().get(tableName));
            growingVoltTables.put(tableName, vt);
        }

        try {
            TradeGenerator tableGen = new TradeGenerator(this.generator, catalog);
            while (tableGen.hasNext()) {
                Object tuple[] = tableGen.next(); // tuple
                String tableName = tableGen.getCurrentTable(); // table name the tuple is for
                VoltTable vt = growingVoltTables.get(tableName);
                int row_idx = vt.getRowCount();

                if (debug) {
                    StringBuilder sb = new StringBuilder();
                    for (Object o: tuple) {
                        sb.append(o.toString());
                        sb.append('|');
                    }
                    LOG.trace("Table[" + tableName + "], Tuple[" + row_idx + "]: "+ sb);
                }

                vt.addRow(tuple);
                row_idx++;

                if (row_idx % batchSize == 0) {
                    LOG.debug("Storing batch of " + batchSize + " tuples for " + tableName + " [total=" + row_idx + "]");
                    this.loadVoltTable(tableName, vt);
                    vt.clearRowData();
                }
            } // FOR
        } catch (Exception ex) {
            LOG.error("Failed to load growing tables", ex);
            System.exit(1);
        }

        // loading remaining (out-of-batch) tuples
        for (Map.Entry<String, VoltTable> table: growingVoltTables.entrySet()) {
            String tableName = table.getKey();
            VoltTable vt = table.getValue();
            int row_idx = vt.getRowCount();

            if (row_idx > 0)
                this.loadVoltTable(tableName, vt);

            LOG.debug("Finished loading " + row_idx + " final tuples for " + tableName);
        }
    }




}
