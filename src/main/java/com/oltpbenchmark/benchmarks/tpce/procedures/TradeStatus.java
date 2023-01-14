package com.oltpbenchmark.benchmarks.tpce.procedures;

import com.oltpbenchmark.benchmarks.tpce.inputdo.TTradeStatusTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradestatus.TTradeStatusTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TradeStatus extends TPCEProcedure{

    private TTradeStatusTxnInput tradeStatusTxnInput;
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(TradeStatus.class);
    @Override
    public boolean run(Connection connection) throws SQLException {
        TTradeStatusTxnOutput tTradeStatusTxnOutput = new TTradeStatusTxnOutput();
        sutInterfaces.CTradeStatus(connection, tradeStatusTxnInput, tTradeStatusTxnOutput);
        if (tTradeStatusTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Trade status volume error" + tTradeStatusTxnOutput.getStatus());
            return false;
        }
    }

    public TradeStatus(TTradeStatusTxnInput tradeStatusTxnInput){
        this.tradeStatusTxnInput = tradeStatusTxnInput;
    }
}
