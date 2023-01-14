package com.oltpbenchmark.benchmarks.tpce.transactionsinterface;

import com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed.TMarketFeedTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketwatch.TMarketWatchTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult.TTradeResultTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketfeed.TMarketFeedTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketwatch.TMarketWatchTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.traderesult.TTradeResultTxnOutput;

import java.sql.Connection;
import java.sql.SQLException;

public interface MarketInterface {
    public void CMarketFeed(Connection connection, TMarketFeedTxnInput input, TMarketFeedTxnOutput output);

    public void CTradeResult(Connection connection, TTradeResultTxnInput input, TTradeResultTxnOutput output);

}
