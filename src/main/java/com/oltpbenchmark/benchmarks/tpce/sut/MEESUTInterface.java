package com.oltpbenchmark.benchmarks.tpce.sut;

import com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed.TMarketFeedTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult.TTradeResultTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketfeed.TMarketFeedTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.traderesult.TTradeResultTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import com.oltpbenchmark.benchmarks.tpce.transactionsinterface.SUTInterfaces;
import com.oltpbenchmark.benchmarks.tpce.transactionsinterface.SUTInterfacesImpl;
import org.apache.log4j.Logger;

public class MEESUTInterface {
    private final SUTInterfaces sutInterfaces = new SUTInterfacesImpl();
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(MEESUTInterface.class);
    public boolean TradeResult( TTradeResultTxnInput pTxnInput ){
        TTradeResultTxnOutput tTradeResultTxnOutput = new TTradeResultTxnOutput();
        sutInterfaces.CTradeResult(pTxnInput, tTradeResultTxnOutput);
        if (tTradeResultTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Trade result error" + tTradeResultTxnOutput.getStatus());
            return false;
        }
    }
    public boolean MarketFeed( TMarketFeedTxnInput pTxnInput ){
        TMarketFeedTxnOutput tMarketFeedTxnOutput = new TMarketFeedTxnOutput();
        sutInterfaces.CMarketFeed(pTxnInput, tMarketFeedTxnOutput);
        if (tMarketFeedTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Market feed error" + tMarketFeedTxnOutput.getStatus());
            return false;
        }
    }
}
