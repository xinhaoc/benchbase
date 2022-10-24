package com.oltpbenchmark.benchmarks.tpce.sut;

import com.oltpbenchmark.benchmarks.tpce.TPCEGenerator;
import com.oltpbenchmark.benchmarks.tpce.inputdo.TTradeStatusTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.brokervolume.TBrokerVolumeTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition.TCustomerPositionTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketwatch.TMarketWatchTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.securitydetail.TSecurityDetailTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup.TTradeLookupTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder.TTradeOrderTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.brokervolume.TBrokerVolumeTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition.TCustomerPositionTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketwatch.TMarketWatchTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.securitydetail.TSecurityDetailTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup.TTradeLookupTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradestatus.TTradeStatusTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import com.oltpbenchmark.benchmarks.tpce.transactionsinterface.SUTInterfaces;
import com.oltpbenchmark.benchmarks.tpce.transactionsinterface.SUTInterfacesImpl;
import org.apache.log4j.Logger;

public class CESUTInterface {
    private final SUTInterfaces sutInterfaces = new SUTInterfacesImpl();
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(CESUTInterface.class);

    public boolean BrokerVolume(TBrokerVolumeTxnInput pTxnInput) {
        TBrokerVolumeTxnOutput tBrokerVolumeTxnOutput = new TBrokerVolumeTxnOutput();
        sutInterfaces.CBrokerVolume(pTxnInput, tBrokerVolumeTxnOutput);
        if (tBrokerVolumeTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Broker volume error" + tBrokerVolumeTxnOutput.getStatus());
            return false;
        }

    }

    public boolean CustomerPosition(TCustomerPositionTxnInput pTxnInput) {
        TCustomerPositionTxnOutput tCustomerPositionTxnOutput = new TCustomerPositionTxnOutput();
        sutInterfaces.CCustomerPosition(pTxnInput, tCustomerPositionTxnOutput);
        if (tCustomerPositionTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Customer Position volume error" + tCustomerPositionTxnOutput.getStatus());
            return false;
        }

    }

    public boolean MarketWatch(TMarketWatchTxnInput pTxnInput) {
        TMarketWatchTxnOutput tMarketFeedTxnOutput = new TMarketWatchTxnOutput();
        sutInterfaces.CMarketWatch(pTxnInput, tMarketFeedTxnOutput);
        if (tMarketFeedTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Marktet Watch volume error" + tMarketFeedTxnOutput.getStatus());
            return false;
        }

    }

    public boolean SecurityDetail(TSecurityDetailTxnInput pTxnInput) {
        TSecurityDetailTxnOutput tSecurityDetailTxnOutput = new TSecurityDetailTxnOutput();
        sutInterfaces.CSecurityDetail(pTxnInput, tSecurityDetailTxnOutput);
        if (tSecurityDetailTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Security Detail volume error" + tSecurityDetailTxnOutput.getStatus());
            return false;
        }
    }

    public boolean TradeLookup(TTradeLookupTxnInput pTxnInput) {
        TTradeLookupTxnOutput tradeLookupTxnOutput = new TTradeLookupTxnOutput();
        sutInterfaces.CTradeLookup(pTxnInput, tradeLookupTxnOutput);
        if (tradeLookupTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Trade lookup volume error" + tradeLookupTxnOutput.getStatus());
            return false;
        }
    }

    public boolean TradeOrder(TTradeOrderTxnInput pTxnInput, int iTradeType, boolean bExecutorIsAccountOwner) {
        TTradeOrderTxnOutput tTradeOrderTxnOutput = new TTradeOrderTxnOutput();
        sutInterfaces.CTradeOrder(pTxnInput, tTradeOrderTxnOutput);
        if (tTradeOrderTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Trade order volume error" + tTradeOrderTxnOutput.getStatus());
            return false;
        }
    }

    public boolean TradeStatus(TTradeStatusTxnInput pTxnInput) {
        TTradeStatusTxnOutput tTradeStatusTxnOutput = new TTradeStatusTxnOutput();
        sutInterfaces.CTradeStatus(pTxnInput, tTradeStatusTxnOutput);
        if (tTradeStatusTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Trade status volume error" + tTradeStatusTxnOutput.getStatus());
            return false;
        }
    }

    public boolean TradeUpdate(TTradeUpdateTxnInput pTxnInput) {
        TTradeUpdateTxnOutput tTradeUpdateTxnOutput = new TTradeUpdateTxnOutput();
        sutInterfaces.CTradeUpdate(pTxnInput, tTradeUpdateTxnOutput);
        if (tTradeUpdateTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Trade update volume error" + tTradeUpdateTxnOutput.getStatus());
            return false;
        }
    }
}
