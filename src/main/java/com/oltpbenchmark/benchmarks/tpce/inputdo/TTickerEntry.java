package com.oltpbenchmark.benchmarks.tpce.inputdo;

public class TTickerEntry {
    public double              price_quote;
    public int               trade_qty;
    public String                symbol;
    public TTickerEntry(){
        symbol = new String();
    }

    public double getPrice_quote() {
        return price_quote;
    }

    public void setPrice_quote(double price_quote) {
        this.price_quote = price_quote;
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
}
