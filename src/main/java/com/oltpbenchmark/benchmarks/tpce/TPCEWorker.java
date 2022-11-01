package com.oltpbenchmark.benchmarks.tpce;

import com.oltpbenchmark.SubmittedProcedure;
import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.TransactionType;
import com.oltpbenchmark.api.Worker;
import com.oltpbenchmark.benchmarks.tpcc.TPCCWorker;
import com.oltpbenchmark.benchmarks.tpce.emulator.CE;
import com.oltpbenchmark.benchmarks.tpce.emulator.DM;
import com.oltpbenchmark.benchmarks.tpce.emulator.MEE;
import com.oltpbenchmark.benchmarks.tpce.inputdo.TTradeStatusTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.brokervolume.TBrokerVolumeTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.cleanup.TTradeCleanupTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition.TCustomerPositionTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.datamaintenance.TDataMaintenanceTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed.TMarketFeedTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketwatch.TMarketWatchTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.securitydetail.TSecurityDetailTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup.TTradeLookupTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder.TTradeOrderTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult.TTradeResultTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateTxnInput;
import com.oltpbenchmark.benchmarks.tpce.log.BaseLogger;
import com.oltpbenchmark.benchmarks.tpce.settings.TDriverCETxnSettings;
import com.oltpbenchmark.benchmarks.tpce.sut.CESUTInterface;
import com.oltpbenchmark.benchmarks.tpce.sut.DMSUTInterface;
import com.oltpbenchmark.benchmarks.tpce.sut.MEESUTInterface;
import com.oltpbenchmark.benchmarks.tpce.tablegenerator.SecurityHandler;
import com.oltpbenchmark.types.TransactionStatus;
import com.oltpbenchmark.api.Procedure.UserAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class TPCEWorker<EGenLogFormatterTab> extends Worker<TPCEBenchmark> {

    private static final Logger LOG = LoggerFactory.getLogger(TPCEWorker.class);
    private long timestamp;

    private CESUTInterface sut;
    private DMSUTInterface dmsutInterface;
    private TBrokerVolumeTxnInput brokerVolumeTxnInput;
    private TCustomerPositionTxnInput customerPositionTxnInput;
    private TDataMaintenanceTxnInput dataMaintenanceTxnInput;
    private TMarketFeedTxnInput marketFeedTxnInput;
    private TMarketWatchTxnInput marketWatchTxnInput;
    private TSecurityDetailTxnInput securityDetailTxnInput;
    private TTradeCleanupTxnInput tradeCleanupTxnInput;
    private TTradeLookupTxnInput tradeLookupTxnInput;
    private TTradeOrderTxnInput tradeOrderTxnInput;
    private TTradeResultTxnInput tradeResultTxnInput;
    private TTradeStatusTxnInput tradeStatusTxnInput;
    private TTradeUpdateTxnInput tradeUpdateTxnInput;
    private TDriverCETxnSettings driverCETxnSettings;
    private EGenLogFormatterTab logFormat;
    private BaseLogger logger;
    private CE customerEmulator;
    private DM dataMaintenanceGenerator;
    private DMSUTInterface dataMaintenanceCallback;
    private MEE marketExchangeGenerator;
    private MEESUTInterface marketExchangeCallback;
    private SecurityHandler securityHandler;


    public TPCEWorker(TPCEBenchmark benchmarkModule, int id) {
        super(benchmarkModule, id);
//        logFormat = new EGenLogFormatterTab();
//        logger = new EGenLogger(DriverType.eDriverEGenLoader, 0, logFormat);
//        brokerVolumeTxnInput = new TBrokerVolumeTxnInput();
//        customerPositionTxnInput = new TCustomerPositionTxnInput();
//        dataMaintenanceTxnInput = new TDataMaintenanceTxnInput();
//        marketFeedTxnInput = new TMarketFeedTxnInput();
//        marketWatchTxnInput = new TMarketWatchTxnInput();
//        securityDetailTxnInput = new TSecurityDetailTxnInput();
//        tradeCleanupTxnInput = new TTradeCleanupTxnInput();
//        tradeLookupTxnInput = new TTradeLookupTxnInput();
//        tradeOrderTxnInput = new TTradeOrderTxnInput();
//        tradeResultTxnInput = new TTradeResultTxnInput();
//        tradeStatusTxnInput = new TTradeStatusTxnInput();
//        tradeUpdateTxnInput = new TTradeUpdateTxnInput();
//        driverCETxnSettings = new TDriverCETxnSettings();
//
//        File inputDir = new File(dataPath);
//        TPCEGenerator inputFiles = new TPCEGenerator(inputDir, totalCustomerCount, scaleFactor, initialDays);
//        securityHandler = new SecurityHandler(inputFiles);
//
//        //CE input generator
//        sut = new CESUTInterface();
//        customerEmulator = new CE(sut, logger, inputFiles, configuredCustomerCount, totalCustomerCount, scaleFactor, initialDays, 0, driverCETxnSettings);
//
//        // DataMaintenance input generator
//        dataMaintenanceCallback = new DataMaintenanceCallback(dataMaintenanceTxnInput, tradeCleanupTxnInput);
//        dataMaintenanceGenerator = new DM(dataMaintenanceCallback, logger, inputFiles, configuredCustomerCount, totalCustomerCount, scaleFactor, initialDays, 1);
//
//        //MarketExchange input generator
//        marketExchangeCallback = new MarketExchangeCallback(tradeResultTxnInput, marketFeedTxnInput);
//        marketExchangeGenerator = new MEE(0, marketExchangeCallback, logger, securityHandler, 1, configuredCustomerCount);
//        marketExchangeGenerator.enableTickerTape();
//
//        timestamp = System.currentTimeMillis();
    }

    @Override
    protected TransactionStatus executeWork(Connection conn, TransactionType txnType) throws UserAbortException, SQLException {
        try {
            //use type to do CE
            //doTxn
//            customerEmulator.doTxn(txnType.getId());
            //do DataMaintenance per minute
            if (System.currentTimeMillis() - timestamp > TPCEConstants.DATA_MAINTENANCE_TIME_SPACE) {
//                dm.dotxn();
            }

        } catch (Exception e) {

        }

        return TransactionStatus.SUCCESS;
    }

    //generate
//    @Override
//    protected SubmittedProcedure generateProcedure(SubmittedProcedure originPieceOfWork) {
////        return new SubmittedProcedure(customerEmulator.getCETxnMixGenerator().generateNextTxnType());
//    }
}
