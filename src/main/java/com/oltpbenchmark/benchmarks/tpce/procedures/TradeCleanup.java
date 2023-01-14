package com.oltpbenchmark.benchmarks.tpce.procedures;

import com.oltpbenchmark.benchmarks.tpce.inputdo.cleanup.TTradeCleanupTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TTradeCleanupTxnOutput;

import java.sql.Connection;
import java.sql.SQLException;

public class TradeCleanup extends TPCEProcedure{
    private TTradeCleanupTxnInput tradeCleanupTxnInput;
    @Override
    public boolean run(Connection connection) throws SQLException {
        TTradeCleanupTxnOutput tTradeCleanupTxnOutput = new TTradeCleanupTxnOutput();
        sutInterfaces.CTradeCleanup(connection, tradeCleanupTxnInput, tTradeCleanupTxnOutput);
        return true;
    }

    public TradeCleanup(TTradeCleanupTxnInput tradeCleanupTxnInput){
        this.tradeCleanupTxnInput = tradeCleanupTxnInput;
    }
}
