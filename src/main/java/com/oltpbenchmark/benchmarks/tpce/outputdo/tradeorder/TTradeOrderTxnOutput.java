package com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder;

public class TTradeOrderTxnOutput {

    double buy_value;
    double sell_value;
    double tax_amount;
    long trade_id;
    int status;

    public TTradeOrderTxnOutput() {
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

    public double getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(double tax_amount) {
        this.tax_amount = tax_amount;
    }

    public long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long trade_id) {
        this.trade_id = trade_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
