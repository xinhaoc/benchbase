package com.oltpbenchmark.benchmarks.tpce.procedures;

import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TradeUpdate extends TPCEProcedure{
    private TTradeUpdateTxnInput tradeUpdateTxnInput;
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(TradeUpdate.class);
    @Override
    public boolean run(Connection connection) throws SQLException {
        TTradeUpdateTxnOutput tTradeUpdateTxnOutput = new TTradeUpdateTxnOutput();
        sutInterfaces.CTradeUpdate(connection, tradeUpdateTxnInput, tTradeUpdateTxnOutput);
        if (tTradeUpdateTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Trade update volume error" + tTradeUpdateTxnOutput.getStatus());
            return false;
        }
    }

    public TradeUpdate(TTradeUpdateTxnInput tradeUpdateTxnInput){
        this.tradeUpdateTxnInput = tradeUpdateTxnInput;
    }
}
