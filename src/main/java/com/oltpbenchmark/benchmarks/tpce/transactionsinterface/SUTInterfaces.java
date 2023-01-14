package com.oltpbenchmark.benchmarks.tpce.transactionsinterface;

import com.oltpbenchmark.benchmarks.tpce.emulator.TTradeRequest;
import com.oltpbenchmark.benchmarks.tpce.inputdo.TTradeStatusTxnInput;
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

import java.sql.Connection;
import java.sql.SQLException;

public interface SUTInterfaces {
    public void CBrokerVolume(Connection connection, TBrokerVolumeTxnInput input, TBrokerVolumeTxnOutput output) throws SQLException;

    public void CCustomerPosition(Connection connection, TCustomerPositionTxnInput input, TCustomerPositionTxnOutput output) throws SQLException;

    public void CDataMaintenance(Connection connection, TDataMaintenanceTxnInput input, TDataMaintenanceTxnOutput output);

    public void CMarketFeed(Connection connection, TMarketFeedTxnInput input, TMarketFeedTxnOutput output);

    public void CMarketWatch(Connection connection, TMarketWatchTxnInput input, TMarketWatchTxnOutput output) throws SQLException;

    public void CSecurityDetail(Connection connection, TSecurityDetailTxnInput input, TSecurityDetailTxnOutput output) throws SQLException;

    public void CTradeCleanup(Connection connection, TTradeCleanupTxnInput input, TTradeCleanupTxnOutput output) throws SQLException;

    public void CTradeLookup(Connection connection, TTradeLookupTxnInput input, TTradeLookupTxnOutput output);

    public void CTradeOrder(Connection connection, TTradeOrderTxnInput input, TTradeOrderTxnOutput output) throws SQLException;

    public void CTradeStatus(Connection connection, TTradeStatusTxnInput input, TTradeStatusTxnOutput output) throws SQLException;

    public void CTradeUpdate(Connection connection, TTradeUpdateTxnInput input, TTradeUpdateTxnOutput output);


}
