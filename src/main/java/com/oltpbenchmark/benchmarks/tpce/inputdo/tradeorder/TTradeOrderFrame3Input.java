package com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder;

public class TTradeOrderFrame3Input {
    double requested_price;                // IN-OUT parameter
    long acct_id;
    long cust_id;
    int cust_tier;
    int is_lifo;
    int tax_status;
    int trade_qty;
    int type_is_margin;
    String co_name;       // IN-OUT parameter
    String issue;
    String st_pending_id;
    String st_submitted_id;
    String symbol;     // IN-OUT parameter
    String trade_type_id;

    public TTradeOrderFrame3Input() {
        co_name = new String();
        issue = new String();
        st_pending_id = new String();
        st_submitted_id = new String();
        symbol = new String();
        trade_type_id = new String();
    }

    public double getRequested_price() {
        return requested_price;
    }

    public void setRequested_price(double requested_price) {
        this.requested_price = requested_price;
    }

    public long getAcct_id() {
        return acct_id;
    }

    public void setAcct_id(long acct_id) {
        this.acct_id = acct_id;
    }

    public long getCust_id() {
        return cust_id;
    }

    public void setCust_id(long cust_id) {
        this.cust_id = cust_id;
    }

    public int getCust_tier() {
        return cust_tier;
    }

    public void setCust_tier(int cust_tier) {
        this.cust_tier = cust_tier;
    }

    public int getIs_lifo() {
        return is_lifo;
    }

    public void setIs_lifo(int is_lifo) {
        this.is_lifo = is_lifo;
    }

    public int getTax_status() {
        return tax_status;
    }

    public void setTax_status(int tax_status) {
        this.tax_status = tax_status;
    }

    public int getTrade_qty() {
        return trade_qty;
    }

    public void setTrade_qty(int trade_qty) {
        this.trade_qty = trade_qty;
    }

    public int getType_is_margin() {
        return type_is_margin;
    }

    public void setType_is_margin(int type_is_margin) {
        this.type_is_margin = type_is_margin;
    }

    public String getCo_name() {
        return co_name;
    }

    public void setCo_name(String co_name) {
        this.co_name = co_name;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getSt_pending_id() {
        return st_pending_id;
    }

    public void setSt_pending_id(String st_pending_id) {
        this.st_pending_id = st_pending_id;
    }

    public String getSt_submitted_id() {
        return st_submitted_id;
    }

    public void setSt_submitted_id(String st_submitted_id) {
        this.st_submitted_id = st_submitted_id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTrade_type_id() {
        return trade_type_id;
    }

    public void setTrade_type_id(String trade_type_id) {
        this.trade_type_id = trade_type_id;
    }
}
