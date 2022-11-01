//package com.oltpbenchmark.benchmarks.tpce.dbinterface;
//
//import com.oltpbenchmark.api.SQLStmt;
//import com.oltpbenchmark.benchmarks.tpcc.TPCCConstants;
//import com.oltpbenchmark.benchmarks.tpcc.procedures.OrderStatus;
//import com.oltpbenchmark.benchmarks.tpce.emulator.TTradeRequest;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.TTradeStatusTxnInput;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.brokervolume.TBrokerVolumeFrame1Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.brokervolume.TBrokerVolumeTxnInput;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.cleanup.TTradeCleanupFrame1Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.cleanup.TTradeCleanupTxnInput;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition.TCustomerPositionFrame1Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition.TCustomerPositionFrame2Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.datamaintenance.TDataMaintenanceFrame1Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.datamaintenance.TDataMaintenanceTxnInput;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed.TMarketFeedFrame1Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.marketwatch.TMarketWatchFrame1Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.marketwatch.TMarketWatchTxnInput;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.securitydetail.TSecurityDetailFrame1Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.securitydetail.TSecurityDetailTxnInput;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup.TTradeLookupFrame1Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup.TTradeLookupFrame2Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup.TTradeLookupFrame3Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup.TTradeLookupFrame4Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder.TTradeOrderFrame1Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder.TTradeOrderFrame2Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder.TTradeOrderFrame3Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder.TTradeOrderFrame4Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult.*;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateFrame1Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateFrame2Input;
//import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateFrame3Input;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.TMarketWatchFrame1Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.TNews;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.brokervolume.TBrokerVolumeFrame1Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition.TCustomerPositionFrame1Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition.TCustomerPositionFrame2Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.marketfeed.TMarketFeedFrame1Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.securitydetail.TSecurityDetailFrame1Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup.TTradeLookupFrame1Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup.TTradeLookupFrame2Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup.TTradeLookupFrame3Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup.TTradeLookupFrame4Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderFrame1Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderFrame2Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderFrame3Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderFrame4Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.traderesult.*;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.tradestatus.TTradeStatusFrame1Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateFrame1Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateFrame2Output;
//import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateFrame3Output;
//import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;
//import com.oltpbenchmark.jdbc.AutoIncrementPreparedStatement;
//import com.oltpbenchmark.types.DatabaseType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.*;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//public class DBConnectionImpl implements DBConnection {
//
//    private static final Logger LOG = LoggerFactory.getLogger(DBConnectionImpl.class);
//    Connection conn = new Connection();
//
//    SQLStmt BrokerVolumeFrame1 = new SQLStmt("SELECT * FROM BrokerVolumeFrame1('{ ? }',' ? ')");
//
//    @Override
//    public ResultSet exec(Connection conn, PreparedStatement statement) throws SQLException {
//        try {
//            ResultSet rs = statement.executeQuery();
//            conn.commit();
//            return rs;
//        } catch (SQLException e) {
//            LOG.error(statement.toString() + e);
//            conn.rollback();
//            return null;
//        }
//    }
//
//    @Override
//    public void execute(Connection conn, TBrokerVolumeTxnInput tBrokerVolumeTxnInput, TBrokerVolumeFrame1Output tBrokerVolumeFrame1Output) {
//        int i_broker_name;
//        int i_list_len;
//        int i_volume;
//
//        try {
//            //start transaction
//            conn.setAutoCommit(false);
//            StringBuilder brokers = new StringBuilder();
//            brokers.append(tBrokerVolumeTxnInput.getBrokerList()[0]);
//            for (int i = 1; i < tBrokerVolumeTxnInput.getBrokerList().length; i++) {
//                brokers.append(", ");
//                brokers.append(tBrokerVolumeTxnInput.getBrokerList()[i]);
//            }
//            PreparedStatement brokerVolumePreparedStatement = this.getPreparedStatement(conn, BrokerVolumeFrame1);
//            brokerVolumePreparedStatement.setString(1, brokers.toString());
//            brokerVolumePreparedStatement.setString(2, tBrokerVolumeTxnInput.getSectorName());
//
//            ResultSet rs = exec(conn, brokerVolumePreparedStatement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for" + brokerVolumePreparedStatement.toString());
//            } else {
//                tBrokerVolumeFrame1Output.setList_len(rs.getInt("list_len"));
//            }
//
//        } catch (SQLException e) {
//            LOG.error("roll back exception" + e);
//        }
//
//    }
//
//    @Override
//    public void execute(Connection conn, TCustomerPositionFrame1Input tCustomerPositionFrame1Input, TCustomerPositionFrame1Output tCustomerPositionFrame1Output) {
//        try {
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM CustomerPositionFrame1( ? ,' ? ')");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tCustomerPositionFrame1Input.getCust_id());
//            statement.setString(2, tCustomerPositionFrame1Input.getTax_id());
//            ResultSet rs = exec(conn, statement);
//
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for" + statement.toString());
//            } else {
//                tCustomerPositionFrame1Output.setCust_id(rs.getLong("cust_id"));
//                tCustomerPositionFrame1Output.setAcct_len(rs.getInt("acct_len"));
//                tCustomerPositionFrame1Output.setC_ad_id(rs.getLong("c_ad_id"));
//                tCustomerPositionFrame1Output.setC_area_1(rs.getString("c_area_1"));
//                tCustomerPositionFrame1Output.setC_area_2(rs.getString("c_area_2"));
//                tCustomerPositionFrame1Output.setC_area_3(rs.getString("c_area_3"));
//
//                tCustomerPositionFrame1Output.setC_ctry_1(rs.getString("c_ctry_1"));
//                tCustomerPositionFrame1Output.setC_ctry_2(rs.getString("c_ctry_2"));
//                tCustomerPositionFrame1Output.setC_ctry_3(rs.getString("c_ctry_3"));
//
//                tCustomerPositionFrame1Output.setC_dob(rs.getObject("c_dob", TimestampType.class));
//                tCustomerPositionFrame1Output.setC_email_1(rs.getString("c_email_1"));
//                tCustomerPositionFrame1Output.setC_email_2(rs.getString("c_email_2"));
//                tCustomerPositionFrame1Output.setC_ext_1(rs.getString("c_ext_1"));
//                tCustomerPositionFrame1Output.setC_ext_2(rs.getString("c_ext_2"));
//                tCustomerPositionFrame1Output.setC_ext_3(rs.getString("c_ext_3"));
//                tCustomerPositionFrame1Output.setC_f_name(rs.getString("c_f_name"));
//                tCustomerPositionFrame1Output.setC_gndr(rs.getString("c_gndr"));
//
//                tCustomerPositionFrame1Output.setC_l_name(rs.getString("c_l_name"));
//                tCustomerPositionFrame1Output.setC_local_1(rs.getString("c_local_1"));
//                tCustomerPositionFrame1Output.setC_local_2(rs.getString("c_local_2"));
//                tCustomerPositionFrame1Output.setC_local_3(rs.getString("c_local_3"));
//
//                tCustomerPositionFrame1Output.setC_m_name(rs.getString("c_m_name"));
//                tCustomerPositionFrame1Output.setC_st_id(rs.getString("c_st_id"));
//                tCustomerPositionFrame1Output.setC_tier(rs.getObject("c_tier", char.class));
//
//                Array asset = rs.getArray("asset_total");
//                double[] asset_total = (double[]) asset.getArray();
//                for (int i = 0; i < asset_total.length; i++) {
//                    tCustomerPositionFrame1Output.getAsset_total()[i] = asset_total[i];
//                }
//
//                Array acct = rs.getArray("acct_id");
//                long[] acct_id = (long[]) acct.getArray();
//                for (int i = 0; i < acct_id.length; i++) {
//                    tCustomerPositionFrame1Output.getAcct_id()[i] = acct_id[i];
//                }
//
//                Array cast = rs.getArray("cash_bal");
//                double[] cash_bal = (double[]) cast.getArray();
//                for (int i = 0; i < cash_bal.length; i++) {
//                    tCustomerPositionFrame1Output.getCash_bal()[i] = cash_bal[i];
//                }
//            }
//        } catch (SQLException e) {
//
//        }
//    }
//
//    @Override
//    public void execute(Connection conn, TCustomerPositionFrame2Input tCustomerPositionFrame2Input, TCustomerPositionFrame2Output tCustomerPositionFrame2Output) {
//        int i_hist_dts;
//        int i_hist_len;
//        int i_qty;
//        int i_symbol;
//        int i_trade_id;
//        int i_trade_status;
//
//        try {
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM CustomerPositionFrame2(\" ? \")");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tCustomerPositionFrame2Input.getAcct_id());
//            ResultSet rs = exec(conn, statement);
//
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for" + sqlStmt.toString());
//            } else {
//                tCustomerPositionFrame2Output.setHist_len(rs.getInt("hist_len"));
//                Array hist = rs.getArray("hist_dts");
//                TimestampType[] hist_dts = (TimestampType[]) hist.getArray();
//                for (int i = 0; i < hist_dts.length; i++) {
//                    tCustomerPositionFrame2Output.getHist_dts()[i] = hist_dts[i];
//                }
//
//                Array trade = rs.getArray("trade_id");
//                long[] trade_id = (long[]) trade.getArray();
//                for (int i = 0; i < trade_id.length; i++) {
//                    tCustomerPositionFrame2Output.getTrade_id()[i] = trade_id[i];
//                }
//
//                Array q = rs.getArray("qty");
//                int[] qty = (int[]) q.getArray();
//                for (int i = 0; i < qty.length; i++) {
//                    tCustomerPositionFrame2Output.getQty()[i] = qty[i];
//                }
//
//                Array sym = rs.getArray("symbol");
//                String[] symbol = (String[]) sym.getArray();
//                for (int i = 0; i < symbol.length; i++) {
//                    tCustomerPositionFrame2Output.getSymbol()[i] = symbol[i];
//                }
//
//                Array trade_s = rs.getArray("trade_status");
//                String[] trade_status = (String[]) trade_s.getArray();
//                for (int i = 0; i < trade_status.length; i++) {
//                    tCustomerPositionFrame2Output.getTrade_status()[i] = trade_status[i];
//                }
//
//            }
//        } catch (SQLException e) {
//
//        }
//
//    }
//
//    @Override
//    public void execute(Connection conn, TDataMaintenanceTxnInput tDataMaintenanceTxnInput) {
//        try {
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM DataMaintenanceFrame1( ? , ?, ? , ? , ? , ?, ? , ? )");
//
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tDataMaintenanceTxnInput.getAcctId());
//            statement.setLong(2, tDataMaintenanceTxnInput.getCId());
//            statement.setLong(3, tDataMaintenanceTxnInput.getCoId());
//            statement.setInt(4, tDataMaintenanceTxnInput.getDayOfMonth());
//            statement.setString(5, tDataMaintenanceTxnInput.getSymbol());
//            statement.setString(6, tDataMaintenanceTxnInput.getTableName());
//            statement.setString(7, tDataMaintenanceTxnInput.getTxId());
//            statement.setInt(8, tDataMaintenanceTxnInput.getVolIncr());
//            ResultSet rs = exec(conn, statement);
//        } catch (SQLException e) {
//
//        }
//    }
//
//    @Override
//    public void execute(TMarketFeedFrame1Input tMarketFeedFrame1Input, TMarketFeedFrame1Output tMarketFeedFrame1Output, CSendToMarketInterface cSendToMarketInterface) {
//        String osSymbol = "", osPrice = "", osQty = "";
//        for( int i = 0; i < (tMarketFeedFrame1Input.getEntries().length); i++){
//            if(i == 0){
//                osSymbol += "\"" + tMarketFeedFrame1Input.getEntries()[i].getSymbol();
//                osPrice += tMarketFeedFrame1Input.getEntries()[i].getPrice_quote();
//                osQty += tMarketFeedFrame1Input.getEntries()[i].getTrade_qty();
//            }else {
//                osSymbol << "\",\"" << pIn->Entries[i].symbol;
//                osSymbol += "\",\"" + tMarketFeedFrame1Input.getEntries()[i].getSymbol();
//                osPrice += "," + tMarketFeedFrame1Input.getEntries()[i].getPrice_quote();
//                osQty += "," + tMarketFeedFrame1Input.getEntries()[i].getTrade_qty();
//            }
//        }
//        osSymbol += "\"";
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM MarketFeedFrame1('{ ? } ',' ? ','{ ? }', '{ ? }',' ? ',' ? ',' ? ')");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setString(1, osPrice);
//            statement.setString(2, tMarketFeedFrame1Input.getStatusAndTradeType().getStatus_submitted());
//            statement.setString(3, osSymbol);
//            statement.setString(4, osQty);
//            statement.setString(5, tMarketFeedFrame1Input.getStatusAndTradeType().getType_limit_buy());
//            statement.setString(6, tMarketFeedFrame1Input.getStatusAndTradeType().getType_limit_sell());
//            statement.setString(7, tMarketFeedFrame1Input.getStatusAndTradeType().getType_stop_loss());
//            ResultSet rs = exec(conn, statement);
//            tMarketFeedFrame1Output.setNum_updated(rs.getInt("num_updated"));
//            tMarketFeedFrame1Output.setSend_len(rs.getInt("send_len"));
//
//            TTradeRequest m_TriggeredLimitOrders = new TTradeRequest();
//
//            Array symbol = rs.getArray("symbol");
//            String[] sym = (String[]) symbol.getArray();
//            Array trade_id = rs.getArray("trade_id");
//            long[] trade = (long[]) trade_id.getArray();
//            Array price_quote = rs.getArray("price_quote");
//            double[] price = (double[]) price_quote.getArray();
//            Array trade_qty = rs.getArray("trade_qty");
//            int[] qty = (int[]) trade_qty.getArray();
//            Array trade_type = rs.getArray("trade_type");
//            String[] type = (String[]) trade_type.getArray();
//            for (int i = 0; i < sym.length; i++) {
//                TTradeRequest request = new TTradeRequest();
//                request.setSymbol(sym[i]);
//                request.setTrade_id(trade[i]);
//                request.setPrice_quote(price[i]);
//                request.setTrade_qty(qty[i]);
//                request.setTrade_type_id(type[i]);
//                //TODO send to market
//            }
//
//        }catch (SQLException e){
//
//        }
//
//
//    }
//
////    @Override
////    public void execute(TMarketFeedFrame1Input tMarketFeedFrame1Input, TMarketFeedFrame1Output tMarketFeedFrame1Output, CSendToMarketInterface cSendToMarketInterface) {
////
////    }
//
//    @Override
//    public void execute(Connection conn, TMarketWatchTxnInput tMarketWatchFrame1Input, TMarketWatchFrame1Output tMarketWatchFrame1Output) {
//        try {
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM MarketWatchFrame1(? , ?, ? , ? , ? , ?, ? , ? )");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tMarketWatchFrame1Input.getAcctId());
//            statement.setLong(2, tMarketWatchFrame1Input.getCId());
//            statement.setLong(3, tMarketWatchFrame1Input.getEndingCoId());
//            statement.setString(4, tMarketWatchFrame1Input.getIndustryName());
//            statement.setInt(5, tMarketWatchFrame1Input.getStartDay().getM_date().getYear());
//            statement.setInt(6, tMarketWatchFrame1Input.getStartDay().getM_date().getMonth());
//            statement.setInt(7, tMarketWatchFrame1Input.getStartDay().getM_date().getDay());
//            statement.setLong(8, tMarketWatchFrame1Input.getStartingCoId());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for" + sqlStmt.toString());
//            } else {
//                tMarketWatchFrame1Output.setPct_change(rs.getDouble("pct_change"));
//            }
//        } catch (SQLException e) {
//
//        }
//    }
//
//    @Override
//    public void execute(Connection conn, TSecurityDetailTxnInput tSecurityDetailFrame1Input, TSecurityDetailFrame1Output tSecurityDetailFrame1Output) {
//        try {
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM SecurityDetailFrame1( ?, ?, ?, ? )");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setString(1, tSecurityDetailFrame1Input.getAccessLobFlag() == 0 ? "false" : "true");
//            statement.setInt(2, tSecurityDetailFrame1Input.getMaxRowsToReturn());
//            statement.setString(3, tSecurityDetailFrame1Input.getStartDay().getM_date().getYear() + "-"
//                + tSecurityDetailFrame1Input.getStartDay().getM_date().getMonth() + "-" +
//                tSecurityDetailFrame1Input.getStartDay().getM_date().getDay());
//            statement.setString(4, tSecurityDetailFrame1Input.getSymbol());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for" + sqlStmt.toString());
//            } else {
//                tSecurityDetailFrame1Output.setS52_wk_high(rs.getDouble("x52_wk_high"));
//                tSecurityDetailFrame1Output.setS52_wk_low(rs.getDouble("x52_wk_low"));
//                tSecurityDetailFrame1Output.setCeo_name(rs.getString("ceo_name"));
//                tSecurityDetailFrame1Output.setCo_ad_cty(rs.getString("co_ad_ctry"));
//                tSecurityDetailFrame1Output.setCo_ad_div(rs.getString("co_ad_div"));
//                tSecurityDetailFrame1Output.setCo_ad_line1(rs.getString("co_ad_line1"));
//                tSecurityDetailFrame1Output.setCo_ad_line2(rs.getString("co_ad_line2"));
//                tSecurityDetailFrame1Output.setCo_ad_town(rs.getString("co_ad_town"));
//                tSecurityDetailFrame1Output.setCo_ad_zip(rs.getString("co_ad_zip"));
//                tSecurityDetailFrame1Output.setCo_desc(rs.getString("co_desc"));
//                tSecurityDetailFrame1Output.setCo_name(rs.getString("co_name"));
//                tSecurityDetailFrame1Output.setCo_st_id(rs.getString("co_st_id"));
//                tSecurityDetailFrame1Output.setDay_len(rs.getInt("day_len"));
//                tSecurityDetailFrame1Output.setDivid(rs.getDouble("divid"));
//                tSecurityDetailFrame1Output.setEx_ad_cty(rs.getString("ex_ad_ctry"));
//                tSecurityDetailFrame1Output.setEx_ad_div(rs.getString("ex_ad_div"));
//                tSecurityDetailFrame1Output.setEx_ad_line1(rs.getString("ex_ad_line1"));
//                tSecurityDetailFrame1Output.setEx_ad_line2(rs.getString("ex_ad_line2"));
//                tSecurityDetailFrame1Output.setEx_ad_town(rs.getString("ex_ad_town"));
//                tSecurityDetailFrame1Output.setEx_ad_zip(rs.getString("ex_ad_zip"));
//                tSecurityDetailFrame1Output.setEx_close(rs.getInt("ex_close"));
//                tSecurityDetailFrame1Output.setEx_desc(rs.getString("ex_desc"));
//                tSecurityDetailFrame1Output.setEx_name(rs.getString("ex_name"));
//                tSecurityDetailFrame1Output.setEx_num_symb(rs.getInt("ex_num_symb"));
//                tSecurityDetailFrame1Output.setEx_open(rs.getInt("ex_open"));
//                tSecurityDetailFrame1Output.setFin_len(rs.getInt("fin_len"));
//                tSecurityDetailFrame1Output.setLast_open(rs.getDouble("last_open"));
//                tSecurityDetailFrame1Output.setLast_price(rs.getDouble("last_price"));
//                tSecurityDetailFrame1Output.setLast_vol(rs.getLong("last_vol"));
//                tSecurityDetailFrame1Output.setNews_len(rs.getInt("news_len"));
//                tSecurityDetailFrame1Output.setNum_out(rs.getInt("num_out"));
//                tSecurityDetailFrame1Output.setPe_ratio(rs.getDouble("pe_ratio"));
//                tSecurityDetailFrame1Output.setSp_rate(rs.getString("sp_rate"));
//                tSecurityDetailFrame1Output.setS_name(rs.getString("s_name"));
//                tSecurityDetailFrame1Output.setYield(rs.getDouble("yield"));
//
//                //TODO tnews
//                tSecurityDetailFrame1Output.setNews(rs.getString("news"));
//
//                //TODO TDailyHistory
//                tSecurityDetailFrame1Output.setDay(rs.getString("day"));
//
//                //TODO TFinInfo
//                tSecurityDetailFrame1Output.setFin(rs.getString("fin"));
//
//                Array cp_in_name = rs.getArray("cp_in_name");
//                String[] cp_str = (String[]) cp_in_name.getArray();
//                for(int i = 0; i < cp_str.length; i++) tSecurityDetailFrame1Output.getCp_in_name()[i] = cp_str[i];
//
//                Array cp_co_name = rs.getArray("cp_in_name");
//                String[] co_str = (String[]) cp_co_name.getArray();
//                for(int i = 0; i < co_str.length; i++) tSecurityDetailFrame1Output.getCp_co_name()[i] = co_str[i];
//
//                String ex_date = rs.getString("ex_date");
//                String[] ex_date_str = ex_date.split("-");
//                if(ex_date_str.length == 3){
//                    tSecurityDetailFrame1Output.setEx_date(
//                        new TimestampType(new GregorianCalendar(Integer.parseInt(ex_date_str[0]), Integer.parseInt(ex_date_str[1]), Integer.parseInt(ex_date_str[2]), 0, 0, 0).getTime()));
//                }
//
//
//                String open_date = rs.getString("open_date");
//                String[] open_date_str = open_date.split("-");
//                if(open_date_str.length == 3){
//                    tSecurityDetailFrame1Output.setOpen_date(
//                        new TimestampType(new GregorianCalendar(Integer.parseInt(open_date_str[0]), Integer.parseInt(open_date_str[1]), Integer.parseInt(open_date_str[2]), 0, 0, 0).getTime()));
//                }
//
//                String start_date = rs.getString("start_date");
//                String[] start_date_str = start_date.split("-");
//                if(start_date_str.length == 3){
//                    tSecurityDetailFrame1Output.setStart_date(
//                        new TimestampType(new GregorianCalendar(Integer.parseInt(start_date_str[0]), Integer.parseInt(start_date_str[1]), Integer.parseInt(start_date_str[2]), 0, 0, 0).getTime()));
//                }
//
//                String x52_wk_high_date = rs.getString("x52_wk_high_date");
//                String[] str = x52_wk_high_date.split("-");
//                if(str.length == 3){
//                    tSecurityDetailFrame1Output.setS52_wk_high_date(
//                        new TimestampType(new GregorianCalendar(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]), 0, 0, 0).getTime()));
//                }
//
//                String x52_wk_low_date = rs.getString("x52_wk_low_date");
//                String[] str2 = x52_wk_low_date.split("-");
//                if(str.length == 3){
//                    tSecurityDetailFrame1Output.setS52_wk_low_date(
//                        new TimestampType(new GregorianCalendar(Integer.parseInt(str2[0]), Integer.parseInt(str2[1]), Integer.parseInt(str2[2]), 0, 0, 0).getTime()));
//                }
//
//            }
//
//
//        } catch (Exception e) {
//
//        }
//
//    }
//
//    @Override
//    public void execute(Connection conn, TTradeCleanupTxnInput tTradeCleanupFrame1Input) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeCleanupFrame1(?, ?, ?, ?)");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setString(1, tTradeCleanupFrame1Input.getSt_canceled_id());
//            statement.setString(2, tTradeCleanupFrame1Input.getSt_pending_id());
//            statement.setString(3, tTradeCleanupFrame1Input.getSt_submitted_id());
//            statement.setLong(4, tTradeCleanupFrame1Input.getStart_trade_id());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(Connection conn, TTradeLookupFrame1Input tTradeLookupFrame1Input, TTradeLookupFrame1Output tTradeLookupFrame1Output) {
//        try{
//            conn.setAutoCommit(false);
//            StringBuilder trades = new StringBuilder();
//            trades.append(tTradeLookupFrame1Input.getTrade_id()[0]);
//            for(int i = 1; i < tTradeLookupFrame1Input.getMax_trades(); i++){
//                trades.append(",");
//                trades.append(tTradeLookupFrame1Input.getTrade_id()[i]);
//            }
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeLookupFrame1( ? , '{ ? }'");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setInt(1, tTradeLookupFrame1Input.getMax_trades());
//            statement.setString(2, trades.toString());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//                tTradeLookupFrame1Output.setTrade_info();
//                tTradeLookupFrame1Output.setNum_found();
//            }
//
//            Array bid_price = rs.getArray("bid_price");
//            double[] price = (double[]) bid_price.getArray();
//            for (int i = 0; i < price.length; i++) {
//                tTradeLookupFrame1Output.getTrade_info()[i].setBid_price(price[i]);
//            }
//
//            Array cash_transaction_amount = rs.getArray("cash_transaction_amount");
//            double[] transaction_amount = (double[]) cash_transaction_amount.getArray();
//            for(int i = 0; i < transaction_amount.length; i++){
//                tTradeLookupFrame1Output.getTrade_info()[i].setCash_transaction_amount(transaction_amount[i]);
//            }
//
//            Array cash_transaction_dts = rs.getArray("cash_transaction_dts");
//            String[] transaction_dts = (String[]) cash_transaction_dts.getArray();
//            for(int i = 0; i < transaction_dts.length; i++){
//                //%hd-%hd-%hd %hd:%hd:%hd
//                tTradeLookupFrame1Output.getTrade_info()[i].getCash_transaction_dts();
//            }
//
//
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(Connection conn, TTradeLookupFrame2Input tTradeLookupFrame2Input, TTradeLookupFrame2Output tTradeLookupFrame2Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeLookupFrame2(?,'?-?-? ?:?:?',?,'?-?-? ?:?:?')");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tTradeLookupFrame2Input.getAcct_id());
//            statement.setInt(2, tTradeLookupFrame2Input.getEnd_trade_dts().getM_date().getYear());
//            statement.setInt(3, tTradeLookupFrame2Input.getEnd_trade_dts().getM_date().getMonth());
//            statement.setInt(4, tTradeLookupFrame2Input.getEnd_trade_dts().getM_date().getDay());
//            statement.setInt(5, tTradeLookupFrame2Input.getEnd_trade_dts().getM_date().getHours());
//            statement.setInt(6, tTradeLookupFrame2Input.getEnd_trade_dts().getM_date().getMinutes());
//            statement.setInt(7, tTradeLookupFrame2Input.getEnd_trade_dts().getM_date().getSeconds());
//            statement.setInt(8, tTradeLookupFrame2Input.getMax_trades());
//            statement.setInt(9, tTradeLookupFrame2Input.getStart_trade_dts().getM_date().getYear());
//            statement.setInt(10, tTradeLookupFrame2Input.getStart_trade_dts().getM_date().getMonth());
//            statement.setInt(11, tTradeLookupFrame2Input.getStart_trade_dts().getM_date().getDay());
//            statement.setInt(12, tTradeLookupFrame2Input.getStart_trade_dts().getM_date().getHours());
//            statement.setInt(13, tTradeLookupFrame2Input.getStart_trade_dts().getM_date().getMinutes());
//            statement.setInt(14, tTradeLookupFrame2Input.getStart_trade_dts().getM_date().getSeconds());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(Connection conn, TTradeLookupFrame3Input tTradeLookupFrame3Input, TTradeLookupFrame3Output tTradeLookupFrame3Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeLookupFrame3('?-?-? ?:?:?',?,?,'?-?-? ?:?:?','?')");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setInt(1, tTradeLookupFrame3Input.getEnd_trade_dts().getM_date().getYear());
//            statement.setInt(2, tTradeLookupFrame3Input.getEnd_trade_dts().getM_date().getMonth());
//            statement.setInt(3, tTradeLookupFrame3Input.getEnd_trade_dts().getM_date().getDay());
//            statement.setInt(4, tTradeLookupFrame3Input.getEnd_trade_dts().getM_date().getHours());
//            statement.setInt(5, tTradeLookupFrame3Input.getEnd_trade_dts().getM_date().getMinutes());
//            statement.setInt(6, tTradeLookupFrame3Input.getEnd_trade_dts().getM_date().getSeconds());
//            statement.setLong(7, tTradeLookupFrame3Input.getMax_acct_id());
//            statement.setInt(8, tTradeLookupFrame3Input.getMax_trades());
//            statement.setInt(9, tTradeLookupFrame3Input.getStart_trade_dts().getM_date().getYear());
//            statement.setInt(10, tTradeLookupFrame3Input.getStart_trade_dts().getM_date().getMonth());
//            statement.setInt(11, tTradeLookupFrame3Input.getStart_trade_dts().getM_date().getDay());
//            statement.setInt(12, tTradeLookupFrame3Input.getStart_trade_dts().getM_date().getHours());
//            statement.setInt(13, tTradeLookupFrame3Input.getStart_trade_dts().getM_date().getMinutes());
//            statement.setInt(14, tTradeLookupFrame3Input.getStart_trade_dts().getM_date().getSeconds());
//            statement.setString(15, tTradeLookupFrame3Input.getSymbol());
//
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(Connection conn, TTradeLookupFrame4Input tTradeLookupFrame4Input, TTradeLookupFrame4Output tTradeLookupFrame4Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeLookupFrame4(?,'?-?-? ?:?:?')");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tTradeLookupFrame4Input.getAcct_id());
//            statement.setInt(2, tTradeLookupFrame4Input.getTrade_dts().getM_date().getYear());
//            statement.setInt(3, tTradeLookupFrame4Input.getTrade_dts().getM_date().getMonth());
//            statement.setInt(4, tTradeLookupFrame4Input.getTrade_dts().getM_date().getDay());
//            statement.setInt(5, tTradeLookupFrame4Input.getTrade_dts().getM_date().getHours());
//            statement.setInt(6, tTradeLookupFrame4Input.getTrade_dts().getM_date().getMinutes());
//            statement.setInt(7, tTradeLookupFrame4Input.getTrade_dts().getM_date().getSeconds());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeOrderFrame1Input tTradeOrderFrame1Input, TTradeOrderFrame1Output tTradeOrderFrame1Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeOrderFrame1(?)");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tTradeOrderFrame1Input.getAcct_id());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeOrderFrame2Input tTradeOrderFrame2Input, TTradeOrderFrame2Output tTradeOrderFrame2Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeOrderFrame2(?,?,?,'?')");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tTradeOrderFrame2Input.getAcct_id());
//            statement.setString(2, tTradeOrderFrame2Input.getExec_f_name());
//            statement.setString(3, tTradeOrderFrame2Input.getExec_l_name());
//            statement.setString(4, tTradeOrderFrame2Input.getExec_tax_id());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeOrderFrame3Input tTradeOrderFrame3Input, TTradeOrderFrame3Output tTradeOrderFrame3Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeOrderFrame3(?,?,?::SMALLINT,?::SMALLINT,'?','?','?',?::SMALLINT,?,'?',?::SMALLINT,?,?,'?')");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tTradeOrderFrame3Input.getAcct_id());
//            statement.setLong(2, tTradeOrderFrame3Input.getCust_id());
//            statement.setInt(3, tTradeOrderFrame3Input.getCust_tier());
//            statement.setInt(4, tTradeOrderFrame3Input.getIs_lifo());
//            statement.setString(5, tTradeOrderFrame3Input.getIssue());
//            statement.setString(6, tTradeOrderFrame3Input.getSt_pending_id());
//            statement.setString(7, tTradeOrderFrame3Input.getSt_submitted_id());
//            statement.setInt(8, tTradeOrderFrame3Input.getTax_status());
//            statement.setInt(9, tTradeOrderFrame3Input.getTrade_qty());
//            statement.setString(10, tTradeOrderFrame3Input.getTrade_type_id());
//            statement.setInt(11, tTradeOrderFrame3Input.getType_is_margin());
//            statement.setString(12, tTradeOrderFrame3Input.getCo_name());
//            statement.setDouble(13, tTradeOrderFrame3Input.getRequested_price());
//            statement.setString(14, tTradeOrderFrame3Input.getSymbol());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeOrderFrame4Input tTradeOrderFrame4Input, TTradeOrderFrame4Output tTradeOrderFrame4Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeOrderFrame4(?,?,?,?,?,?::SMALLINT,?::SMALLINT,?,'?','?',?,'?',?::SMALLINT,)");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tTradeOrderFrame4Input.getAcct_id());
//            statement.setLong(2, tTradeOrderFrame4Input.getBroker_id());
//            statement.setDouble(3, tTradeOrderFrame4Input.getCharge_amount());
//            statement.setDouble(4, tTradeOrderFrame4Input.getComm_amount());
//            statement.setString(5, tTradeOrderFrame4Input.getExec_name());
//            statement.setInt(6, tTradeOrderFrame4Input.getIs_cash());
//            statement.setInt(7, tTradeOrderFrame4Input.getIs_lifo());
//            statement.setDouble(8, tTradeOrderFrame4Input.getRequested_price());
//            statement.setString(9, tTradeOrderFrame4Input.getStatus_id());
//            statement.setString(10, tTradeOrderFrame4Input.getSymbol());
//            statement.setInt(11, tTradeOrderFrame4Input.getTrade_qty());
//            statement.setString(12, tTradeOrderFrame4Input.getTrade_type_id());
//            statement.setInt(13, tTradeOrderFrame4Input.getType_is_market());
//
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//                tTradeOrderFrame4Output.setTrade_id(rs.getLong("trade_id"));
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeResultFrame1Input tTradeResultFrame1Input, TTradeResultFrame1Output tTradeResultFrame1Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeResultFrame1(?)");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tTradeResultFrame1Input.getTrade_id());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeResultFrame2Input tTradeResultFrame2Input, TTradeResultFrame2Output tTradeResultFrame2Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeResultFrame2(?,?,?::SMALLINT,',?',?,?,?,?::SMALLINT)");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tTradeResultFrame2Input.getAcct_id());
//            statement.setInt(2, tTradeResultFrame2Input.getHs_qty());
//            statement.setInt(3, tTradeResultFrame2Input.getIs_lifo());
//            statement.setString(4, tTradeResultFrame2Input.getSymbol());
//            statement.setLong(5, tTradeResultFrame2Input.getTrade_id());
//            statement.setDouble(6, tTradeResultFrame2Input.getTrade_price());
//            statement.setInt(7, tTradeResultFrame2Input.getTrade_qty());
//            statement.setInt(8, tTradeResultFrame2Input.getType_is_sell());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeResultFrame3Input tTradeResultFrame3Input, TTradeResultFrame3Output tTradeResultFrame3Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeResultFrame3(?,?,?,?)");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setDouble(1, tTradeResultFrame3Input.getBuy_value());
//            statement.setLong(2, tTradeResultFrame3Input.getCust_id());
//            statement.setDouble(3, tTradeResultFrame3Input.getSell_value());
//            statement.setLong(4, tTradeResultFrame3Input.getTrade_id());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeResultFrame4Input tTradeResultFrame4Input, TTradeResultFrame4Output tTradeResultFrame4Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeResultFrame4(?,'?',?,'?')");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tTradeResultFrame4Input.getCust_id());
//            statement.setString(2, tTradeResultFrame4Input.getSymbol());
//            statement.setInt(3, tTradeResultFrame4Input.getTrade_qty());
//            statement.setString(4, tTradeResultFrame4Input.getType_id());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeResultFrame5Input tTradeResultFrame5Input) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeResultFrame5(?,?,'?','?-?-? ?:?:?',?,?)");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tTradeResultFrame5Input.getBroker_id());
//            statement.setDouble(2, tTradeResultFrame5Input.getComm_amount());
//            statement.setString(3, tTradeResultFrame5Input.getSt_completed_id());
//            statement.setInt(4, tTradeResultFrame5Input.getTrade_dts().getM_date().getYear());
//            statement.setInt(5, tTradeResultFrame5Input.getTrade_dts().getM_date().getMonth());
//            statement.setInt(6, tTradeResultFrame5Input.getTrade_dts().getM_date().getDay());
//            statement.setInt(7, tTradeResultFrame5Input.getTrade_dts().getM_date().getHours());
//            statement.setInt(8, tTradeResultFrame5Input.getTrade_dts().getM_date().getMinutes());
//            statement.setInt(9, tTradeResultFrame5Input.getTrade_dts().getM_date().getSeconds());
//            statement.setLong(10, tTradeResultFrame5Input.getTrade_id());
//            statement.setDouble(11, tTradeResultFrame5Input.getTrade_price());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeResultFrame6Input tTradeResultFrame6Input, TTradeResultFrame6Output tTradeResultFrame6Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeResultFrame6(?,'?-?-? ?:?:?',?,?,'?-?-? ?:?:?',?,?::SMALLINT,?,'?')");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tTradeResultFrame6Input.getAcct_id());
//            statement.setInt(2, tTradeResultFrame6Input.getDue_date().getM_date().getYear());
//            statement.setInt(3, tTradeResultFrame6Input.getDue_date().getM_date().getMonth());
//            statement.setInt(4, tTradeResultFrame6Input.getDue_date().getM_date().getDay());
//            statement.setInt(5, tTradeResultFrame6Input.getDue_date().getM_date().getHours());
//            statement.setInt(6, tTradeResultFrame6Input.getDue_date().getM_date().getMinutes());
//            statement.setInt(7, tTradeResultFrame6Input.getDue_date().getM_date().getSeconds());
//            statement.setString(8, tTradeResultFrame6Input.getS_name());
//            statement.setDouble(9, tTradeResultFrame6Input.getSe_amount());
//            statement.setInt(10, tTradeResultFrame6Input.getDue_date().getM_date().getYear());
//            statement.setInt(11, tTradeResultFrame6Input.getDue_date().getM_date().getMonth());
//            statement.setInt(12, tTradeResultFrame6Input.getDue_date().getM_date().getDay());
//            statement.setInt(13, tTradeResultFrame6Input.getDue_date().getM_date().getHours());
//            statement.setInt(14, tTradeResultFrame6Input.getDue_date().getM_date().getMinutes());
//            statement.setInt(15, tTradeResultFrame6Input.getDue_date().getM_date().getSeconds());
//            statement.setLong(16, tTradeResultFrame6Input.getTrade_id());
//            statement.setInt(17, tTradeResultFrame6Input.getTrade_is_cash());
//            statement.setInt(18, tTradeResultFrame6Input.getTrade_qty());
//            statement.setString(19, tTradeResultFrame6Input.getType_name());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//                tTradeResultFrame6Output.setAcct_bal(rs.getDouble("acct_bal"));
//            }
//
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeStatusTxnInput tTradeStatusFrame1Input, TTradeStatusFrame1Output tTradeStatusFrame1Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeStatusFrame1(?)");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tTradeStatusFrame1Input.getAcctId());
//
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeUpdateFrame1Input tTradeUpdateFrame1Input, TTradeUpdateFrame1Output tTradeUpdateFrame1Output) {
//        try{
//            conn.setAutoCommit(false);
//            StringBuilder osTrades = new StringBuilder();
//            osTrades.append(tTradeUpdateFrame1Input.getTrade_id()[0]);
//            for(int i = 1; i < tTradeUpdateFrame1Input.getTrade_id().length; i++){
//                osTrades.append(",");
//                osTrades.append(tTradeUpdateFrame1Input.getTrade_id()[i]);
//            }
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeUpdateFrame1(?,?,'{?}')");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setInt(1, tTradeUpdateFrame1Input.getMax_trades());
//            statement.setInt(2, tTradeUpdateFrame1Input.getMax_updates());
//            statement.setString(3, osTrades.toString());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeUpdateFrame2Input tTradeUpdateFrame2Input, TTradeUpdateFrame2Output tTradeUpdateFrame2Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeLookupFrame2(?,'?-?-? ?:?:?',?,?,'?-?-? ?:?:?')");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setLong(1, tTradeUpdateFrame2Input.getAcct_id());
//            statement.setInt(2, tTradeUpdateFrame2Input.getEnd_trade_dts().getM_date().getYear());
//            statement.setInt(3, tTradeUpdateFrame2Input.getEnd_trade_dts().getM_date().getMonth());
//            statement.setInt(4, tTradeUpdateFrame2Input.getEnd_trade_dts().getM_date().getDay());
//            statement.setInt(5, tTradeUpdateFrame2Input.getEnd_trade_dts().getM_date().getHours());
//            statement.setInt(6, tTradeUpdateFrame2Input.getEnd_trade_dts().getM_date().getMinutes());
//            statement.setInt(7, tTradeUpdateFrame2Input.getEnd_trade_dts().getM_date().getSeconds());
//            statement.setInt(8, tTradeUpdateFrame2Input.getMax_trades());
//            statement.setInt(9, tTradeUpdateFrame2Input.getMax_updates());
//            statement.setLong(10, tTradeUpdateFrame2Input.getAcct_id());
//            statement.setInt(11, tTradeUpdateFrame2Input.getStart_trade_dts().getM_date().getYear());
//            statement.setInt(12, tTradeUpdateFrame2Input.getStart_trade_dts().getM_date().getMonth());
//            statement.setInt(13, tTradeUpdateFrame2Input.getStart_trade_dts().getM_date().getDay());
//            statement.setInt(14, tTradeUpdateFrame2Input.getStart_trade_dts().getM_date().getHours());
//            statement.setInt(15, tTradeUpdateFrame2Input.getStart_trade_dts().getM_date().getMinutes());
//            statement.setInt(16, tTradeUpdateFrame2Input.getStart_trade_dts().getM_date().getSeconds());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else{
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void execute(TTradeUpdateFrame3Input tTradeUpdateFrame3Input, TTradeUpdateFrame3Output tTradeUpdateFrame3Output) {
//        try{
//            conn.setAutoCommit(false);
//            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeLookupFrame2('?-?-? ?:?:?',?,?,?,'?-?-? ?:?:?','?')");
//            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
//            statement.setInt(1, tTradeUpdateFrame3Input.getEnd_trade_dts().getM_date().getYear());
//            statement.setInt(2, tTradeUpdateFrame3Input.getEnd_trade_dts().getM_date().getMonth());
//            statement.setInt(3, tTradeUpdateFrame3Input.getEnd_trade_dts().getM_date().getDay());
//            statement.setInt(4, tTradeUpdateFrame3Input.getEnd_trade_dts().getM_date().getHours());
//            statement.setInt(5, tTradeUpdateFrame3Input.getEnd_trade_dts().getM_date().getMinutes());
//            statement.setInt(6, tTradeUpdateFrame3Input.getEnd_trade_dts().getM_date().getSeconds());
//            statement.setLong(7, tTradeUpdateFrame3Input.getMax_acct_id());
//            statement.setInt(8, tTradeUpdateFrame3Input.getMax_trades());
//            statement.setInt(9, tTradeUpdateFrame3Input.getMax_updates());
//            statement.setInt(10, tTradeUpdateFrame3Input.getStart_trade_dts().getM_date().getYear());
//            statement.setInt(11, tTradeUpdateFrame3Input.getStart_trade_dts().getM_date().getMonth());
//            statement.setInt(12, tTradeUpdateFrame3Input.getStart_trade_dts().getM_date().getDay());
//            statement.setInt(13, tTradeUpdateFrame3Input.getStart_trade_dts().getM_date().getHours());
//            statement.setInt(14, tTradeUpdateFrame3Input.getStart_trade_dts().getM_date().getMinutes());
//            statement.setInt(15, tTradeUpdateFrame3Input.getStart_trade_dts().getM_date().getSeconds());
//            statement.setString(16, tTradeUpdateFrame3Input.getSymbol());
//            ResultSet rs = exec(conn, statement);
//            if (rs == null || !rs.next()) {
//                throw new SQLException("no result for cleanup" + sqlStmt.toString());
//            }else {
//                tTradeUpdateFrame3Output.setNum_found(rs.getInt("num_found"));
//                tTradeUpdateFrame3Output.setNum_updated(rs.getInt("num_updated"));
//                Array acc_id = rs.getArray("acct_id");
//                long[] acc = (long[]) acc_id.getArray();
//                for(int i = 0; i < acc.length; i++){
//                    tTradeUpdateFrame3Output.getTrade_info()[i].setAcct_id(acc[i]);
//                }
//                Array cash_transaction_amount = rs.getArray("cash_transaction_amount");
//                double[] transaction_amount = (double[])cash_transaction_amount.getArray();
//                for(int i = 0; i < transaction_amount.length; i++){
//                    tTradeUpdateFrame3Output.getTrade_info()[i].setCash_transaction_amount(transaction_amount[i]);
//                }
//
//
//            }
//        }catch (SQLException e){
//
//        }
//    }
//
//    @Override
//    public void reconnect() {
//        conn.disconnect();
//        conn.connect();
//    }
//
//    @Override
//    public void rollback() {
//
//    }
//
//    @Override
//    public void setBrokerageHouse(CBrokerageHouse cBrokerageHouse) {
//
//    }
//
//    @Override
//    public void setReadCommitted() {
//
//    }
//
//    @Override
//    public void setReadUncommitted() {
//
//    }
//
//    @Override
//    public void setRepeatableRead() {
//
//    }
//
//    @Override
//    public void setSerializable() {
//
//    }
//
//    public final PreparedStatement getPreparedStatement(Connection conn, SQLStmt stmt, Object... params) throws SQLException {
//        PreparedStatement pStmt = this.getPreparedStatementReturnKeys(conn, stmt, null);
//        for (int i = 0; i < params.length; i++) {
//            pStmt.setObject(i + 1, params[i]);
//        }
//        return (pStmt);
//    }
//
//    /**
//     * Return a PreparedStatement for the given SQLStmt handle
//     * The underlying Procedure API will make sure that the proper SQL
//     * for the target DBMS is used for this SQLStmt.
//     *
//     * @param conn
//     * @param stmt
//     * @param is
//     * @return
//     * @throws SQLException
//     */
//    public final PreparedStatement getPreparedStatementReturnKeys(Connection conn, SQLStmt stmt, int[] is) throws SQLException {
//
//        PreparedStatement pStmt = null;
//
//        // HACK: If the target system is Postgres, wrap the PreparedStatement in a special
//        //       one that fakes the getGeneratedKeys().
//        if (is != null && (this.dbType == DatabaseType.POSTGRES || this.dbType == DatabaseType.COCKROACHDB)) {
//            pStmt = new AutoIncrementPreparedStatement(this.dbType, conn.prepareStatement(stmt.getSQL()));
//        }
//        // Everyone else can use the regular getGeneratedKeys() method
//        else if (is != null) {
//            pStmt = conn.prepareStatement(stmt.getSQL(), is);
//        }
//        // They don't care about keys
//        else {
//            pStmt = conn.prepareStatement(stmt.getSQL());
//        }
//
//
//        return (pStmt);
//    }
//
//
//}
