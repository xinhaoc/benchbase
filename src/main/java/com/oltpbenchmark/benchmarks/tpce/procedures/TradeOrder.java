package com.oltpbenchmark.benchmarks.tpce.procedures;

import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder.TTradeOrderTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TradeOrder extends TPCEProcedure{
    private TTradeOrderTxnInput tradeOrderTxnInput;
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(TradeOrder.class);
    @Override
    public boolean run(Connection connection) throws SQLException {
        TTradeOrderTxnOutput tTradeOrderTxnOutput = new TTradeOrderTxnOutput();
        sutInterfaces.CTradeOrder(connection, tradeOrderTxnInput, tTradeOrderTxnOutput);
        if (tTradeOrderTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Trade order volume error" + tTradeOrderTxnOutput.getStatus());
            return false;
        }
    }

    public TradeOrder(TTradeOrderTxnInput tradeOrderTxnInput){
        this.tradeOrderTxnInput = tradeOrderTxnInput;
    }
}
