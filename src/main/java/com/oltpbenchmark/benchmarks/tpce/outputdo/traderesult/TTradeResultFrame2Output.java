package com.oltpbenchmark.benchmarks.tpce.outputdo.traderesult;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.GregorianCalendar;

public class TTradeResultFrame2Output {
    double buy_value;
    double sell_value;
    long broker_id;
    long cust_id;
    int tax_status;
    TimestampType trade_dts;

    public TTradeResultFrame2Output() {
        trade_dts = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
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

    public long getBroker_id() {
        return broker_id;
    }

    public void setBroker_id(long broker_id) {
        this.broker_id = broker_id;
    }

    public long getCust_id() {
        return cust_id;
    }

    public void setCust_id(long cust_id) {
        this.cust_id = cust_id;
    }

    public int getTax_status() {
        return tax_status;
    }

    public void setTax_status(int tax_status) {
        this.tax_status = tax_status;
    }

    public TimestampType getTrade_dts() {
        return trade_dts;
    }

    public void setTrade_dts(TimestampType trade_dts) {
        this.trade_dts = trade_dts;
    }
}
