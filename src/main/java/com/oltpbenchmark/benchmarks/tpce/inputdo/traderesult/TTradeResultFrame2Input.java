package com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult;

public class TTradeResultFrame2Input {

    double trade_price;
    long acct_id;
    long trade_id;
    int hs_qty;
    int is_lifo;
    int trade_qty;
    int type_is_sell;
    String symbol;

    public TTradeResultFrame2Input() {
        symbol = new String();
    }


    public double getTrade_price() {
        return trade_price;
    }

    public void setTrade_price(double trade_price) {
        this.trade_price = trade_price;
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

    public int getHs_qty() {
        return hs_qty;
    }

    public void setHs_qty(int hs_qty) {
        this.hs_qty = hs_qty;
    }

    public int getIs_lifo() {
        return is_lifo;
    }

    public void setIs_lifo(int is_lifo) {
        this.is_lifo = is_lifo;
    }

    public int getTrade_qty() {
        return trade_qty;
    }

    public void setTrade_qty(int trade_qty) {
        this.trade_qty = trade_qty;
    }

    public int getType_is_sell() {
        return type_is_sell;
    }

    public void setType_is_sell(int type_is_sell) {
        this.type_is_sell = type_is_sell;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
