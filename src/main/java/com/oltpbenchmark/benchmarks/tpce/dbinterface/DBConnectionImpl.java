package com.oltpbenchmark.benchmarks.tpce.dbinterface;

import com.oltpbenchmark.benchmarks.tpce.inputdo.TTradeStatusTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.brokervolume.TBrokerVolumeTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.cleanup.TTradeCleanupTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition.TCustomerPositionFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition.TCustomerPositionFrame2Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.datamaintenance.TDataMaintenanceTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed.TMarketFeedFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketwatch.TMarketWatchTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.securitydetail.TSecurityDetailTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup.TTradeLookupFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup.TTradeLookupFrame2Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup.TTradeLookupFrame3Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup.TTradeLookupFrame4Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder.TTradeOrderFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder.TTradeOrderFrame2Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder.TTradeOrderFrame3Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder.TTradeOrderFrame4Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult.*;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateFrame2Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateFrame3Input;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TMarketWatchFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.brokervolume.TBrokerVolumeFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition.TCustomerPositionFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition.TCustomerPositionFrame2Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketfeed.TMarketFeedFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.securitydetail.TSecurityDetailFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup.TTradeLookupFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup.TTradeLookupFrame2Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup.TTradeLookupFrame3Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup.TTradeLookupFrame4Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderFrame2Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderFrame3Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderFrame4Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.traderesult.*;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradestatus.TTradeStatusFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateFrame2Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateFrame3Output;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionImpl implements DBConnection{
    @java.lang.Override
    public ResultSet exec(Connection conn, PreparedStatement statement) throws SQLException {
        return null;
    }

    @java.lang.Override
    public void execute(Connection conn, TBrokerVolumeTxnInput tBrokerVolumeTxnInput, TBrokerVolumeFrame1Output tBrokerVolumeFrame1Output) {

    }

    @java.lang.Override
    public void execute(Connection conn, TCustomerPositionFrame1Input tCustomerPositionFrame1Input, TCustomerPositionFrame1Output tCustomerPositionFrame1Output) {

    }

    @java.lang.Override
    public void execute(Connection conn, TCustomerPositionFrame2Input tCustomerPositionFrame2Input, TCustomerPositionFrame2Output tCustomerPositionFrame2Output) {

    }

    @java.lang.Override
    public void execute(Connection conn, TDataMaintenanceTxnInput tDataMaintenanceTxnInput) {

    }

//    @java.lang.Override
//    public void execute(TMarketFeedFrame1Input tMarketFeedFrame1Input, TMarketFeedFrame1Output tMarketFeedFrame1Output, CSendToMarketInterface cSendToMarketInterface) {
//
//    }

    @java.lang.Override
    public void execute(Connection conn, TMarketWatchTxnInput tMarketWatchFrame1Input, TMarketWatchFrame1Output tMarketWatchFrame1Output) {

    }

    @java.lang.Override
    public void execute(Connection conn, TSecurityDetailTxnInput tSecurityDetailFrame1Input, TSecurityDetailFrame1Output tSecurityDetailFrame1Output) {

    }

    @java.lang.Override
    public void execute(Connection conn, TTradeCleanupTxnInput tTradeCleanupFrame1Input) {

    }

    @java.lang.Override
    public void execute(Connection conn, TTradeLookupFrame1Input tTradeLookupFrame1Input, TTradeLookupFrame1Output tTradeLookupFrame1Output) {

    }

    @java.lang.Override
    public void execute(Connection conn, TTradeLookupFrame2Input tTradeLookupFrame2Input, TTradeLookupFrame2Output tTradeLookupFrame2Output) {

    }

    @java.lang.Override
    public void execute(Connection conn, TTradeLookupFrame3Input tTradeLookupFrame3Input, TTradeLookupFrame3Output tTradeLookupFrame3Output) {

    }

    @java.lang.Override
    public void execute(Connection conn, TTradeLookupFrame4Input tTradeLookupFrame4Input, TTradeLookupFrame4Output tTradeLookupFrame4Output) {

    }

    @java.lang.Override
    public void execute(TTradeOrderFrame1Input tTradeOrderFrame1Input, TTradeOrderFrame1Output tTradeOrderFrame1Output) {

    }

    @java.lang.Override
    public void execute(TTradeOrderFrame2Input tTradeOrderFrame2Input, TTradeOrderFrame2Output tTradeOrderFrame2Output) {

    }

    @java.lang.Override
    public void execute(TTradeOrderFrame3Input tTradeOrderFrame3Input, TTradeOrderFrame3Output tTradeOrderFrame3Output) {

    }

    @java.lang.Override
    public void execute(TTradeOrderFrame4Input tTradeOrderFrame4Input, TTradeOrderFrame4Output tTradeOrderFrame4Output) {

    }

    @java.lang.Override
    public void execute(TTradeResultFrame1Input tTradeResultFrame1Input, TTradeResultFrame1Output tTradeResultFrame1Output) {

    }

    @java.lang.Override
    public void execute(TTradeResultFrame2Input tTradeResultFrame2Input, TTradeResultFrame2Output tTradeResultFrame2Output) {

    }

    @java.lang.Override
    public void execute(TTradeResultFrame3Input tTradeResultFrame3Input, TTradeResultFrame3Output tTradeResultFrame3Output) {

    }

    @java.lang.Override
    public void execute(TTradeResultFrame4Input tTradeResultFrame4Input, TTradeResultFrame4Output tTradeResultFrame4Output) {

    }

    @java.lang.Override
    public void execute(TTradeResultFrame5Input tTradeResultFrame5Input) {

    }

    @java.lang.Override
    public void execute(TTradeResultFrame6Input tTradeResultFrame6Input, TTradeResultFrame6Output tTradeResultFrame6Output) {

    }

    @java.lang.Override
    public void execute(TTradeStatusTxnInput tTradeStatusFrame1Input, TTradeStatusFrame1Output tTradeStatusFrame1Output) {

    }

    @java.lang.Override
    public void execute(TTradeUpdateFrame1Input tTradeUpdateFrame1Input, TTradeUpdateFrame1Output tTradeUpdateFrame1Output) {

    }

    @java.lang.Override
    public void execute(TTradeUpdateFrame2Input tTradeUpdateFrame2Input, TTradeUpdateFrame2Output tTradeUpdateFrame2Output) {

    }

    @java.lang.Override
    public void execute(TTradeUpdateFrame3Input tTradeUpdateFrame3Input, TTradeUpdateFrame3Output tTradeUpdateFrame3Output) {

    }

    @java.lang.Override
    public void reconnect() {

    }

    @java.lang.Override
    public void rollback() {

    }

//    @java.lang.Override
//    public void setBrokerageHouse(CBrokerageHouse cBrokerageHouse) {
//
//    }

    @java.lang.Override
    public void setReadCommitted() {

    }

    @java.lang.Override
    public void setReadUncommitted() {

    }

    @java.lang.Override
    public void setRepeatableRead() {

    }

    @java.lang.Override
    public void setSerializable() {

    }
}
