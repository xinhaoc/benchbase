package com.oltpbenchmark.benchmarks.tpce.dbinterface;

import com.oltpbenchmark.benchmarks.tpce.inputdo.brokervolume.TBrokerVolumeTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.cleanup.TTradeCleanupTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.datamaintenance.TDataMaintenanceFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.datamaintenance.TDataMaintenanceTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketwatch.TMarketWatchFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.securitydetail.TSecurityDetailFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.TTradeStatusTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.brokervolume.TBrokerVolumeFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.cleanup.TTradeCleanupFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition.TCustomerPositionFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition.TCustomerPositionFrame2Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed.TMarketFeedFrame1Input;
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
import com.oltpbenchmark.benchmarks.tpce.outputdo.TDataMaintenanceTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TMarketWatchFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketwatch.TMarketWatchTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TTradeCleanupTxnOutput;
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

public interface DBConnection {

    public ResultSet exec(Connection conn, PreparedStatement statement) throws SQLException;

    public void execute(Connection conn, TBrokerVolumeTxnInput tBrokerVolumeTxnInput,
                        TBrokerVolumeFrame1Output tBrokerVolumeFrame1Output);

    void execute(Connection conn, TCustomerPositionFrame1Input tCustomerPositionFrame1Input,
                 TCustomerPositionFrame1Output tCustomerPositionFrame1Output);
    void execute(Connection conn, TCustomerPositionFrame2Input tCustomerPositionFrame2Input,
                 TCustomerPositionFrame2Output tCustomerPositionFrame2Output);

    void execute(Connection conn, TDataMaintenanceTxnInput tDataMaintenanceTxnInput, TDataMaintenanceTxnOutput tDataMaintenanceTxnOutput);

    void execute(Connection conn, TMarketFeedFrame1Input tMarketFeedFrame1Input, TMarketFeedFrame1Output tMarketFeedFrame1Output);

    void execute(Connection conn, TMarketWatchTxnInput tMarketWatchFrame1Input, TMarketWatchFrame1Output tMarketWatchFrame1Output);

    void execute(Connection conn, TSecurityDetailTxnInput tSecurityDetailFrame1Input,
                 TSecurityDetailFrame1Output tSecurityDetailFrame1Output);

    void execute(Connection conn, TTradeCleanupTxnInput tTradeCleanupFrame1Input, TTradeCleanupTxnOutput tTradeCleanupTxnOutput);

    void execute(Connection conn, TTradeLookupFrame1Input tTradeLookupFrame1Input, TTradeLookupFrame1Output tTradeLookupFrame1Output);
    void execute(Connection conn, TTradeLookupFrame2Input tTradeLookupFrame2Input, TTradeLookupFrame2Output tTradeLookupFrame2Output);
    void execute(Connection conn, TTradeLookupFrame3Input tTradeLookupFrame3Input, TTradeLookupFrame3Output tTradeLookupFrame3Output);
    void execute(Connection conn, TTradeLookupFrame4Input tTradeLookupFrame4Input, TTradeLookupFrame4Output tTradeLookupFrame4Output);

    void execute(Connection conn, TTradeOrderFrame1Input tTradeOrderFrame1Input, TTradeOrderFrame1Output tTradeOrderFrame1Output);
    void execute(Connection conn, TTradeOrderFrame2Input tTradeOrderFrame2Input, TTradeOrderFrame2Output tTradeOrderFrame2Output);
    void execute(Connection conn, TTradeOrderFrame3Input tTradeOrderFrame3Input, TTradeOrderFrame3Output tTradeOrderFrame3Output);
    void execute(Connection conn, TTradeOrderFrame4Input tTradeOrderFrame4Input, TTradeOrderFrame4Output tTradeOrderFrame4Output);

    void execute(Connection conn, TTradeResultFrame1Input tTradeResultFrame1Input, TTradeResultFrame1Output tTradeResultFrame1Output);
    void execute(Connection conn, TTradeResultFrame2Input tTradeResultFrame2Input, TTradeResultFrame2Output tTradeResultFrame2Output);
    void execute(Connection conn, TTradeResultFrame3Input tTradeResultFrame3Input, TTradeResultFrame3Output tTradeResultFrame3Output);
    void execute(Connection conn, TTradeResultFrame4Input tTradeResultFrame4Input, TTradeResultFrame4Output tTradeResultFrame4Output);
    void execute(Connection conn, TTradeResultFrame5Input tTradeResultFrame5Input);
    void execute(Connection conn, TTradeResultFrame6Input tTradeResultFrame6Input, TTradeResultFrame6Output tTradeResultFrame6Output);

    void execute(Connection conn, TTradeStatusTxnInput tTradeStatusFrame1Input, TTradeStatusFrame1Output tTradeStatusFrame1Output);

    void execute(Connection conn, TTradeUpdateFrame1Input tTradeUpdateFrame1Input, TTradeUpdateFrame1Output tTradeUpdateFrame1Output);
    void execute(Connection conn, TTradeUpdateFrame2Input tTradeUpdateFrame2Input, TTradeUpdateFrame2Output tTradeUpdateFrame2Output);
    void execute(Connection conn, TTradeUpdateFrame3Input tTradeUpdateFrame3Input, TTradeUpdateFrame3Output tTradeUpdateFrame3Output);

//    void reconnect(Connection conn);

    void rollback();

//    void setBrokerageHouse(CBrokerageHouse cBrokerageHouse);

    void setReadCommitted();
    void setReadUncommitted();
    void setRepeatableRead();
    void setSerializable();
}
