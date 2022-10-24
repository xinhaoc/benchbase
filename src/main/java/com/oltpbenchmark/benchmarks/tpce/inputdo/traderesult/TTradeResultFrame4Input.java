package com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult;

public class TTradeResultFrame4Input {
    long cust_id;
    int trade_qty;
    String symbol;
    String type_id;

    public TTradeResultFrame4Input() {
        symbol = new String();
        type_id = new String();
    }

    public long getCust_id() {
        return cust_id;
    }

    public void setCust_id(long cust_id) {
        this.cust_id = cust_id;
    }

    public int getTrade_qty() {
        return trade_qty;
    }

    public void setTrade_qty(int trade_qty) {
        this.trade_qty = trade_qty;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }
}
