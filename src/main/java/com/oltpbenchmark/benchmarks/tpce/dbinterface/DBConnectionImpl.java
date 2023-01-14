package com.oltpbenchmark.benchmarks.tpce.dbinterface;

import com.oltpbenchmark.api.SQLStmt;
import com.oltpbenchmark.benchmarks.tpce.emulator.MEE;
import com.oltpbenchmark.benchmarks.tpce.emulator.TTradeRequest;
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
import com.oltpbenchmark.benchmarks.tpce.outputdo.*;
import com.oltpbenchmark.benchmarks.tpce.outputdo.brokervolume.TBrokerVolumeFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition.TCustomerPositionFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition.TCustomerPositionFrame2Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketfeed.TMarketFeedFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.securitydetail.TSecurityDetailFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup.*;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderFrame2Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderFrame3Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.TTradeOrderFrame4Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.traderesult.*;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradestatus.TTradeStatusFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateFrame2Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateFrame3Output;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.eMEETradeRequestAction.eMEEProcessOrder;
import static com.oltpbenchmark.benchmarks.tpce.pojo.TxnHarnessStructs.*;
import static java.sql.Connection.TRANSACTION_READ_COMMITTED;

import com.oltpbenchmark.jdbc.AutoIncrementPreparedStatement;
import com.oltpbenchmark.types.DatabaseType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.time.Instant;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DBConnectionImpl implements DBConnection {

    private static final Logger LOG = LoggerFactory.getLogger(DBConnectionImpl.class);
    private static MEE marketExchangeGenerator = MEE.getMee();

    @Override
    public ResultSet exec(Connection conn, PreparedStatement statement) throws SQLException {
        try {
            ResultSet rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            LOG.error("execute sql statement error: " + statement.toString() + e);
            conn.rollback();
            return null;
        }
    }

    private void TokenizeSmart() {
        return;
    }

    @Override
    public void execute(Connection conn, TBrokerVolumeTxnInput tBrokerVolumeTxnInput, TBrokerVolumeFrame1Output tBrokerVolumeFrame1Output) {
        int i_broker_name;
        int i_list_len;
        int i_volume;

        try {
            //start transaction
            StringBuilder brokers = new StringBuilder();

            brokers.append("{");
            brokers.append(tBrokerVolumeTxnInput.getBrokerList()[0]);
            for (int i = 1; i < tBrokerVolumeTxnInput.getBrokerList().length; i++) {
                brokers.append(",");
                brokers.append(tBrokerVolumeTxnInput.getBrokerList()[i]);
            }
            brokers.append("}");

            String sql = String.format("SELECT * FROM BrokerVolumeFrame1( '%s', '%s' )", brokers.toString(), tBrokerVolumeTxnInput.getSectorName());
            PreparedStatement brokerVolumePreparedStatement = conn.prepareStatement(sql);
            ResultSet rs = exec(conn, brokerVolumePreparedStatement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for" + brokerVolumePreparedStatement.toString());
            } else {
                if(rs.getInt("list_len") == 0){
                    return;
                }

                tBrokerVolumeFrame1Output.setList_len(rs.getInt("list_len"));
                String[] broker_name = (String[]) rs.getArray("broker_name").getArray();
                BigDecimal[] volume = (BigDecimal[]) rs.getArray("volume").getArray();
                int listLen = rs.getInt("list_len");

                //check count
                if (listLen != broker_name.length || listLen != volume.length) {
                    LOG.error("Broker Volume check count error: " + listLen);
                }
                for (int i = 0; i < broker_name.length; i++) {
                    tBrokerVolumeFrame1Output.getBroker_name()[i] = broker_name[i];
                    tBrokerVolumeFrame1Output.getVolume()[i] = volume[i].doubleValue();
                }
            }

        } catch (Exception e) {
            LOG.error("Broker volume roll back exception" + e);
        }

    }

    @Override
    public void execute(Connection conn, TCustomerPositionFrame1Input tCustomerPositionFrame1Input, TCustomerPositionFrame1Output tCustomerPositionFrame1Output) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM CustomerPositionFrame1( ? , ?)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tCustomerPositionFrame1Input.getCust_id());
            statement.setString(2, tCustomerPositionFrame1Input.getTax_id());
            ResultSet rs = exec(conn, statement);

            if (rs == null || !rs.next()) {
                throw new SQLException("no result for" + statement.toString());
            } else {
                tCustomerPositionFrame1Output.setCust_id(rs.getLong("cust_id"));
                tCustomerPositionFrame1Output.setAcct_len(rs.getInt("acct_len"));
                tCustomerPositionFrame1Output.setC_ad_id(rs.getLong("c_ad_id"));
                tCustomerPositionFrame1Output.setC_area_1(rs.getString("c_area_1"));
                tCustomerPositionFrame1Output.setC_area_2(rs.getString("c_area_2"));
                tCustomerPositionFrame1Output.setC_area_3(rs.getString("c_area_3"));

                tCustomerPositionFrame1Output.setC_ctry_1(rs.getString("c_ctry_1"));
                tCustomerPositionFrame1Output.setC_ctry_2(rs.getString("c_ctry_2"));
                tCustomerPositionFrame1Output.setC_ctry_3(rs.getString("c_ctry_3"));


                tCustomerPositionFrame1Output.setC_dob(new TimestampType(rs.getDate("c_dob")));
                tCustomerPositionFrame1Output.setC_email_1(rs.getString("c_email_1"));
                tCustomerPositionFrame1Output.setC_email_2(rs.getString("c_email_2"));
                tCustomerPositionFrame1Output.setC_ext_1(rs.getString("c_ext_1"));
                tCustomerPositionFrame1Output.setC_ext_2(rs.getString("c_ext_2"));
                tCustomerPositionFrame1Output.setC_ext_3(rs.getString("c_ext_3"));
                tCustomerPositionFrame1Output.setC_f_name(rs.getString("c_f_name"));
                tCustomerPositionFrame1Output.setC_gndr(rs.getString("c_gndr"));

                tCustomerPositionFrame1Output.setC_l_name(rs.getString("c_l_name"));
                tCustomerPositionFrame1Output.setC_local_1(rs.getString("c_local_1"));
                tCustomerPositionFrame1Output.setC_local_2(rs.getString("c_local_2"));
                tCustomerPositionFrame1Output.setC_local_3(rs.getString("c_local_3"));

                tCustomerPositionFrame1Output.setC_m_name(rs.getString("c_m_name"));
                tCustomerPositionFrame1Output.setC_st_id(rs.getString("c_st_id"));
                tCustomerPositionFrame1Output.setC_tier(Character.forDigit(rs.getInt("c_tier"), 10));


                BigDecimal[] asset = (BigDecimal[]) rs.getArray("asset_total").getArray();
                for (int i = 0; i < asset.length; i++) {
                    tCustomerPositionFrame1Output.getAsset_total()[i] = asset[i].doubleValue();
                }

                Long[] acct = (Long[]) rs.getArray("acct_id").getArray();
                for (int i = 0; i < acct.length; i++) {
                    tCustomerPositionFrame1Output.getAcct_id()[i] = acct[i];
                }

                BigDecimal[] cash = (BigDecimal[]) rs.getArray("cash_bal").getArray();
                for (int i = 0; i < cash.length; i++) {
                    tCustomerPositionFrame1Output.getCash_bal()[i] = cash[i].doubleValue();
                }

                //check count
                if (tCustomerPositionFrame1Output.getAcct_len() != asset.length ||
                    tCustomerPositionFrame1Output.getAcct_len() != acct.length || tCustomerPositionFrame1Output.getAcct_len() != cash.length) {
                    LOG.error("Customer Position check count error: " + tCustomerPositionFrame1Output.getAcct_len());
                }
            }
        } catch (Exception e) {
            LOG.error("Customer Position frame1 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TCustomerPositionFrame2Input tCustomerPositionFrame2Input, TCustomerPositionFrame2Output tCustomerPositionFrame2Output) {
        int i_hist_dts;
        int i_hist_len;
        int i_qty;
        int i_symbol;
        int i_trade_id;
        int i_trade_status;

        try {

            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM CustomerPositionFrame2(?)");

            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tCustomerPositionFrame2Input.getAcct_id());
            ResultSet rs = exec(conn, statement);

            if (rs == null || !rs.next()) {
                throw new SQLException("no result for" + sqlStmt.toString());
            } else {
                tCustomerPositionFrame2Output.setHist_len(rs.getInt("hist_len"));

                Array hist = rs.getArray("hist_dts");
                Timestamp[] hist_dts = (Timestamp[]) hist.getArray();

                for (int i = 0; i < hist_dts.length; i++) {
                    Instant instant = hist_dts[i].toInstant();
                    long micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                    tCustomerPositionFrame2Output.getHist_dts()[i] = new TimestampType(micros);
                }

                Array trade = rs.getArray("trade_id");
                Long[] trade_id = (Long[]) trade.getArray();
                for (int i = 0; i < trade_id.length; i++) {
                    tCustomerPositionFrame2Output.getTrade_id()[i] = trade_id[i];
                }

                Array q = rs.getArray("qty");
                Integer[] qty = (Integer[]) q.getArray();
                for (int i = 0; i < qty.length; i++) {
                    tCustomerPositionFrame2Output.getQty()[i] = qty[i];
                }

                Array sym = rs.getArray("symbol");
                String[] symbol = (String[]) sym.getArray();
                for (int i = 0; i < symbol.length; i++) {
                    tCustomerPositionFrame2Output.getSymbol()[i] = symbol[i];
                }

                Array trade_s = rs.getArray("trade_status");
                String[] trade_status = (String[]) trade_s.getArray();
                for (int i = 0; i < trade_status.length; i++) {
                    tCustomerPositionFrame2Output.getTrade_status()[i] = trade_status[i];
                }

                int length = tCustomerPositionFrame2Output.getHist_len();
                if (length != hist_dts.length || length != trade_id.length
                    || length != qty.length || length != symbol.length || length != trade_status.length) {
                    LOG.error("Customer Position frame2 check count error: " + length);
                }

            }
        } catch (Exception e) {
            LOG.error("Customer Position frame2 roll back exception" + e);
        }

    }

    @Override
    public void execute(Connection conn, TDataMaintenanceTxnInput tDataMaintenanceTxnInput, TDataMaintenanceTxnOutput tDataMaintenanceTxnOutput) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM DataMaintenanceFrame1( ? , ?, ? , ? , ? , ?, ? , ? )");

            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tDataMaintenanceTxnInput.getAcctId());
            statement.setLong(2, tDataMaintenanceTxnInput.getCId());
            statement.setLong(3, tDataMaintenanceTxnInput.getCoId());
            statement.setInt(4, tDataMaintenanceTxnInput.getDayOfMonth());
            statement.setString(5, tDataMaintenanceTxnInput.getSymbol());
            statement.setString(6, tDataMaintenanceTxnInput.getTableName());
            statement.setString(7, tDataMaintenanceTxnInput.getTxId());
            statement.setInt(8, tDataMaintenanceTxnInput.getVolIncr());
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for" + sqlStmt.toString());
            } else {
                tDataMaintenanceTxnOutput.setStatus(rs.getInt("status"));
            }

        } catch (Exception e) {
            tDataMaintenanceTxnOutput.setStatus(ErrorCode.EXPECTED_ROLLBACK);
            LOG.error("Data Maintenance roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TMarketFeedFrame1Input tMarketFeedFrame1Input, TMarketFeedFrame1Output tMarketFeedFrame1Output) {
        StringBuilder symbol = new StringBuilder();
        symbol.append("{");
        symbol.append(tMarketFeedFrame1Input.getEntries()[0].getSymbol());
        for (int i = 1; i < tMarketFeedFrame1Input.getEntries().length; i++) {
            symbol.append(",");
            symbol.append(tMarketFeedFrame1Input.getEntries()[i].getSymbol());
        }
        symbol.append("}");

        StringBuilder price = new StringBuilder();
        price.append("{");
        price.append(tMarketFeedFrame1Input.getEntries()[0].getPrice_quote());
        for (int i = 1; i < tMarketFeedFrame1Input.getEntries().length; i++) {
            price.append(",");
            price.append(tMarketFeedFrame1Input.getEntries()[i].getPrice_quote());
        }
        price.append("}");

        StringBuilder qty = new StringBuilder();
        qty.append("{");
        qty.append(tMarketFeedFrame1Input.getEntries()[0].getTrade_qty());
        for (int i = 1; i < tMarketFeedFrame1Input.getEntries().length; i++) {
            qty.append(",");
            qty.append(tMarketFeedFrame1Input.getEntries()[i].getTrade_qty());
        }
        qty.append("}");


        try {
            String sql = String.format("SELECT * FROM MarketFeedFrame1('%d','%s','%s','%s', '%s', '%s', '%s', '%s')", max_feed_len,
                price.toString(), tMarketFeedFrame1Input.getStatusAndTradeType().status_submitted, symbol.toString(),
                qty.toString(), tMarketFeedFrame1Input.getStatusAndTradeType().type_limit_buy, tMarketFeedFrame1Input.getStatusAndTradeType().type_limit_sell, tMarketFeedFrame1Input.getStatusAndTradeType().type_stop_loss);

            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for" + statement.toString());
            } else {

                String[] symbol_list = (String[]) rs.getArray("symbol_list").getArray();

                Long[] trade_id_list = (Long[]) rs.getArray("trade_id_list").getArray();
                BigDecimal[] price_quote_list = (BigDecimal[]) rs.getArray("price_quote_list").getArray();
                Integer[] trade_qty_list = (Integer[]) rs.getArray("trade_qty_list").getArray();
                String[] trade_type_list = (String[]) rs.getArray("trade_type_list").getArray();

                tMarketFeedFrame1Output.setNum_updated(rs.getInt("num_updated"));
                tMarketFeedFrame1Output.setSend_len(max_feed_len);

                for (int i = 0; i < symbol_list.length; i++) {
                    TTradeRequest m_TriggeredLimitOrders = new TTradeRequest();
                    m_TriggeredLimitOrders.setSymbol(symbol_list[i]);
                    m_TriggeredLimitOrders.setTrade_id(trade_id_list[i]);
                    m_TriggeredLimitOrders.setPrice_quote(price_quote_list[i].doubleValue());
                    m_TriggeredLimitOrders.setTrade_qty(trade_qty_list[i]);
                    m_TriggeredLimitOrders.setTrade_type_id(trade_type_list[i]);
                    m_TriggeredLimitOrders.seteAction(eMEEProcessOrder);

                    marketExchangeGenerator.submitTradeRequest(conn, m_TriggeredLimitOrders);
                }
            }

        } catch (Exception e) {
            LOG.error("Market Feed roll back exception" + e);
        }

    }


    @Override
    public void execute(Connection conn, TMarketWatchTxnInput tMarketWatchFrame1Input, TMarketWatchFrame1Output tMarketWatchFrame1Output) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM MarketWatchFrame1(? , ?, ? , ? , ? ) as res(status SMALLINT, pct_change FLOAT)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tMarketWatchFrame1Input.getAcctId());
            statement.setLong(2, tMarketWatchFrame1Input.getCId());
            statement.setLong(3, tMarketWatchFrame1Input.getEndingCoId());
            statement.setString(4, tMarketWatchFrame1Input.getIndustryName());
            statement.setLong(5, tMarketWatchFrame1Input.getStartingCoId());
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for" + sqlStmt.toString());
            } else {
                tMarketWatchFrame1Output.setPct_change(rs.getDouble("pct_change"));
            }
        } catch (Exception e) {
            LOG.error("Market Watch roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TSecurityDetailTxnInput tSecurityDetailFrame1Input, TSecurityDetailFrame1Output tSecurityDetailFrame1Output) {
        try {
            conn.setAutoCommit(false);
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM SecurityDetailFrame1( ?, ?, ?, ? )" +
                "as res(fin_len integer,day_len integer,news_len integer,cp_co_name text,cp_in_name text,fin_year text,fin_qtr text,fin_start_year text,fin_start_month text," +
                "fin_start_day text,fin_start_hour text,fin_start_min text,fin_start_sec text,fin_rev text,fin_net_earn text,fin_basic_eps text,fin_dilut_eps text," +
                "fin_margin text,fin_invent text,fin_assets text,fin_liab text,fin_out_basic text,fin_out_dilut text,day_date_year text,day_date_month text,day_date_day text," +
                "day_date_hour text,day_date_minute text,day_date_second text,day_close text,day_high text,day_low text,day_vol text,news_it text,news_year text,news_month text,news_day text," +
                "news_hour text,news_minute text,news_second text,news_src text,news_auth text,news_headline text,news_summary text,last_price FLOAT,last_open FLOAT,last_vol BIGINT,S_NAME VARCHAR,CO_ID BIGINT," +
                "CO_NAME VARCHAR,CO_SP_RATE CHAR(4),CO_CEO VARCHAR,CO_DESC VARCHAR, CO_OPEN_YEAR NUMERIC,CO_OPEN_MONTH NUMERIC,CO_OPEN_DAY NUMERIC,CO_OPEN_HOUR NUMERIC,CO_OPEN_MINUTE NUMERIC,CO_OPEN_SECOND NUMERIC,CO_ST_ID CHAR(4),CA_AD_LINE1 VARCHAR,CA_AD_LINE2 VARCHAR,ZCA_ZC_TOWN VARCHAR,ZCA_ZC_DIV VARCHAR,CA_AD_ZC_CODE CHAR(12)," +
                "CA_AD_CTRY VARCHAR,S_NUM_OUT BIGINT, S_START_YEAR NUMERIC,S_START_MONTH NUMERIC,S_START_DAY NUMERIC,S_START_HOUR NUMERIC,S_START_MINUTE NUMERIC,S_START_SECOND NUMERIC,S_EXCH_YEAR NUMERIC,S_EXCH_MONTH NUMERIC,S_EXCH_DAY NUMERIC,S_EXCH_HOUR NUMERIC,S_EXCH_MINUTE NUMERIC,S_EXCH_SECOND NUMERIC,S_PE FLOAT,S_52WK_HIGH FLOAT, " +
                "S_52WK_HIGH_YEAR NUMERIC,S_52WK_HIGH_MONTH NUMERIC,S_52WK_HIGH_DAY NUMERIC,S_52WK_HIGH_HOUR NUMERIC,S_52WK_HIGH_MINUTE NUMERIC,S_52WK_HIGH_SECOND NUMERIC," +
                "S_52WK_LOW FLOAT,S_52WK_LOW_YEAR NUMERIC,S_52WK_LOW_MONTH NUMERIC,S_52WK_LOW_DAY NUMERIC,S_52WK_LOW_HOUR NUMERIC,S_52WK_LOW_MINUTE NUMERIC,S_52WK_LOW_SECOND NUMERIC, S_DIVIDEND FLOAT,S_YIELD FLOAT,ZEA_ZC_DIV VARCHAR,EA_AD_CTRY VARCHAR,EA_AD_LINE1 VARCHAR,EA_AD_LINE2 VARCHAR,ZEA_ZC_TOWN VARCHAR,EA_AD_ZC_CODE CHAR(12),EX_CLOSE INTEGER," +
                "EX_DESC VARCHAR,EX_NAME VARCHAR,EX_NUM_SYMB INTEGER,EX_OPEN INTEGER)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setShort(1, (short) (tSecurityDetailFrame1Input.getAccessLobFlag() ? 1 : 0));
            statement.setInt(2, tSecurityDetailFrame1Input.getMaxRowsToReturn());
            statement.setTimestamp(3, new Timestamp(tSecurityDetailFrame1Input.getStartDay().getMSTime()));
            statement.setString(4, tSecurityDetailFrame1Input.getSymbol());
            ResultSet rs = exec(conn, statement);
            conn.commit();
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for" + sqlStmt.toString());
            } else {


                tSecurityDetailFrame1Output.setDay_len(rs.getInt("day_len"));
                tSecurityDetailFrame1Output.setFin_len(rs.getInt("fin_len"));
                tSecurityDetailFrame1Output.setNews_len(rs.getInt("news_len"));
                tSecurityDetailFrame1Output.setS52_wk_high(rs.getDouble("S_52WK_HIGH"));


                tSecurityDetailFrame1Output.setS52_wk_high_date(new TimestampType(new GregorianCalendar(rs.getInt("S_52WK_HIGH_YEAR"),
                    rs.getInt("S_52WK_HIGH_MONTH") - 1, rs.getInt("S_52WK_HIGH_DAY"),
                    rs.getInt("S_52WK_HIGH_HOUR"), rs.getInt("S_52WK_HIGH_MINUTE"), rs.getInt("S_52WK_HIGH_SECOND")).getTime()));
                tSecurityDetailFrame1Output.setS52_wk_low_date(new TimestampType(new GregorianCalendar(rs.getInt("S_52WK_LOW_YEAR"),
                    rs.getInt("S_52WK_LOW_MONTH") - 1, rs.getInt("S_52WK_LOW_DAY"),
                    rs.getInt("S_52WK_LOW_HOUR"), rs.getInt("S_52WK_LOW_MINUTE"), rs.getInt("S_52WK_LOW_SECOND")).getTime()));

                tSecurityDetailFrame1Output.setCeo_name(rs.getString("CO_CEO"));
                tSecurityDetailFrame1Output.setCo_ad_cty(rs.getString("CA_AD_CTRY"));
                tSecurityDetailFrame1Output.setCo_ad_div(rs.getString("ZCA_ZC_DIV"));
                tSecurityDetailFrame1Output.setCo_ad_line1(rs.getString("CA_AD_LINE1"));
                tSecurityDetailFrame1Output.setCo_ad_line2(rs.getString("CA_AD_LINE2"));
                tSecurityDetailFrame1Output.setCo_ad_town(rs.getString("ZCA_ZC_TOWN"));
                tSecurityDetailFrame1Output.setCo_ad_zip(rs.getString("CA_AD_ZC_CODE"));
                tSecurityDetailFrame1Output.setCo_desc(rs.getString("CO_DESC"));
                tSecurityDetailFrame1Output.setCo_name(rs.getString("CO_NAME"));
                tSecurityDetailFrame1Output.setCo_st_id(rs.getString("CO_ST_ID"));

                String[] co_str = rs.getString("cp_co_name").substring(1).split("\\|");
                String[] cp_str = rs.getString("cp_in_name").substring(1).split("\\|");
                //check count
                if (co_str.length != max_comp_len || cp_str.length != max_comp_len) {
                    LOG.error("security detail check count error: " + "co_str/cp_str");
                }


                for (int i = 0; i < co_str.length && i < cp_str.length; i++) {
                    tSecurityDetailFrame1Output.getCp_co_name()[i] = co_str[i];
                    tSecurityDetailFrame1Output.getCp_in_name()[i] = cp_str[i];
                }

                String[] day_date_year = rs.getString("day_date_year").substring(1).split("\\|");
                String[] day_date_month = rs.getString("day_date_month").substring(1).split("\\|");
                String[] day_date_day = rs.getString("day_date_day").substring(1).split("\\|");
                String[] day_close = rs.getString("day_close").substring(1).split("\\|");
                String[] day_high = rs.getString("day_high").substring(1).split("\\|");
                String[] day_low = rs.getString("day_low").substring(1).split("\\|");
                String[] day_vol = rs.getString("day_vol").substring(1).split("\\|");

                for (int i = 0; i < day_date_year.length; i++) {
                    tSecurityDetailFrame1Output.getDay()[i] = new TDailyHistory();
                    tSecurityDetailFrame1Output.getDay()[i].setClose(Double.parseDouble(day_close[i]));
                    tSecurityDetailFrame1Output.getDay()[i].setHigh(Double.parseDouble(day_high[i]));
                    tSecurityDetailFrame1Output.getDay()[i].setLow(Double.parseDouble(day_low[i]));
                    tSecurityDetailFrame1Output.getDay()[i].setVol(Long.parseLong(day_vol[i]));
                    tSecurityDetailFrame1Output.getDay()[i].setDate(new TimestampType(new GregorianCalendar(Integer.parseInt(day_date_year[i]),
                        Integer.parseInt(day_date_month[i]) - 1, Integer.parseInt(day_date_day[i])).getTime()));
                }

                //check count
                if (tSecurityDetailFrame1Output.getDay_len() != day_date_year.length) {
                    LOG.error("security detail check count error: " + "day length");
                }
                tSecurityDetailFrame1Output.setDivid(rs.getDouble("S_DIVIDEND"));


                tSecurityDetailFrame1Output.setEx_ad_cty(rs.getString("EA_AD_CTRY"));
                tSecurityDetailFrame1Output.setEx_ad_div(rs.getString("ZEA_ZC_DIV"));
                tSecurityDetailFrame1Output.setEx_ad_line1(rs.getString("EA_AD_LINE1"));
                tSecurityDetailFrame1Output.setEx_ad_line2(rs.getString("EA_AD_LINE2"));
                tSecurityDetailFrame1Output.setEx_ad_town(rs.getString("ZEA_ZC_TOWN"));
                tSecurityDetailFrame1Output.setEx_ad_zip(rs.getString("EA_AD_ZC_CODE"));
                tSecurityDetailFrame1Output.setEx_close(rs.getInt("EX_CLOSE"));
                tSecurityDetailFrame1Output.setEx_date(new TimestampType(new GregorianCalendar(rs.getInt("S_EXCH_YEAR"), rs.getInt("S_EXCH_MONTH") - 1, rs.getInt("S_EXCH_DAY"),
                    rs.getInt("S_EXCH_HOUR"), rs.getInt("S_EXCH_MINUTE"), rs.getInt("S_EXCH_SECOND")).getTime()));
                tSecurityDetailFrame1Output.setEx_desc(rs.getString("EX_DESC"));
                tSecurityDetailFrame1Output.setEx_name(rs.getString("EX_NAME"));
                tSecurityDetailFrame1Output.setEx_num_symb(rs.getInt("EX_NUM_SYMB"));
                tSecurityDetailFrame1Output.setEx_open(rs.getInt("EX_OPEN"));

                //fin

                String[] fin_year = rs.getString("fin_year").substring(1).split("\\|");
                String[] fin_qtr = rs.getString("fin_qtr").substring(1).split("\\|");
                String[] fin_start_year = rs.getString("fin_start_year").substring(1).split("\\|");
                String[] fin_start_month = rs.getString("fin_start_month").substring(1).split("\\|");
                String[] fin_start_day = rs.getString("fin_start_day").substring(1).split("\\|");
                String[] fin_start_hour = rs.getString("fin_start_hour").substring(1).split("\\|");
                String[] fin_start_min = rs.getString("fin_start_min").substring(1).split("\\|");
                String[] fin_start_sec = rs.getString("fin_start_sec").substring(1).split("\\|");
                String[] fin_rev = rs.getString("fin_rev").substring(1).split("\\|");
                String[] fin_net_earn = rs.getString("fin_net_earn").substring(1).split("\\|");
                String[] fin_basic_eps = rs.getString("fin_basic_eps").substring(1).split("\\|");
                String[] fin_dilut_eps = rs.getString("fin_dilut_eps").substring(1).split("\\|");
                String[] fin_margin = rs.getString("fin_margin").substring(1).split("\\|");
                String[] fin_invent = rs.getString("fin_invent").substring(1).split("\\|");
                String[] fin_assets = rs.getString("fin_assets").substring(1).split("\\|");
                String[] fin_liab = rs.getString("fin_liab").substring(1).split("\\|");
                String[] fin_out_basic = rs.getString("fin_out_basic").substring(1).split("\\|");
                String[] fin_out_dilut = rs.getString("fin_out_dilut").substring(1).split("\\|");


                for (int i = 0; i < tSecurityDetailFrame1Output.getFin_len(); i++) {
                    TFinInfo finInfo = new TFinInfo();
                    finInfo.setYear(Integer.parseInt(fin_year[i]));
                    finInfo.setQtr(Integer.parseInt(fin_qtr[i]));
                    finInfo.setStart_date(new TimestampType(new GregorianCalendar(Integer.parseInt(fin_start_year[i]),
                        Integer.parseInt(fin_start_month[i]) - 1, Integer.parseInt(fin_start_day[i])).getTime()));
                    finInfo.setRev(Double.parseDouble(fin_rev[i]));
                    finInfo.setNet_earn(Double.parseDouble(fin_net_earn[i]));
                    finInfo.setBasic_eps(Double.parseDouble(fin_basic_eps[i]));
                    finInfo.setDilut_eps(Double.parseDouble(fin_dilut_eps[i]));
                    finInfo.setMargin(Double.parseDouble(fin_margin[i]));
                    finInfo.setInvent(Double.parseDouble(fin_invent[i]));
                    finInfo.setAssets(Double.parseDouble(fin_assets[i]));
                    finInfo.setLiab(Double.parseDouble(fin_liab[i]));
                    finInfo.setOut_basic(Double.parseDouble(fin_out_basic[i]));
                    finInfo.setOut_dilut(Double.parseDouble(fin_out_dilut[i]));
                    tSecurityDetailFrame1Output.getFin()[i] = finInfo;
                }
                if (tSecurityDetailFrame1Output.getFin_len() != fin_year.length) {
                    LOG.error("security detail check count error: " + "fin length");
                }
                tSecurityDetailFrame1Output.setLast_open(rs.getDouble("last_open"));
                tSecurityDetailFrame1Output.setLast_price(rs.getDouble("last_price"));
                tSecurityDetailFrame1Output.setLast_vol(rs.getLong("last_vol"));


                String[] news_it = rs.getString("news_it").substring(1).split("\\|");
                String[] news_year = rs.getString("news_year").substring(1).split("\\|");
                String[] news_month = rs.getString("news_month").substring(1).split("\\|");
                String[] news_day = rs.getString("news_day").substring(1).split("\\|");
                String[] news_hour = rs.getString("news_hour").substring(1).split("\\|");
                String[] news_minute = rs.getString("news_minute").substring(1).split("\\|");
                String[] news_second = rs.getString("news_second").substring(1).split("\\|");

                String[] news_src = rs.getString("news_src").substring(1).split("\\|");
                String[] news_auth = rs.getString("news_auth").substring(1).split("\\|");
                String[] news_headline = rs.getString("news_headline").substring(1).split("\\|");
                String[] news_summary = rs.getString("news_summary").substring(1).split("\\|");
                for (int i = 0; i < tSecurityDetailFrame1Output.getNews_len(); i++) {
                    TNews news = new TNews();

                    news.setDts(new TimestampType(new GregorianCalendar(Integer.parseInt(news_year[i]),
                        Integer.parseInt(news_month[i]) - 1, Integer.parseInt(news_day[i]),
                        Integer.parseInt(news_hour[i]), Integer.parseInt(news_minute[i]), (int) Double.parseDouble(news_second[i])).getTime()));
                    if (!tSecurityDetailFrame1Input.getAccessLobFlag()) {
                        news.setSrc(news_src[i]);
                        news.setAuth(news_auth[i]);
                        news.setHeadline(news_headline[i]);
                        news.setSummary(news_summary[i]);
                    } else {
                        news.setItem(news_it[i]);
                        news.setSrc(news_src[i]);
                        news.setAuth(news_auth[i]);
                    }
                    tSecurityDetailFrame1Output.getNews()[i] = news;
                }
                if (tSecurityDetailFrame1Output.getNews_len() != news_year.length) {
                    LOG.error("security detail check count error: " + "news length");
                }
                tSecurityDetailFrame1Output.setPe_ratio(rs.getDouble("S_PE"));
                tSecurityDetailFrame1Output.setS_name(rs.getString("S_NAME"));
                tSecurityDetailFrame1Output.setNum_out(rs.getLong("S_NUM_OUT"));
                tSecurityDetailFrame1Output.setSp_rate(rs.getString("CO_SP_RATE"));
                tSecurityDetailFrame1Output.setYield(rs.getDouble("S_YIELD"));
                tSecurityDetailFrame1Output.setOpen_date(new TimestampType(new GregorianCalendar(rs.getInt("CO_OPEN_YEAR"),
                    rs.getInt("CO_OPEN_MONTH") - 1, rs.getInt("CO_OPEN_DAY")).getTime()));
                tSecurityDetailFrame1Output.setStart_date(new TimestampType(new GregorianCalendar(rs.getInt("S_START_YEAR"),
                    rs.getInt("S_START_MONTH") - 1, rs.getInt("S_START_DAY")).getTime()));
            }

        } catch (Exception e) {
            LOG.error("Security Detail roll back exception" + e);
        }

    }

    @Override
    public void execute(Connection conn, TTradeCleanupTxnInput tTradeCleanupFrame1Input, TTradeCleanupTxnOutput output) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeCleanupFrame1(?, ?, ?, ?)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setString(1, tTradeCleanupFrame1Input.getSt_canceled_id());
            statement.setString(2, tTradeCleanupFrame1Input.getSt_pending_id());
            statement.setString(3, tTradeCleanupFrame1Input.getSt_submitted_id());
            statement.setLong(4, tTradeCleanupFrame1Input.getStart_trade_id());
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sqlStmt.toString());
            }
        } catch (Exception e) {
            output.setStatus(ErrorCode.EXPECTED_ROLLBACK);
            LOG.error("Clean up roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeLookupFrame1Input tTradeLookupFrame1Input, TTradeLookupFrame1Output tTradeLookupFrame1Output) {
        try {

            StringBuilder trades = new StringBuilder();
            trades.append("{");
            trades.append(tTradeLookupFrame1Input.getTrade_id()[0]);
            for (int i = 1; i < tTradeLookupFrame1Input.getTrade_id().length; i++) {
                trades.append(",");
                trades.append(tTradeLookupFrame1Input.getTrade_id()[i]);
            }
            trades.append("}");

            String sql = String.format("SELECT * FROM TradeLookupFrame1('%d','%s')", tTradeLookupFrame1Input.getMax_trades(),
                trades.toString());
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sql.toString());
            } else {

                tTradeLookupFrame1Output.setNum_found(rs.getInt("num_found"));

                BigDecimal[] bid_price_list = (BigDecimal[]) rs.getArray("bid_price_list").getArray();
                String[] exec_name_list = (String[]) rs.getArray("exec_name_list").getArray();
                Short[] is_cash_list = (Short[]) rs.getArray("is_cash_list").getArray();
                Short[] is_market_list = (Short[]) rs.getArray("is_market_list").getArray();
                BigDecimal[] trade_price_list = (BigDecimal[]) rs.getArray("trade_price_list").getArray();
                BigDecimal[] settlement_amount_list = (BigDecimal[]) rs.getArray("settlement_amount_list").getArray();
                Timestamp[] settlement_cash_due_date_list = (Timestamp[]) rs.getArray("settlement_cash_due_date_list").getArray();
                String[] settlement_cash_type_list = (String[]) rs.getArray("settlement_cash_type_list").getArray();
                BigDecimal[] cash_transaction_amount_list = (BigDecimal[]) rs.getArray("cash_transaction_amount_list").getArray();
                Timestamp[] cash_transaction_dts_list = (Timestamp[]) rs.getArray("cash_transaction_dts_list").getArray();
                Timestamp[] trade_history_dts_1_list = (Timestamp[]) rs.getArray("trade_history_dts_1_list").getArray();
                Timestamp[] trade_history_dts_2_list = (Timestamp[]) rs.getArray("trade_history_dts_2_list").getArray();
                Timestamp[] trade_history_dts_3_list = (Timestamp[]) rs.getArray("trade_history_dts_3_list").getArray();
                String[] trade_history_status_id_1_list = (String[]) rs.getArray("trade_history_status_id_1_list").getArray();
                String[] trade_history_status_id_2_list = (String[]) rs.getArray("trade_history_status_id_2_list").getArray();
                String[] trade_history_status_id_3_list = (String[]) rs.getArray("trade_history_status_id_3_list").getArray();
                String[] cash_transaction_name_list = (String[]) rs.getArray("cash_transaction_name_list").getArray();

                for (int i = 0; i < bid_price_list.length; i++) {
                    Instant instant = null;
                    long micros = 0L;

                    tTradeLookupFrame1Output.getTrade_info()[i].setBid_price(bid_price_list[i].doubleValue());

                    //only is cash
                    if(cash_transaction_dts_list[i] != null){
                        instant = cash_transaction_dts_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeLookupFrame1Output.getTrade_info()[i].setCash_transaction_amount(cash_transaction_amount_list[i].doubleValue());
                        tTradeLookupFrame1Output.getTrade_info()[i].setCash_transaction_dts(new TimestampType(micros));
                        tTradeLookupFrame1Output.getTrade_info()[i].setCash_transaction_name(cash_transaction_name_list[i]);
                    }


                    tTradeLookupFrame1Output.getTrade_info()[i].setExec_name(exec_name_list[i]);
                    tTradeLookupFrame1Output.getTrade_info()[i].setIs_cash(is_cash_list[i] != 0);
                    tTradeLookupFrame1Output.getTrade_info()[i].setIs_market(is_market_list[i] != 0);
                    tTradeLookupFrame1Output.getTrade_info()[i].setSettlement_amount(settlement_amount_list[i].doubleValue());

                    instant = settlement_cash_due_date_list[i].toInstant();
                    micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                    tTradeLookupFrame1Output.getTrade_info()[i].setSettlement_cash_due_date(new TimestampType(micros));
                    tTradeLookupFrame1Output.getTrade_info()[i].setSettlement_cash_type(settlement_cash_type_list[i]);

                    if(trade_history_dts_1_list[i] != null){
                        instant = trade_history_dts_1_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeLookupFrame1Output.getTrade_info()[i].getTrade_history_dts()[0] = new TimestampType(micros);
                    }


                    if(trade_history_dts_2_list[i] != null){
                        instant = trade_history_dts_2_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeLookupFrame1Output.getTrade_info()[i].getTrade_history_dts()[1] = new TimestampType(micros);
                    }


                    if(trade_history_dts_3_list[i] != null){
                        instant = trade_history_dts_3_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeLookupFrame1Output.getTrade_info()[i].getTrade_history_dts()[2] = new TimestampType(micros);
                    }

                    tTradeLookupFrame1Output.getTrade_info()[i].getTrade_history_status_id()[0] = trade_history_status_id_1_list[i];
                    tTradeLookupFrame1Output.getTrade_info()[i].getTrade_history_status_id()[1] = trade_history_status_id_2_list[i];
                    tTradeLookupFrame1Output.getTrade_info()[i].getTrade_history_status_id()[2] = trade_history_status_id_3_list[i];
                    tTradeLookupFrame1Output.getTrade_info()[i].setTrade_price(trade_price_list[i].doubleValue());
                }

            }

        } catch (Exception e) {
            LOG.error("Trade lookup f1 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeLookupFrame2Input tTradeLookupFrame2Input, TTradeLookupFrame2Output tTradeLookupFrame2Output) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeLookupFrame2(?,?,?,?)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeLookupFrame2Input.getAcct_id());
            statement.setInt(2, tTradeLookupFrame2Input.getMax_trades());
            statement.setTimestamp(3, new Timestamp(tTradeLookupFrame2Input.getStart_trade_dts().getMSTime()));
            statement.setTimestamp(4, new Timestamp(tTradeLookupFrame2Input.getEnd_trade_dts().getMSTime()));

            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sqlStmt.toString());
            }else {

                tTradeLookupFrame2Output.setNum_found(rs.getInt("num_found"));

                BigDecimal[] bid_price_list = (BigDecimal[]) rs.getArray("bid_price_list").getArray();
                BigDecimal[] cash_transaction_amount_list = (BigDecimal[]) rs.getArray("cash_transaction_amount_list").getArray();
                Timestamp[] cash_transaction_dts_list = (Timestamp[]) rs.getArray("cash_transaction_dts_list").getArray();
                String[] cash_transaction_name_list = (String[]) rs.getArray("cash_transaction_name_list").getArray();
                String[] exec_name_list = (String[]) rs.getArray("exec_name_list").getArray();
                Short[] is_cash_list = (Short[]) rs.getArray("is_cash_list").getArray();
                Long[] t_id_list = (Long[]) rs.getArray("t_id_list").getArray();

                BigDecimal[] settlement_amount_list = (BigDecimal[]) rs.getArray("settlement_amount_list").getArray();
                Timestamp[] settlement_cash_due_date_list = (Timestamp[]) rs.getArray("settlement_cash_due_date_list").getArray();
                String[] settlement_cash_type_list = (String[]) rs.getArray("settlement_cash_type_list").getArray();
                Timestamp[] trade_history_dts_1_list = (Timestamp[]) rs.getArray("trade_history_dts_1_list").getArray();
                Timestamp[] trade_history_dts_2_list = (Timestamp[]) rs.getArray("trade_history_dts_2_list").getArray();
                Timestamp[] trade_history_dts_3_list = (Timestamp[]) rs.getArray("trade_history_dts_3_list").getArray();

                String[] trade_history_status_id_1_list = (String[]) rs.getArray("trade_history_status_id_1_list").getArray();
                String[] trade_history_status_id_2_list = (String[]) rs.getArray("trade_history_status_id_2_list").getArray();
                String[] trade_history_status_id_3_list = (String[]) rs.getArray("trade_history_status_id_3_list").getArray();
                BigDecimal[] trade_price_list = (BigDecimal[]) rs.getArray("trade_price_list").getArray();


                for (int i = 0; i < bid_price_list.length; i++) {
                    //time
                    Instant instant = null;
                    long micros = 0L;

                    tTradeLookupFrame2Output.getTrade_info()[i].setBid_price(bid_price_list[i].doubleValue());

                    if(cash_transaction_dts_list[i] != null){
                        instant = cash_transaction_dts_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeLookupFrame2Output.getTrade_info()[i].setCash_transaction_amount(cash_transaction_amount_list[i].doubleValue());
                        tTradeLookupFrame2Output.getTrade_info()[i].setCash_transaction_dts(new TimestampType(micros));
                        tTradeLookupFrame2Output.getTrade_info()[i].setCash_transaction_name(cash_transaction_name_list[i]);
                    }

                    tTradeLookupFrame2Output.getTrade_info()[i].setExec_name(exec_name_list[i]);
                    tTradeLookupFrame2Output.getTrade_info()[i].setIs_cash(is_cash_list[i] != 0);
                    tTradeLookupFrame2Output.getTrade_info()[i].setSettlement_amount(settlement_amount_list[i].doubleValue());

                    instant = settlement_cash_due_date_list[i].toInstant();
                    micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                    tTradeLookupFrame2Output.getTrade_info()[i].setSettlement_cash_due_date(new TimestampType(micros));
                    tTradeLookupFrame2Output.getTrade_info()[i].setSettlement_cash_type(settlement_cash_type_list[i]);

                    if(trade_history_dts_1_list[i] != null){
                        instant = trade_history_dts_1_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeLookupFrame2Output.getTrade_info()[i].getTrade_history_dts()[0] = new TimestampType(micros);
                    }

                    if(trade_history_dts_2_list[i] != null){
                        instant = trade_history_dts_2_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeLookupFrame2Output.getTrade_info()[i].getTrade_history_dts()[1] = new TimestampType(micros);
                    }


                    if(trade_history_dts_3_list[i] != null){
                        instant = trade_history_dts_3_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeLookupFrame2Output.getTrade_info()[i].getTrade_history_dts()[2] = new TimestampType(micros);
                    }


                    tTradeLookupFrame2Output.getTrade_info()[i].getTrade_history_status_id()[0] = trade_history_status_id_1_list[i];
                    tTradeLookupFrame2Output.getTrade_info()[i].getTrade_history_status_id()[1] = trade_history_status_id_2_list[i];
                    tTradeLookupFrame2Output.getTrade_info()[i].getTrade_history_status_id()[2] = trade_history_status_id_3_list[i];
                    tTradeLookupFrame2Output.getTrade_info()[i].setTrade_price(trade_price_list[i].doubleValue());
                    tTradeLookupFrame2Output.getTrade_info()[i].setTrade_id(t_id_list[i]);

                }
            }
        } catch (Exception e) {
            LOG.error("Trade lookup f2 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeLookupFrame3Input tTradeLookupFrame3Input, TTradeLookupFrame3Output tTradeLookupFrame3Output) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeLookupFrame3(?, ?, ?, ?, ?)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeLookupFrame3Input.getMax_acct_id());
            statement.setInt(2, tTradeLookupFrame3Input.getMax_trades());
            statement.setTimestamp(3, new Timestamp(tTradeLookupFrame3Input.getStart_trade_dts().getMSTime()));
            statement.setTimestamp(4, new Timestamp(tTradeLookupFrame3Input.getEnd_trade_dts().getMSTime()));
            statement.setString(5, tTradeLookupFrame3Input.getSymbol());

            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sqlStmt.toString());
            } else {
                tTradeLookupFrame3Output.setNum_found(rs.getInt("num_found"));
                if(tTradeLookupFrame3Output.getNum_found() == 0){
                    return;
                }
                Long[] t_ca_id_list =  (Long[]) rs.getArray("t_ca_id_list").getArray();
                Long[] t_id_list =  (Long[]) rs.getArray("t_id_list").getArray();

                BigDecimal[] cash_transaction_amount_list = (BigDecimal[]) rs.getArray("cash_transaction_amount_list").getArray();
                Timestamp[] cash_transaction_dts_list = (Timestamp[]) rs.getArray("cash_transaction_dts_list").getArray();
                String[] cash_transaction_name_list = (String[]) rs.getArray("cash_transaction_name_list").getArray();
                String[] exec_name_list = (String[]) rs.getArray("exec_name_list").getArray();

                Short[] is_cash_list = (Short[]) rs.getArray("is_cash_list").getArray();
                BigDecimal[] trade_price_list = (BigDecimal[]) rs.getArray("trade_price_list").getArray();
                Integer[] t_qty_list = (Integer[]) rs.getArray("t_qty_list").getArray();
                BigDecimal[] settlement_amount_list = (BigDecimal[]) rs.getArray("settlement_amount_list").getArray();
                Timestamp[] settlement_cash_due_date_list = (Timestamp[]) rs.getArray("settlement_cash_due_date_list").getArray();
                String[] settlement_cash_type_list = (String[]) rs.getArray("settlement_cash_type_list").getArray();
                Timestamp[] t_dts_list = (Timestamp[]) rs.getArray("t_dts_list").getArray();
                Timestamp[] trade_history_dts_1_list = (Timestamp[]) rs.getArray("trade_history_dts_1_list").getArray();
                Timestamp[] trade_history_dts_2_list = (Timestamp[]) rs.getArray("trade_history_dts_2_list").getArray();
                Timestamp[] trade_history_dts_3_list = (Timestamp[]) rs.getArray("trade_history_dts_3_list").getArray();

                String[] trade_history_status_id_1_list = (String[]) rs.getArray("trade_history_status_id_1_list").getArray();
                String[] trade_history_status_id_2_list = (String[]) rs.getArray("trade_history_status_id_2_list").getArray();
                String[] trade_history_status_id_3_list = (String[]) rs.getArray("trade_history_status_id_3_list").getArray();
                String[] t_tt_id_list = (String[]) rs.getArray("t_tt_id_list").getArray();

                for (int i = 0; i < cash_transaction_amount_list.length; i++) {
                    tTradeLookupFrame3Output.getTrade_info()[i] = new TTradeLookupFrame3TradeInfo();
                    Instant instant = null;
                    long micros = 0L;

                    tTradeLookupFrame3Output.getTrade_info()[i].setAcct_id(t_ca_id_list[i]);

                    if(cash_transaction_dts_list[i] != null){
                        instant = cash_transaction_dts_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeLookupFrame3Output.getTrade_info()[i].setCash_transaction_amount(cash_transaction_amount_list[i].doubleValue());
                        tTradeLookupFrame3Output.getTrade_info()[i].setCash_transaction_dts(new TimestampType(micros));
                        tTradeLookupFrame3Output.getTrade_info()[i].setCash_transaction_name(cash_transaction_name_list[i]);
                    }

                    tTradeLookupFrame3Output.getTrade_info()[i].setExec_name(exec_name_list[i]);
                    tTradeLookupFrame3Output.getTrade_info()[i].setIs_cash(is_cash_list[i] != 0);
                    tTradeLookupFrame3Output.getTrade_info()[i].setPrice(trade_price_list[i].doubleValue());
                    tTradeLookupFrame3Output.getTrade_info()[i].setQuantity(t_qty_list[i]);

                    tTradeLookupFrame3Output.getTrade_info()[i].setSettlement_amount(settlement_amount_list[i].doubleValue());

                    instant = settlement_cash_due_date_list[i].toInstant();
                    micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                    tTradeLookupFrame3Output.getTrade_info()[i].setSettlement_cash_due_date(new TimestampType(micros));
                    tTradeLookupFrame3Output.getTrade_info()[i].setSettlement_cash_type(settlement_cash_type_list[i]);

                    instant = t_dts_list[i].toInstant();
                    micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                    tTradeLookupFrame3Output.getTrade_info()[i].setTrade_dts(new TimestampType(micros));

                    if (trade_history_dts_1_list[i] != null) {
                        instant = trade_history_dts_1_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeLookupFrame3Output.getTrade_info()[i].getTrade_history_dts()[0] = new TimestampType(micros);
                    }

                    if (trade_history_dts_2_list[i] != null) {
                        instant = trade_history_dts_2_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeLookupFrame3Output.getTrade_info()[i].getTrade_history_dts()[1] = new TimestampType(micros);

                    }

                    if (trade_history_dts_3_list[i] != null) {
                        instant = trade_history_dts_2_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeLookupFrame3Output.getTrade_info()[i].getTrade_history_dts()[2] = new TimestampType(micros);
                    }

                    tTradeLookupFrame3Output.getTrade_info()[i].getTrade_history_status_id()[0] = trade_history_status_id_1_list[i];
                    tTradeLookupFrame3Output.getTrade_info()[i].getTrade_history_status_id()[1] = trade_history_status_id_2_list[i];
                    tTradeLookupFrame3Output.getTrade_info()[i].getTrade_history_status_id()[2] = trade_history_status_id_3_list[i];
                    tTradeLookupFrame3Output.getTrade_info()[i].setTrade_type(t_tt_id_list[i]);
                    tTradeLookupFrame3Output.getTrade_info()[i].setTrade_id(t_id_list[i]);
                }

            }
        } catch (Exception e) {
            LOG.error("Trade lookup f3 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeLookupFrame4Input tTradeLookupFrame4Input, TTradeLookupFrame4Output tTradeLookupFrame4Output) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeLookupFrame4(?, ?)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeLookupFrame4Input.getAcct_id());
            statement.setTimestamp(2, new Timestamp(tTradeLookupFrame4Input.getTrade_dts().getMSTime()));
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sqlStmt.toString());
            } else {
                tTradeLookupFrame4Output.setNum_found(rs.getInt("num_found"));
                tTradeLookupFrame4Output.setNum_trades_found(rs.getInt("num_trades_found"));

                Long[] holding_history_id_list =  (Long[]) rs.getArray("holding_history_id_list").getArray();
                Long[] holding_history_trade_id_list =  (Long[]) rs.getArray("holding_history_trade_id_list").getArray();
                Integer[] quantity_after_list =  (Integer[]) rs.getArray("quantity_after_list").getArray();
                Integer[] quantity_before_list =  (Integer[]) rs.getArray("quantity_before_list").getArray();

                for(int i = 0; i < holding_history_id_list.length; i++){
                    tTradeLookupFrame4Output.getTrade_info()[i] = new TTradeLookupFrame4TradeInfo();
                    tTradeLookupFrame4Output.getTrade_info()[i].setHolding_history_id(holding_history_id_list[i]);
                    tTradeLookupFrame4Output.getTrade_info()[i].setHolding_history_trade_id(holding_history_trade_id_list[i]);
                    tTradeLookupFrame4Output.getTrade_info()[i].setQuantity_after(quantity_after_list[i]);
                    tTradeLookupFrame4Output.getTrade_info()[i].setQuantity_before(quantity_before_list[i]);
                }

            }
        } catch (Exception e) {
            LOG.error("Trade lookup f4 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeOrderFrame1Input tTradeOrderFrame1Input, TTradeOrderFrame1Output tTradeOrderFrame1Output) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeOrderFrame1(?) " +
                "as res(acct_name VARCHAR, broker_name VARCHAR, " +
                "cust_f_name VARCHAR, cust_id BIGINT, cust_l_name VARCHAR, cust_tier SMALLINT, tax_id VARCHAR, tax_status SMALLINT, broker_id BIGINT)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeOrderFrame1Input.getAcct_id());
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sqlStmt.toString());
            } else {
                tTradeOrderFrame1Output.setAcct_name(rs.getString("acct_name"));
                tTradeOrderFrame1Output.setBroker_id(rs.getLong("broker_id"));
                tTradeOrderFrame1Output.setBroker_name(rs.getString("broker_name"));
                tTradeOrderFrame1Output.setCust_f_name(rs.getString("cust_f_name"));
                tTradeOrderFrame1Output.setCust_id(rs.getLong("cust_id"));
                tTradeOrderFrame1Output.setCust_l_name(rs.getString("cust_l_name"));
                tTradeOrderFrame1Output.setCust_tier(rs.getInt("cust_tier"));
                tTradeOrderFrame1Output.setNum_found(1);
                tTradeOrderFrame1Output.setTax_id(rs.getString("tax_id"));
                tTradeOrderFrame1Output.setTax_status(rs.getInt("tax_status"));

            }
        } catch (Exception e) {
            LOG.error("Trade Order frame1 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeOrderFrame2Input tTradeOrderFrame2Input, TTradeOrderFrame2Output tTradeOrderFrame2Output) {
        try {

            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeOrderFrame2(?,?,?,?)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeOrderFrame2Input.getAcct_id());
            statement.setString(2, tTradeOrderFrame2Input.getExec_f_name());
            statement.setString(3, tTradeOrderFrame2Input.getExec_l_name());
            statement.setString(4, tTradeOrderFrame2Input.getExec_tax_id());
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                tTradeOrderFrame2Output.setAp_acl(new String());
            } else {
                if (rs.getInt(1) == 0) {
                    tTradeOrderFrame2Output.setAp_acl("1");
                }
            }
        } catch (Exception e) {
            LOG.error("Trade Order frame2 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeOrderFrame3Input tTradeOrderFrame3Input, TTradeOrderFrame3Output tTradeOrderFrame3Output) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeOrderFrame3(?,?,?,?,?,?,?,?,?,?,?,?,?,?) " +
                "as res(comp_name varchar , required_price FLOAT, symb_name varchar,buy_value FLOAT,charge_amount FLOAT," +
                "comm_rate FLOAT,cust_assets FLOAT,market_price FLOAT,sec_name varchar,sell_value FLOAT,status_id char(4),tax_amount FLOAT," +
                "type_is_market smallint,type_is_sell smallint)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeOrderFrame3Input.getAcct_id());
            statement.setLong(2, tTradeOrderFrame3Input.getCust_id());
            statement.setShort(3, (short) tTradeOrderFrame3Input.getCust_tier());
            statement.setShort(4, (short) tTradeOrderFrame3Input.getIs_lifo());
            statement.setString(5, tTradeOrderFrame3Input.getIssue());
            statement.setString(6, tTradeOrderFrame3Input.getSt_pending_id());
            statement.setString(7, tTradeOrderFrame3Input.getSt_submitted_id());
            statement.setShort(8, (short) tTradeOrderFrame3Input.getTax_status());
            statement.setInt(9, tTradeOrderFrame3Input.getTrade_qty());
            statement.setString(10, tTradeOrderFrame3Input.getTrade_type_id());
            statement.setShort(11, (short) tTradeOrderFrame3Input.getType_is_margin());
            statement.setString(12, tTradeOrderFrame3Input.getCo_name());
            statement.setDouble(13, tTradeOrderFrame3Input.getRequested_price());
            statement.setString(14, tTradeOrderFrame3Input.getSymbol());
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sqlStmt.toString());
            } else {
                tTradeOrderFrame3Output.setCo_name(rs.getString("comp_name"));
                tTradeOrderFrame3Output.setRequested_price(rs.getDouble("required_price"));
                tTradeOrderFrame3Output.setSymbol(rs.getString("symb_name"));
                tTradeOrderFrame3Output.setBuy_value(rs.getDouble("buy_value"));
                tTradeOrderFrame3Output.setCharge_amount(rs.getDouble("charge_amount"));
                tTradeOrderFrame3Output.setComm_rate(rs.getDouble("comm_rate"));
                tTradeOrderFrame3Output.setAcct_assets(rs.getDouble("cust_assets"));
                tTradeOrderFrame3Output.setMarket_price(rs.getDouble("market_price"));
                tTradeOrderFrame3Output.setS_name(rs.getString("sec_name"));
                tTradeOrderFrame3Output.setSell_value(rs.getDouble("sell_value"));
                tTradeOrderFrame3Output.setStatus_id(rs.getString("status_id"));
                tTradeOrderFrame3Output.setTax_amount(rs.getDouble("tax_amount"));
                tTradeOrderFrame3Output.setType_is_market(rs.getInt("type_is_market"));
                tTradeOrderFrame3Output.setType_is_sell(rs.getInt("type_is_sell"));
            }
        } catch (Exception e) {
            LOG.error("Trade Order frame3 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeOrderFrame4Input tTradeOrderFrame4Input, TTradeOrderFrame4Output tTradeOrderFrame4Output) {
        try {

            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeOrderFrame4(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeOrderFrame4Input.getAcct_id());
            statement.setDouble(2, tTradeOrderFrame4Input.getCharge_amount());
            statement.setDouble(3, tTradeOrderFrame4Input.getComm_amount());
            statement.setString(4, tTradeOrderFrame4Input.getExec_name());
            statement.setShort(5, (short) tTradeOrderFrame4Input.getIs_cash());
            statement.setShort(6, (short) tTradeOrderFrame4Input.getIs_lifo());
            statement.setDouble(7, tTradeOrderFrame4Input.getRequested_price());
            statement.setString(8, tTradeOrderFrame4Input.getStatus_id());
            statement.setString(9, tTradeOrderFrame4Input.getSymbol());
            statement.setInt(10, tTradeOrderFrame4Input.getTrade_qty());
            statement.setString(11, tTradeOrderFrame4Input.getTrade_type_id());
            statement.setShort(12, (short) tTradeOrderFrame4Input.getType_is_market());
            statement.setLong(13, tTradeOrderFrame4Input.getBroker_id());

            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sqlStmt.toString());
            } else {
                tTradeOrderFrame4Output.setTrade_id(rs.getLong(1));
            }
        } catch (Exception e) {
            LOG.error("Trade Order frame4 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeResultFrame1Input tTradeResultFrame1Input, TTradeResultFrame1Output tTradeResultFrame1Output) {
        try {

            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeResultFrame1(?) " +
                "as res(acct_id BIGINT,charge FLOAT,holdsum_qty INTEGER,is_lifo smallint, symbol char(15)," +
                "trade_is_cash smallint,trade_qty INTEGER,type_id char(3),type_is_market smallint,type_is_sell smallint,type_name char(12))");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeResultFrame1Input.getTrade_id());
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sqlStmt.toString());
            } else {

                tTradeResultFrame1Output.setNum_found(1);
                tTradeResultFrame1Output.setAcct_id(rs.getLong("acct_id"));
                tTradeResultFrame1Output.setCharge(rs.getDouble("charge"));
                tTradeResultFrame1Output.setHs_qty(rs.getInt("holdsum_qty"));
                tTradeResultFrame1Output.setIs_lifo((int) rs.getShort("is_lifo"));
                tTradeResultFrame1Output.setSymbol(rs.getString("symbol"));
                tTradeResultFrame1Output.setTrade_is_cash(rs.getShort("trade_is_cash"));
                tTradeResultFrame1Output.setTrade_qty(rs.getInt("trade_qty"));
                tTradeResultFrame1Output.setType_id(rs.getString("type_id"));
                tTradeResultFrame1Output.setType_is_market(rs.getShort("type_is_market"));
                tTradeResultFrame1Output.setType_is_sell(rs.getShort("type_is_sell"));
                tTradeResultFrame1Output.setType_name(rs.getString("type_name"));
            }
        } catch (Exception e) {
            LOG.error("Trade result frame1 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeResultFrame2Input tTradeResultFrame2Input, TTradeResultFrame2Output tTradeResultFrame2Output) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeResultFrame2(?, ?, ?, ?, ?, ?, ?, ?)" +
                "as res(broker_id BIGINT,buy_value numeric(12, 2),cust_id BIGINT,sell_value numeric(12, 2),tax_status smallint,trade_dts timestamp)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeResultFrame2Input.getAcct_id());
            statement.setInt(2, tTradeResultFrame2Input.getHs_qty());
            statement.setShort(3, (short) tTradeResultFrame2Input.getIs_lifo());
            statement.setString(4, tTradeResultFrame2Input.getSymbol());
            statement.setLong(5, tTradeResultFrame2Input.getTrade_id());
            statement.setDouble(6, tTradeResultFrame2Input.getTrade_price());
            statement.setInt(7, tTradeResultFrame2Input.getTrade_qty());
            statement.setShort(8, (short) tTradeResultFrame2Input.getType_is_sell());
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for trade result f2" + sqlStmt.toString());
            } else {
                tTradeResultFrame2Output.setBroker_id(rs.getLong("broker_id"));
                tTradeResultFrame2Output.setBuy_value(rs.getDouble("buy_value"));
                tTradeResultFrame2Output.setCust_id(rs.getLong("cust_id"));
                tTradeResultFrame2Output.setSell_value(rs.getDouble("sell_value"));
                tTradeResultFrame2Output.setTax_status(rs.getShort("tax_status"));
                Instant instant = rs.getTimestamp("trade_dts").toInstant();
                long micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                tTradeResultFrame2Output.setTrade_dts(new TimestampType(micros));

            }
        } catch (Exception e) {
            LOG.error("Trade result frame2 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeResultFrame3Input tTradeResultFrame3Input, TTradeResultFrame3Output tTradeResultFrame3Output) {
        try {

            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeResultFrame3(?,?,?,?,?)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setBigDecimal(1, BigDecimal.valueOf(tTradeResultFrame3Input.getBuy_value()));
            statement.setLong(2, tTradeResultFrame3Input.getCust_id());
            statement.setBigDecimal(3, BigDecimal.valueOf(tTradeResultFrame3Input.getSell_value()));
            statement.setLong(4, tTradeResultFrame3Input.getTrade_id());
            statement.setFloat(5, (float) tTradeResultFrame3Output.getTax_amount());
            ResultSet rs = exec(conn, statement);

            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sqlStmt.toString());
            } else {

                tTradeResultFrame3Output.setTax_amount(rs.getDouble(1));
            }
        } catch (Exception e) {
            LOG.error("Trade result frame3 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeResultFrame4Input tTradeResultFrame4Input, TTradeResultFrame4Output tTradeResultFrame4Output) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeResultFrame4(?,?,?,?) " +
                "as res(comm_rate numeric(5, 2), sec_name varchar)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeResultFrame4Input.getCust_id());
            statement.setString(2, tTradeResultFrame4Input.getSymbol());
            statement.setInt(3, tTradeResultFrame4Input.getTrade_qty());
            statement.setString(4, tTradeResultFrame4Input.getType_id());
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for trade result f4" + sqlStmt.toString());
            } else {
                tTradeResultFrame4Output.setComm_rate(rs.getDouble("comm_rate"));
                tTradeResultFrame4Output.setS_name(rs.getString("sec_name"));
            }
        } catch (Exception e) {
            LOG.error("Trade result frame4 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeResultFrame5Input tTradeResultFrame5Input) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeResultFrame5(?, ?, ?, ?, ?, ?)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeResultFrame5Input.getBroker_id());
            statement.setBigDecimal(2, BigDecimal.valueOf(tTradeResultFrame5Input.getComm_amount()));
            statement.setString(3, tTradeResultFrame5Input.getSt_completed_id());
            statement.setTimestamp(4, new Timestamp(tTradeResultFrame5Input.getTrade_dts().getMSTime()));
            statement.setLong(5, tTradeResultFrame5Input.getTrade_id());
            statement.setFloat(6, (float) tTradeResultFrame5Input.getTrade_price());
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for trade result f5" + sqlStmt.toString());
            }
        } catch (Exception e) {
            LOG.error("Trade result frame5 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeResultFrame6Input tTradeResultFrame6Input, TTradeResultFrame6Output tTradeResultFrame6Output) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeResultFrame6(?,?,?,?,?,?,?,?,?)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeResultFrame6Input.getAcct_id());
            statement.setTimestamp(2, new Timestamp(tTradeResultFrame6Input.getDue_date().getMSTime()));
            statement.setString(3, tTradeResultFrame6Input.getS_name());
            statement.setFloat(4, (float) tTradeResultFrame6Input.getSe_amount());
            statement.setTimestamp(5, new Timestamp(tTradeResultFrame6Input.getTrade_dts().getMSTime()));
            statement.setLong(6, tTradeResultFrame6Input.getTrade_id());
            statement.setShort(7, (short) tTradeResultFrame6Input.getTrade_is_cash());
            statement.setInt(8, tTradeResultFrame6Input.getTrade_qty());
            statement.setString(9, tTradeResultFrame6Input.getType_name());
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sqlStmt.toString());
            } else {
                tTradeResultFrame6Output.setAcct_bal(rs.getDouble(1));
            }

        } catch (Exception e) {
            LOG.error("Trade result frame6 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeStatusTxnInput tTradeStatusFrame1Input, TTradeStatusFrame1Output tTradeStatusFrame1Output) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeStatusFrame1(?) ");
//                +
//                "as res(cust_l_name VARCHAR,cust_f_name VARCHAR, broker_name VARCHAR, " +
//                "T_CHRG FLOAT[], T_EXEC_NAME VARCHAR, EX_NAME VARCHAR, S_NAME VARCHAR, ST_NAME CHAR(10), T_S_SYMB CHAR(15), " +
//                "year NUMERIC, month NUMERIC,day NUMERIC, hour NUMERIC, minute NUMERIC, second NUMERIC, T_ID BIGINT, T_QTY INTEGER,TT_NAME CHAR(12)) ");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeStatusFrame1Input.getAcctId());

            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sqlStmt.toString());

            } else {

                tTradeStatusFrame1Output.setBroker_name(rs.getString("broker_name"));
                tTradeStatusFrame1Output.setCust_f_name(rs.getString("cust_f_name"));
                tTradeStatusFrame1Output.setCust_l_name(rs.getString("cust_l_name"));
                tTradeStatusFrame1Output.setNum_found(rs.getInt("num_found"));


                BigDecimal[] charge_list = (BigDecimal[]) rs.getArray("charge_list").getArray();
                for (int i = 0; i < charge_list.length; i++) {
                    tTradeStatusFrame1Output.getCharge()[i] = charge_list[i].doubleValue();
                }

                String[] exec_name_list = (String[]) rs.getArray("exec_name_list").getArray();
                for (int i = 0; i < exec_name_list.length; i++) {
                    tTradeStatusFrame1Output.getExec_name()[i] = exec_name_list[i];
                }

                String[] ex_name_list = (String[]) rs.getArray("ex_name_list").getArray();
                for (int i = 0; i < ex_name_list.length; i++) {
                    tTradeStatusFrame1Output.getEx_name()[i] = ex_name_list[i];
                }

                String[] s_name_list = (String[]) rs.getArray("s_name_list").getArray();
                for (int i = 0; i < s_name_list.length; i++) {
                    tTradeStatusFrame1Output.getS_name()[i] = s_name_list[i];
                }

                String[] st_name_list = (String[]) rs.getArray("st_name_list").getArray();
                for (int i = 0; i < st_name_list.length; i++) {
                    tTradeStatusFrame1Output.getStatus_name()[i] = st_name_list[i];
                }

                String[] symbol = (String[]) rs.getArray("symbol").getArray();
                for (int i = 0; i < symbol.length; i++) {
                    tTradeStatusFrame1Output.getSymbol()[i] = symbol[i];
                }

                Timestamp[] trade_dts_list = (Timestamp[]) rs.getArray("trade_dts_list").getArray();
                for (int i = 0; i < symbol.length; i++) {
                    Instant instant = trade_dts_list[i].toInstant();
                    long micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                    tTradeStatusFrame1Output.getTrade_dts()[i] = new TimestampType(micros);
                }

                Long[] trade_id_list = (Long[]) rs.getArray("trade_id_list").getArray();
                for (int i = 0; i < trade_id_list.length; i++) {
                    tTradeStatusFrame1Output.getTrade_id()[i] = trade_id_list[i];
                }

                Integer[] trade_qty_list = (Integer[]) rs.getArray("trade_qty_list").getArray();
                for (int i = 0; i < trade_qty_list.length; i++) {
                    tTradeStatusFrame1Output.getTrade_qty()[i] = trade_qty_list[i];
                }

                String[] type_name_list = (String[]) rs.getArray("type_name_list").getArray();
                for (int i = 0; i < type_name_list.length; i++) {
                    tTradeStatusFrame1Output.getType_name()[i] = type_name_list[i];
                }

            }
        } catch (Exception e) {
            LOG.error("Trade Status frame1 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeUpdateFrame1Input tTradeUpdateFrame1Input, TTradeUpdateFrame1Output tTradeUpdateFrame1Output) {
        try {

            StringBuilder osTrades = new StringBuilder();
            osTrades.append("{");
            osTrades.append(tTradeUpdateFrame1Input.getTrade_id()[0]);
            for (int i = 1; i < tTradeUpdateFrame1Input.getTrade_id().length; i++) {
                osTrades.append(",");
                osTrades.append(tTradeUpdateFrame1Input.getTrade_id()[i]);
            }
            osTrades.append("}");


            String sql = String.format("SELECT * FROM TradeUpdateFrame1('%d','%d','%s')", tTradeUpdateFrame1Input.getMax_trades(),
                tTradeUpdateFrame1Input.getMax_updates(), osTrades.toString());

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sql.toString());
            } else {
                tTradeUpdateFrame1Output.setNum_updated(rs.getInt("num_updated"));
                tTradeUpdateFrame1Output.setNum_found(rs.getInt("num_found"));
                BigDecimal[] bid_price_list = (BigDecimal[]) rs.getArray("bid_price_list").getArray();

                BigDecimal[] cash_transaction_amount_list = (BigDecimal[]) rs.getArray("cash_transaction_amount_list").getArray();
                Timestamp[] cash_transaction_dts_list = (Timestamp[]) rs.getArray("cash_transaction_dts_list").getArray();

                String[] cash_transaction_name_list = (String[]) rs.getArray("cash_transaction_name_list").getArray();

                String[] exec_name_list = (String[]) rs.getArray("exec_name_list").getArray();
                Short[] is_cash_list = (Short[]) rs.getArray("is_cash_list").getArray();
                Short[] is_market_list = (Short[]) rs.getArray("is_market_list").getArray();
                BigDecimal[] settlement_amount_list = (BigDecimal[]) rs.getArray("settlement_amount_list").getArray();
                Timestamp[] settlement_cash_due_date_list = (Timestamp[]) rs.getArray("settlement_cash_due_date_list").getArray();
                String[] settlement_cash_type_list = (String[]) rs.getArray("settlement_cash_type_list").getArray();
                Timestamp[] trade_history_dts_1_list = (Timestamp[]) rs.getArray("trade_history_dts_1_list").getArray();
                Timestamp[] trade_history_dts_2_list = (Timestamp[]) rs.getArray("trade_history_dts_2_list").getArray();
                Timestamp[] trade_history_dts_3_list = (Timestamp[]) rs.getArray("trade_history_dts_3_list").getArray();

                String[] trade_history_status_id_1_list = (String[]) rs.getArray("trade_history_status_id_1_list").getArray();
                String[] trade_history_status_id_2_list = (String[]) rs.getArray("trade_history_status_id_2_list").getArray();
                String[] trade_history_status_id_3_list = (String[]) rs.getArray("trade_history_status_id_3_list").getArray();
                BigDecimal[] trade_price_list = (BigDecimal[]) rs.getArray("trade_price_list").getArray();


                for (int i = 0; i < bid_price_list.length; i++) {
                    //time
                    Instant instant = null;
                    long micros = 0L;

                    if(cash_transaction_dts_list[i] != null){
                        instant = cash_transaction_dts_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeUpdateFrame1Output.getTrade_info()[i].setCash_transaction_amount(cash_transaction_amount_list[i].doubleValue());
                        tTradeUpdateFrame1Output.getTrade_info()[i].setCash_transaction_dts(new TimestampType(micros));
                        tTradeUpdateFrame1Output.getTrade_info()[i].setCash_transaction_name(cash_transaction_name_list[i]);
                    }
                    tTradeUpdateFrame1Output.getTrade_info()[i].setBid_price(bid_price_list[i].doubleValue());

                    tTradeUpdateFrame1Output.getTrade_info()[i].setExec_name(exec_name_list[i]);
                    tTradeUpdateFrame1Output.getTrade_info()[i].setIs_cash(is_cash_list[i] != 0);
                    tTradeUpdateFrame1Output.getTrade_info()[i].setIs_market(is_market_list[i] != 0);
                    tTradeUpdateFrame1Output.getTrade_info()[i].setSettlement_amount(settlement_amount_list[i].doubleValue());

                    instant = settlement_cash_due_date_list[i].toInstant();
                    micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                    tTradeUpdateFrame1Output.getTrade_info()[i].setSettlement_cash_due_date(new TimestampType(micros));
                    tTradeUpdateFrame1Output.getTrade_info()[i].setSettlement_cash_type(settlement_cash_type_list[i]);

                    if(trade_history_dts_1_list[i] != null){
                        instant = trade_history_dts_1_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeUpdateFrame1Output.getTrade_info()[i].getTrade_history_dts()[0] = new TimestampType(micros);
                    }


                    if(trade_history_dts_2_list[i] != null){
                        instant = trade_history_dts_2_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeUpdateFrame1Output.getTrade_info()[i].getTrade_history_dts()[1] = new TimestampType(micros);
                    }


                    if(trade_history_dts_3_list[i] != null){
                        instant = trade_history_dts_3_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeUpdateFrame1Output.getTrade_info()[i].getTrade_history_dts()[2] = new TimestampType(micros);
                    }


                    tTradeUpdateFrame1Output.getTrade_info()[i].getTrade_history_status_id()[0] = trade_history_status_id_1_list[i];
                    tTradeUpdateFrame1Output.getTrade_info()[i].getTrade_history_status_id()[1] = trade_history_status_id_2_list[i];
                    tTradeUpdateFrame1Output.getTrade_info()[i].getTrade_history_status_id()[2] = trade_history_status_id_3_list[i];
                    tTradeUpdateFrame1Output.getTrade_info()[i].setTrade_price(trade_price_list[i].doubleValue());

                }


            }
        } catch (Exception e) {
            LOG.error("Trade update frame1 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeUpdateFrame2Input tTradeUpdateFrame2Input, TTradeUpdateFrame2Output tTradeUpdateFrame2Output) {
        try {
            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeUpdateFrame2(?, ?, ?, ?, ?)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeUpdateFrame2Input.getAcct_id());
            statement.setInt(2, tTradeUpdateFrame2Input.getMax_trades());
            statement.setInt(3, tTradeUpdateFrame2Input.getMax_updates());
            statement.setTimestamp(4, new Timestamp(tTradeUpdateFrame2Input.getStart_trade_dts().getMSTime()));
            statement.setTimestamp(5, new Timestamp(tTradeUpdateFrame2Input.getEnd_trade_dts().getMSTime()));
            ResultSet rs = exec(conn, statement);
            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sqlStmt.toString());
            } else {
                tTradeUpdateFrame2Output.setNum_found(tTradeUpdateFrame2Input.getMax_trades());
                tTradeUpdateFrame2Output.setNum_updated(rs.getInt("num_updated"));
                BigDecimal[] bid_price_list = (BigDecimal[]) rs.getArray("bid_price_list").getArray();
                BigDecimal[] cash_transaction_amount_list = (BigDecimal[]) rs.getArray("cash_transaction_amount_list").getArray();
                Timestamp[] cash_transaction_dts_list = (Timestamp[]) rs.getArray("cash_transaction_dts_list").getArray();
                String[] cash_transaction_name_list = (String[]) rs.getArray("cash_transaction_name_list").getArray();
                String[] exec_name_list = (String[]) rs.getArray("exec_name_list").getArray();
                Short[] is_cash_list = (Short[]) rs.getArray("is_cash_list").getArray();


                BigDecimal[] settlement_amount_list = (BigDecimal[]) rs.getArray("settlement_amount_list").getArray();
                Timestamp[] settlement_cash_due_date_list = (Timestamp[]) rs.getArray("settlement_cash_due_date_list").getArray();
                String[] settlement_cash_type_list = (String[]) rs.getArray("settlement_cash_type_list").getArray();
                Timestamp[] trade_history_dts_1_list = (Timestamp[]) rs.getArray("trade_history_dts_1_list").getArray();
                Timestamp[] trade_history_dts_2_list = (Timestamp[]) rs.getArray("trade_history_dts_2_list").getArray();
                Timestamp[] trade_history_dts_3_list = (Timestamp[]) rs.getArray("trade_history_dts_3_list").getArray();

                String[] trade_history_status_id_1_list = (String[]) rs.getArray("trade_history_status_id_1_list").getArray();
                String[] trade_history_status_id_2_list = (String[]) rs.getArray("trade_history_status_id_2_list").getArray();
                String[] trade_history_status_id_3_list = (String[]) rs.getArray("trade_history_status_id_3_list").getArray();
                BigDecimal[] trade_price_list = (BigDecimal[]) rs.getArray("trade_price_list").getArray();


                for (int i = 0; i < bid_price_list.length; i++) {
                    //time
                    Instant instant = null;
                    long micros = 0L;

                    tTradeUpdateFrame2Output.getTrade_info()[i].setBid_price(bid_price_list[i].doubleValue());

                    if(cash_transaction_dts_list[i] != null){
                        instant = cash_transaction_dts_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeUpdateFrame2Output.getTrade_info()[i].setCash_transaction_amount(cash_transaction_amount_list[i].doubleValue());
                        tTradeUpdateFrame2Output.getTrade_info()[i].setCash_transaction_dts(new TimestampType(micros));
                        tTradeUpdateFrame2Output.getTrade_info()[i].setCash_transaction_name(cash_transaction_name_list[i]);
                    }

                    tTradeUpdateFrame2Output.getTrade_info()[i].setExec_name(exec_name_list[i]);
                    tTradeUpdateFrame2Output.getTrade_info()[i].setIs_cash(is_cash_list[i] != 0);
                    tTradeUpdateFrame2Output.getTrade_info()[i].setSettlement_amount(settlement_amount_list[i].doubleValue());

                    instant = settlement_cash_due_date_list[i].toInstant();
                    micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                    tTradeUpdateFrame2Output.getTrade_info()[i].setSettlement_cash_due_date(new TimestampType(micros));
                    tTradeUpdateFrame2Output.getTrade_info()[i].setSettlement_cash_type(settlement_cash_type_list[i]);

                    if(trade_history_dts_1_list[i] != null){
                        instant = trade_history_dts_1_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeUpdateFrame2Output.getTrade_info()[i].getTrade_history_dts()[0] = new TimestampType(micros);
                    }

                    if(trade_history_dts_2_list[i] != null){
                        instant = trade_history_dts_2_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeUpdateFrame2Output.getTrade_info()[i].getTrade_history_dts()[1] = new TimestampType(micros);
                    }

                    if (trade_history_dts_3_list[i] != null){
                        instant = trade_history_dts_3_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeUpdateFrame2Output.getTrade_info()[i].getTrade_history_dts()[2] = new TimestampType(micros);
                    }


                    tTradeUpdateFrame2Output.getTrade_info()[i].getTrade_history_status_id()[0] = trade_history_status_id_1_list[i];
                    tTradeUpdateFrame2Output.getTrade_info()[i].getTrade_history_status_id()[1] = trade_history_status_id_2_list[i];
                    tTradeUpdateFrame2Output.getTrade_info()[i].getTrade_history_status_id()[2] = trade_history_status_id_3_list[i];
                    tTradeUpdateFrame2Output.getTrade_info()[i].setTrade_price(trade_price_list[i].doubleValue());

                }
            }
        } catch (Exception e) {
            LOG.error("Trade update frame2 roll back exception" + e);
        }
    }

    @Override
    public void execute(Connection conn, TTradeUpdateFrame3Input tTradeUpdateFrame3Input, TTradeUpdateFrame3Output tTradeUpdateFrame3Output) {
        try {

            SQLStmt sqlStmt = new SQLStmt("SELECT * FROM TradeUpdateFrame3(?,?,?,?,?,?)");
            PreparedStatement statement = this.getPreparedStatement(conn, sqlStmt);
            statement.setLong(1, tTradeUpdateFrame3Input.getMax_acct_id());
            statement.setInt(2, tTradeUpdateFrame3Input.getMax_trades());
            statement.setInt(3, tTradeUpdateFrame3Input.getMax_updates());
            statement.setTimestamp(4, new Timestamp(tTradeUpdateFrame3Input.getStart_trade_dts().getMSTime()));
            statement.setTimestamp(5, new Timestamp(tTradeUpdateFrame3Input.getEnd_trade_dts().getMSTime()));
            statement.setString(6, tTradeUpdateFrame3Input.getSymbol());

            ResultSet rs = exec(conn, statement);

            if (rs == null || !rs.next()) {
                throw new SQLException("no result for cleanup" + sqlStmt.toString());
            } else {

                tTradeUpdateFrame3Output.setNum_updated(rs.getInt("num_updated"));
                if (tTradeUpdateFrame3Output.getNum_updated() == 0) {
                    return;
                }
                tTradeUpdateFrame3Output.setNum_found(tTradeUpdateFrame3Input.getMax_trades());
                Array t_ca_id_list = rs.getArray("t_ca_id_list");
                Long[] acc = (Long[]) t_ca_id_list.getArray();
                BigDecimal[] cash_transaction_amount_list = (BigDecimal[]) rs.getArray("cash_transaction_amount_list").getArray();
                Timestamp[] cash_transaction_dts_list = (Timestamp[]) rs.getArray("cash_transaction_dts_list").getArray();
                String[] cash_transaction_name_list = (String[]) rs.getArray("cash_transaction_name_list").getArray();
                String[] exec_name_list = (String[]) rs.getArray("exec_name_list").getArray();

                Short[] is_cash_list = (Short[]) rs.getArray("is_cash_list").getArray();
                BigDecimal[] trade_price_list = (BigDecimal[]) rs.getArray("trade_price_list").getArray();
                Integer[] t_qty_list = (Integer[]) rs.getArray("t_qty_list").getArray();
                String[] s_name_list = (String[]) rs.getArray("s_name_list").getArray();
                BigDecimal[] settlement_amount_list = (BigDecimal[]) rs.getArray("settlement_amount_list").getArray();
                Timestamp[] settlement_cash_due_date_list = (Timestamp[]) rs.getArray("settlement_cash_due_date_list").getArray();
                String[] settlement_cash_type_list = (String[]) rs.getArray("settlement_cash_type_list").getArray();
                Timestamp[] t_dts_list = (Timestamp[]) rs.getArray("t_dts_list").getArray();
                Timestamp[] trade_history_dts_1_list = (Timestamp[]) rs.getArray("trade_history_dts_1_list").getArray();
                Timestamp[] trade_history_dts_2_list = (Timestamp[]) rs.getArray("trade_history_dts_2_list").getArray();


                Timestamp[] trade_history_dts_3_list = (Timestamp[]) rs.getArray("trade_history_dts_3_list").getArray();

                String[] trade_history_status_id_1_list = (String[]) rs.getArray("trade_history_status_id_1_list").getArray();
                String[] trade_history_status_id_2_list = (String[]) rs.getArray("trade_history_status_id_2_list").getArray();
                String[] trade_history_status_id_3_list = (String[]) rs.getArray("trade_history_status_id_3_list").getArray();
                String[] t_tt_id_list = (String[]) rs.getArray("t_tt_id_list").getArray();
                Long[] t_id_list = (Long[]) rs.getArray("t_id_list").getArray();
                String[] tt_name_list = (String[]) rs.getArray("tt_name_list").getArray();


                for (int i = 0; i < cash_transaction_amount_list.length; i++) {
                    Instant instant = null;
                    long micros = 0L;

                    if(cash_transaction_dts_list[i] != null){
                        instant = cash_transaction_dts_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeUpdateFrame3Output.getTrade_info()[i].setAcct_id(acc[i]);
                        tTradeUpdateFrame3Output.getTrade_info()[i].setCash_transaction_amount(cash_transaction_amount_list[i].doubleValue());
                        tTradeUpdateFrame3Output.getTrade_info()[i].setCash_transaction_dts(new TimestampType(micros));;
                    }


                    tTradeUpdateFrame3Output.getTrade_info()[i].setCash_transaction_name(cash_transaction_name_list[i]);
                    tTradeUpdateFrame3Output.getTrade_info()[i].setExec_name(exec_name_list[i]);
                    tTradeUpdateFrame3Output.getTrade_info()[i].setIs_cash(is_cash_list[i] != 0);
                    tTradeUpdateFrame3Output.getTrade_info()[i].setPrice(trade_price_list[i].doubleValue());
                    tTradeUpdateFrame3Output.getTrade_info()[i].setQuantity(t_qty_list[i]);
                    tTradeUpdateFrame3Output.getTrade_info()[i].setS_name(s_name_list[i]);

                    tTradeUpdateFrame3Output.getTrade_info()[i].setSettlement_amount(settlement_amount_list[i].doubleValue());

                    instant = settlement_cash_due_date_list[i].toInstant();
                    micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                    tTradeUpdateFrame3Output.getTrade_info()[i].setSettlement_cash_due_date(new TimestampType(micros));
                    tTradeUpdateFrame3Output.getTrade_info()[i].setSettlement_cash_type(settlement_cash_type_list[i]);

                    instant = t_dts_list[i].toInstant();
                    micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                    tTradeUpdateFrame3Output.getTrade_info()[i].setTrade_dts(new TimestampType(micros));

                    if (trade_history_dts_1_list[i] != null) {
                        instant = trade_history_dts_1_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeUpdateFrame3Output.getTrade_info()[i].getTrade_history_dts()[0] = new TimestampType(micros);
                    }

                    if (trade_history_dts_2_list[i] != null) {
                        instant = trade_history_dts_2_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeUpdateFrame3Output.getTrade_info()[i].getTrade_history_dts()[1] = new TimestampType(micros);

                    }

                    if (trade_history_dts_3_list[i] != null) {
                        instant = trade_history_dts_2_list[i].toInstant();
                        micros = TimeUnit.SECONDS.toMicros(instant.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(instant.getNano());
                        tTradeUpdateFrame3Output.getTrade_info()[i].getTrade_history_dts()[2] = new TimestampType(micros);
                    }

                    tTradeUpdateFrame3Output.getTrade_info()[i].getTrade_history_status_id()[0] = trade_history_status_id_1_list[i];
                    tTradeUpdateFrame3Output.getTrade_info()[i].getTrade_history_status_id()[1] = trade_history_status_id_2_list[i];
                    tTradeUpdateFrame3Output.getTrade_info()[i].getTrade_history_status_id()[2] = trade_history_status_id_3_list[i];
                    tTradeUpdateFrame3Output.getTrade_info()[i].setTrade_type(t_tt_id_list[i]);
                    tTradeUpdateFrame3Output.getTrade_info()[i].setTrade_id(t_id_list[i]);
                    tTradeUpdateFrame3Output.getTrade_info()[i].setType_name(tt_name_list[i]);
                }

            }
        } catch (Exception e) {
            LOG.error("Trade update frame3 roll back exception" + e);
        }
    }


    @Override
    public void rollback() {

    }


    @Override
    public void setReadCommitted() {

    }

    @Override
    public void setReadUncommitted() {

    }

    @Override
    public void setRepeatableRead() {

    }

    @Override
    public void setSerializable() {

    }

    public final PreparedStatement getPreparedStatement(Connection conn, SQLStmt stmt, Object... params) throws SQLException {
        PreparedStatement pStmt = this.getPreparedStatementReturnKeys(conn, stmt, null);
        for (int i = 0; i < params.length; i++) {
            pStmt.setObject(i + 1, params[i]);
        }
        return (pStmt);
    }

    /**
     * Return a PreparedStatement for the given SQLStmt handle
     * The underlying Procedure API will make sure that the proper SQL
     * for the target DBMS is used for this SQLStmt.
     *
     * @param conn
     * @param stmt
     * @param is
     * @return
     * @throws SQLException
     */
    public final PreparedStatement getPreparedStatementReturnKeys(Connection conn, SQLStmt stmt, int[] is) throws SQLException {

        PreparedStatement pStmt = conn.prepareStatement(stmt.getSQL());
        return (pStmt);
    }


}
