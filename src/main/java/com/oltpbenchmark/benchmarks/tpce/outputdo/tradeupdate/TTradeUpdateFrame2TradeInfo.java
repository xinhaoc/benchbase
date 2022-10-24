package com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.GregorianCalendar;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.TradeLookupMaxTradeHistoryRowsReturned;
import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.TradeUpdateMaxTradeHistoryRowsReturned;

public class TTradeUpdateFrame2TradeInfo {
    double bid_price;
    double cash_transaction_amount;
    double settlement_amount;
    double trade_price;
    long trade_id;
    boolean is_cash;
    TimestampType[] trade_history_dts;
    TimestampType cash_transaction_dts;
    TimestampType settlement_cash_due_date;
    String[] trade_history_status_id;
    String cash_transaction_name;
    String exec_name;
    String settlement_cash_type;
    int[] trade_history_dts_ind;
    int[] trade_history_status_id_ind;
    int bid_price_ind;
    int cash_transaction_amount_ind;
    int cash_transaction_dts_ind;
    int cash_transaction_name_ind;
    int exec_name_ind;
    int is_cash_ind;
    int settlement_amount_ind;
    int settlement_cash_due_date_ind;
    int settlement_cash_type_ind;
    int trade_id_ind;
    int trade_price_ind;

    public TTradeUpdateFrame2TradeInfo() {

        trade_history_dts = new TimestampType[TradeUpdateMaxTradeHistoryRowsReturned];
        for (int i = 0; i < TradeUpdateMaxTradeHistoryRowsReturned; i++)
            trade_history_dts[i] =
                new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
        cash_transaction_dts = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
        settlement_cash_due_date = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());


        trade_history_status_id = new String[TradeUpdateMaxTradeHistoryRowsReturned];
        for (int i = 0; i < TradeUpdateMaxTradeHistoryRowsReturned; i++) trade_history_status_id[i] = new String();
        cash_transaction_name = new String();
        exec_name = new String();
        settlement_cash_type = new String();
        trade_history_dts_ind = new int[TradeUpdateMaxTradeHistoryRowsReturned];
        trade_history_status_id_ind = new int[TradeUpdateMaxTradeHistoryRowsReturned];
    }

    public double getBid_price() {
        return bid_price;
    }

    public void setBid_price(double bid_price) {
        this.bid_price = bid_price;
    }

    public double getCash_transaction_amount() {
        return cash_transaction_amount;
    }

    public void setCash_transaction_amount(double cash_transaction_amount) {
        this.cash_transaction_amount = cash_transaction_amount;
    }

    public double getSettlement_amount() {
        return settlement_amount;
    }

    public void setSettlement_amount(double settlement_amount) {
        this.settlement_amount = settlement_amount;
    }

    public double getTrade_price() {
        return trade_price;
    }

    public void setTrade_price(double trade_price) {
        this.trade_price = trade_price;
    }

    public long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long trade_id) {
        this.trade_id = trade_id;
    }

    public boolean isIs_cash() {
        return is_cash;
    }

    public void setIs_cash(boolean is_cash) {
        this.is_cash = is_cash;
    }

    public TimestampType[] getTrade_history_dts() {
        return trade_history_dts;
    }

    public void setTrade_history_dts(TimestampType[] trade_history_dts) {
        this.trade_history_dts = trade_history_dts;
    }

    public TimestampType getCash_transaction_dts() {
        return cash_transaction_dts;
    }

    public void setCash_transaction_dts(TimestampType cash_transaction_dts) {
        this.cash_transaction_dts = cash_transaction_dts;
    }

    public TimestampType getSettlement_cash_due_date() {
        return settlement_cash_due_date;
    }

    public void setSettlement_cash_due_date(TimestampType settlement_cash_due_date) {
        this.settlement_cash_due_date = settlement_cash_due_date;
    }

    public String[] getTrade_history_status_id() {
        return trade_history_status_id;
    }

    public void setTrade_history_status_id(String[] trade_history_status_id) {
        this.trade_history_status_id = trade_history_status_id;
    }

    public String getCash_transaction_name() {
        return cash_transaction_name;
    }

    public void setCash_transaction_name(String cash_transaction_name) {
        this.cash_transaction_name = cash_transaction_name;
    }

    public String getExec_name() {
        return exec_name;
    }

    public void setExec_name(String exec_name) {
        this.exec_name = exec_name;
    }

    public String getSettlement_cash_type() {
        return settlement_cash_type;
    }

    public void setSettlement_cash_type(String settlement_cash_type) {
        this.settlement_cash_type = settlement_cash_type;
    }

    public int[] getTrade_history_dts_ind() {
        return trade_history_dts_ind;
    }

    public void setTrade_history_dts_ind(int[] trade_history_dts_ind) {
        this.trade_history_dts_ind = trade_history_dts_ind;
    }

    public int[] getTrade_history_status_id_ind() {
        return trade_history_status_id_ind;
    }

    public void setTrade_history_status_id_ind(int[] trade_history_status_id_ind) {
        this.trade_history_status_id_ind = trade_history_status_id_ind;
    }

    public int getBid_price_ind() {
        return bid_price_ind;
    }

    public void setBid_price_ind(int bid_price_ind) {
        this.bid_price_ind = bid_price_ind;
    }

    public int getCash_transaction_amount_ind() {
        return cash_transaction_amount_ind;
    }

    public void setCash_transaction_amount_ind(int cash_transaction_amount_ind) {
        this.cash_transaction_amount_ind = cash_transaction_amount_ind;
    }

    public int getCash_transaction_dts_ind() {
        return cash_transaction_dts_ind;
    }

    public void setCash_transaction_dts_ind(int cash_transaction_dts_ind) {
        this.cash_transaction_dts_ind = cash_transaction_dts_ind;
    }

    public int getCash_transaction_name_ind() {
        return cash_transaction_name_ind;
    }

    public void setCash_transaction_name_ind(int cash_transaction_name_ind) {
        this.cash_transaction_name_ind = cash_transaction_name_ind;
    }

    public int getExec_name_ind() {
        return exec_name_ind;
    }

    public void setExec_name_ind(int exec_name_ind) {
        this.exec_name_ind = exec_name_ind;
    }

    public int getIs_cash_ind() {
        return is_cash_ind;
    }

    public void setIs_cash_ind(int is_cash_ind) {
        this.is_cash_ind = is_cash_ind;
    }

    public int getSettlement_amount_ind() {
        return settlement_amount_ind;
    }

    public void setSettlement_amount_ind(int settlement_amount_ind) {
        this.settlement_amount_ind = settlement_amount_ind;
    }

    public int getSettlement_cash_due_date_ind() {
        return settlement_cash_due_date_ind;
    }

    public void setSettlement_cash_due_date_ind(int settlement_cash_due_date_ind) {
        this.settlement_cash_due_date_ind = settlement_cash_due_date_ind;
    }

    public int getSettlement_cash_type_ind() {
        return settlement_cash_type_ind;
    }

    public void setSettlement_cash_type_ind(int settlement_cash_type_ind) {
        this.settlement_cash_type_ind = settlement_cash_type_ind;
    }

    public int getTrade_id_ind() {
        return trade_id_ind;
    }

    public void setTrade_id_ind(int trade_id_ind) {
        this.trade_id_ind = trade_id_ind;
    }

    public int getTrade_price_ind() {
        return trade_price_ind;
    }

    public void setTrade_price_ind(int trade_price_ind) {
        this.trade_price_ind = trade_price_ind;
    }
}
