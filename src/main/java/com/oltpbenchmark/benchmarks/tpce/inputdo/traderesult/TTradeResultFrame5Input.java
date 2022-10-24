package com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.GregorianCalendar;

public class TTradeResultFrame5Input {
    double comm_amount;
    double trade_price;
    long broker_id;
    long trade_id;
    TimestampType trade_dts;
    String st_completed_id;

    public TTradeResultFrame5Input() {
        trade_dts = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
        st_completed_id = new String();
    }

    public double getComm_amount() {
        return comm_amount;
    }

    public void setComm_amount(double comm_amount) {
        this.comm_amount = comm_amount;
    }

    public double getTrade_price() {
        return trade_price;
    }

    public void setTrade_price(double trade_price) {
        this.trade_price = trade_price;
    }

    public long getBroker_id() {
        return broker_id;
    }

    public void setBroker_id(long broker_id) {
        this.broker_id = broker_id;
    }

    public long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long trade_id) {
        this.trade_id = trade_id;
    }

    public TimestampType getTrade_dts() {
        return trade_dts;
    }

    public void setTrade_dts(TimestampType trade_dts) {
        this.trade_dts = trade_dts;
    }

    public String getSt_completed_id() {
        return st_completed_id;
    }

    public void setSt_completed_id(String st_completed_id) {
        this.st_completed_id = st_completed_id;
    }
}
