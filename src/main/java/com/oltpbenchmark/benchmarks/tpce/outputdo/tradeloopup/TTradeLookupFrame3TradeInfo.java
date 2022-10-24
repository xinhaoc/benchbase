package com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.GregorianCalendar;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.TradeLookupMaxTradeHistoryRowsReturned;

public class TTradeLookupFrame3TradeInfo {

    double              cash_transaction_amount;
    double              price;
    double              settlement_amount;
    long              acct_id;
    long              trade_id;
    int               quantity;
    boolean                is_cash;
    TimestampType[] trade_history_dts;
    TimestampType    cash_transaction_dts;
    TimestampType    settlement_cash_due_date;
    TimestampType    trade_dts;
    String[]                trade_history_status_id;
    String                cash_transaction_name;
    String                exec_name;
    String                settlement_cash_type;
    String                trade_type;
    int[]        trade_history_dts_ind;
    int[]        trade_history_status_id_ind;
    int        acct_id_ind;
    int        cash_transaction_amount_ind;
    int        cash_transaction_dts_ind;
    int        cash_transaction_name_ind;
    int        exec_name_ind;
    int        is_cash_ind;
    int        price_ind;
    int        quantity_ind;
    int        settlement_amount_ind;
    int        settlement_cash_due_date_ind;
    int        settlement_cash_type_ind;
    int        trade_dts_ind;
    int        trade_id_ind;
    int        trade_type_ind;

    public TTradeLookupFrame3TradeInfo() {
        trade_history_dts = new TimestampType[TradeLookupMaxTradeHistoryRowsReturned];
        for (int i = 0; i < TradeLookupMaxTradeHistoryRowsReturned; i++)
            trade_history_dts[i] =
                new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
        cash_transaction_dts = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
        settlement_cash_due_date = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
        trade_dts = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());

        trade_history_status_id = new String[TradeLookupMaxTradeHistoryRowsReturned];
        for(int i = 0; i < TradeLookupMaxTradeHistoryRowsReturned; i++){
            trade_history_status_id[i] = new String();
        }
        cash_transaction_name = new String();
        exec_name = new String();
        settlement_cash_type = new String();
        trade_type = new String();
        trade_history_dts_ind = new int[TradeLookupMaxTradeHistoryRowsReturned];
        trade_history_status_id_ind = new int[TradeLookupMaxTradeHistoryRowsReturned];
    }

    public double getCash_transaction_amount() {
        return cash_transaction_amount;
    }

    public void setCash_transaction_amount(double cash_transaction_amount) {
        this.cash_transaction_amount = cash_transaction_amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSettlement_amount() {
        return settlement_amount;
    }

    public void setSettlement_amount(double settlement_amount) {
        this.settlement_amount = settlement_amount;
    }

    public long getAcct_id() {
        return acct_id;
    }

    public void setAcct_id(long acct_id) {
        this.acct_id = acct_id;
    }

    public long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long trade_id) {
        this.trade_id = trade_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public TimestampType getTrade_dts() {
        return trade_dts;
    }

    public void setTrade_dts(TimestampType trade_dts) {
        this.trade_dts = trade_dts;
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

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
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

    public int getAcct_id_ind() {
        return acct_id_ind;
    }

    public void setAcct_id_ind(int acct_id_ind) {
        this.acct_id_ind = acct_id_ind;
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

    public int getPrice_ind() {
        return price_ind;
    }

    public void setPrice_ind(int price_ind) {
        this.price_ind = price_ind;
    }

    public int getQuantity_ind() {
        return quantity_ind;
    }

    public void setQuantity_ind(int quantity_ind) {
        this.quantity_ind = quantity_ind;
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

    public int getTrade_dts_ind() {
        return trade_dts_ind;
    }

    public void setTrade_dts_ind(int trade_dts_ind) {
        this.trade_dts_ind = trade_dts_ind;
    }

    public int getTrade_id_ind() {
        return trade_id_ind;
    }

    public void setTrade_id_ind(int trade_id_ind) {
        this.trade_id_ind = trade_id_ind;
    }

    public int getTrade_type_ind() {
        return trade_type_ind;
    }

    public void setTrade_type_ind(int trade_type_ind) {
        this.trade_type_ind = trade_type_ind;
    }
}
