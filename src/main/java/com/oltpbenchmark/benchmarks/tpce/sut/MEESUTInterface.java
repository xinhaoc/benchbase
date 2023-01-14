package com.oltpbenchmark.benchmarks.tpce.sut;

import com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed.TMarketFeedTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult.TTradeResultTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketfeed.TMarketFeedTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.traderesult.TTradeResultTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import com.oltpbenchmark.benchmarks.tpce.transactionsinterface.MarketInterface;
import com.oltpbenchmark.benchmarks.tpce.transactionsinterface.MarketInterfaceImpl;
import com.oltpbenchmark.benchmarks.tpce.transactionsinterface.SUTInterfaces;
import com.oltpbenchmark.benchmarks.tpce.transactionsinterface.SUTInterfacesImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class MEESUTInterface {
    private final MarketInterface marketInterface = new MarketInterfaceImpl();
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(MEESUTInterface.class);
    public boolean TradeResult(Connection connection, TTradeResultTxnInput pTxnInput ){
        TTradeResultTxnOutput tTradeResultTxnOutput = new TTradeResultTxnOutput();
        marketInterface.CTradeResult(connection, pTxnInput, tTradeResultTxnOutput);
        if (tTradeResultTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Trade result error" + tTradeResultTxnOutput.getStatus());
            return false;
        }
    }
    public boolean MarketFeed(Connection connection, TMarketFeedTxnInput pTxnInput ){

        TMarketFeedTxnOutput tMarketFeedTxnOutput = new TMarketFeedTxnOutput();
        marketInterface.CMarketFeed(connection, pTxnInput, tMarketFeedTxnOutput);
        if (tMarketFeedTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Market feed error" + tMarketFeedTxnOutput.getStatus());
            return false;
        }
    }

}
