package com.oltpbenchmark.benchmarks.tpce.procedures;

import com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup.TTradeLookupTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup.TTradeLookupTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TradeLookUp extends TPCEProcedure{

    private TTradeLookupTxnInput tradeLookupTxnInput;
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(TradeLookUp.class);
    @Override
    public boolean run(Connection connection) throws SQLException {
        TTradeLookupTxnOutput tradeLookupTxnOutput = new TTradeLookupTxnOutput();
        sutInterfaces.CTradeLookup(connection, tradeLookupTxnInput, tradeLookupTxnOutput);
        if (tradeLookupTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Trade lookup volume error" + tradeLookupTxnOutput.getStatus());
            return false;
        }
    }

    public TradeLookUp(TTradeLookupTxnInput tradeLookupTxnInput){
        this.tradeLookupTxnInput = tradeLookupTxnInput;
    }
}
