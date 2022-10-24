package com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult;

public class TTradeResultFrame3Input {
    double buy_value;
    double sell_value;
    long cust_id;
    long trade_id;

    public TTradeResultFrame3Input() {
    }

    public double getBuy_value() {
        return buy_value;
    }

    public void setBuy_value(double buy_value) {
        this.buy_value = buy_value;
    }

    public double getSell_value() {
        return sell_value;
    }

    public void setSell_value(double sell_value) {
        this.sell_value = sell_value;
    }

    public long getCust_id() {
        return cust_id;
    }

    public void setCust_id(long cust_id) {
        this.cust_id = cust_id;
    }

    public long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long trade_id) {
        this.trade_id = trade_id;
    }
}
