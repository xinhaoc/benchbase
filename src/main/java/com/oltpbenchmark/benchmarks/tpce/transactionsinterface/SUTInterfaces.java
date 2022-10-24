package com.oltpbenchmark.benchmarks.tpce.transactionsinterface;

import com.oltpbenchmark.benchmarks.tpce.emulator.TTradeRequest;
import com.oltpbenchmark.benchmarks.tpce.inputdo.TTradeStatusTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.brokervolume.TBrokerVolumeFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.brokervolume.TBrokerVolumeTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.cleanup.TTradeCleanupTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition.TCustomerPositionTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.datamaintenance.TDataMaintenanceTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed.TMarketFeedTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketwatch.TMarketWatchTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.securitydetail.TSecurityDetailTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup.TTradeLookupTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder.TTradeOrderTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult.TTradeResultTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TDataMaintenanceTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TTradeCleanupTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.brokervolume.TBrokerVolumeFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.brokervolume.TBrokerVolumeTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition.TCustomerPositionTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketfeed.TMarketFeedTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketwatch.TMarketWatchTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.securitydetail.TSecurityDetailTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup.TTradeLookupTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.traderesult.TTradeResultTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradestatus.TTradeStatusTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateTxnOutput;

import java.lang.reflect.InvocationTargetException;

public interface SUTInterfaces {
    public void CBrokerVolume(TBrokerVolumeTxnInput input, TBrokerVolumeTxnOutput output);

    public void CCustomerPosition(TCustomerPositionTxnInput input, TCustomerPositionTxnOutput output);

    public void CDataMaintenance(TDataMaintenanceTxnInput input, TDataMaintenanceTxnOutput output);

    public void CMarketFeed(TMarketFeedTxnInput input, TMarketFeedTxnOutput output);

    public void CMarketWatch(TMarketWatchTxnInput input, TMarketWatchTxnOutput output);

    public void CSecurityDetail(TSecurityDetailTxnInput input, TSecurityDetailTxnOutput output);

    public void CSendToMarketInterface(TTradeRequest tTradeRequest);

    public void CTradeCleanup(TTradeCleanupTxnInput input, TTradeCleanupTxnOutput output);

    public void CTradeLookup(TTradeLookupTxnInput input, TTradeLookupTxnOutput output);

    public void CTradeOrder(TTradeOrderTxnInput input, TTradeOrderTxnOutput output);

    public void CTradeResult(TTradeResultTxnInput input, TTradeResultTxnOutput output);

    public void CTradeStatus(TTradeStatusTxnInput input, TTradeStatusTxnOutput output);

    public void CTradeUpdate(TTradeUpdateTxnInput input, TTradeUpdateTxnOutput output);


}
