package com.oltpbenchmark.benchmarks.tpce.procedures;

import com.oltpbenchmark.benchmarks.tpce.inputdo.marketwatch.TMarketWatchTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketwatch.TMarketWatchTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class MarketWatch extends TPCEProcedure{
    private TMarketWatchTxnInput marketWatchTxnInput;
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(MarketWatch.class);
    @Override
    public boolean run(Connection connection) throws SQLException {
        TMarketWatchTxnOutput tMarketFeedTxnOutput = new TMarketWatchTxnOutput();
        sutInterfaces.CMarketWatch(connection, marketWatchTxnInput, tMarketFeedTxnOutput);
        if (tMarketFeedTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Marktet Watch volume error" + tMarketFeedTxnOutput.getStatus());
            return false;
        }
    }

    public MarketWatch(TMarketWatchTxnInput marketWatchTxnInput){
        this.marketWatchTxnInput = marketWatchTxnInput;
    }
}
