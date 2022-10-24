package com.oltpbenchmark.benchmarks.tpce.sut;

import com.oltpbenchmark.benchmarks.tpce.TPCEGenerator;
import com.oltpbenchmark.benchmarks.tpce.inputdo.cleanup.TTradeCleanupTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.datamaintenance.TDataMaintenanceTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TDataMaintenanceTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TTradeCleanupTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import com.oltpbenchmark.benchmarks.tpce.transactionsinterface.SUTInterfaces;
import com.oltpbenchmark.benchmarks.tpce.transactionsinterface.SUTInterfacesImpl;
import org.apache.log4j.Logger;

public class DMSUTInterface {
    private final SUTInterfaces sutInterfaces = new SUTInterfacesImpl();
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(DMSUTInterface.class);

    public boolean DataMaintenance( TDataMaintenanceTxnInput pTxnInput ){
        TDataMaintenanceTxnOutput tDataMaintenanceTxnOutput = new TDataMaintenanceTxnOutput();
        sutInterfaces.CDataMaintenance(pTxnInput, tDataMaintenanceTxnOutput);
        if (tDataMaintenanceTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Data Maintain error" + tDataMaintenanceTxnOutput.getStatus());
            return false;
        }
    }
    public boolean TradeCleanup( TTradeCleanupTxnInput pTxnInput ){
        TTradeCleanupTxnOutput tTradeCleanupTxnOutput = new TTradeCleanupTxnOutput();
        sutInterfaces.CTradeCleanup(pTxnInput, tTradeCleanupTxnOutput);
        if (tTradeCleanupTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Trade clean error" + tTradeCleanupTxnOutput.getStatus());
            return false;
        }
    }
}
