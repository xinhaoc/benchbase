package com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.GregorianCalendar;

public class TTradeResultFrame6Input {
    double              se_amount;
    long              acct_id;
    long              trade_id;
    int               trade_is_cash;
    int               trade_qty;
    TimestampType due_date;
    TimestampType    trade_dts;
    String                s_name;
    String                type_name;

    public TTradeResultFrame6Input() {
        due_date = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
        trade_dts = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
        s_name = new String();
        type_name = new String();
    }

    public double getSe_amount() {
        return se_amount;
    }

    public void setSe_amount(double se_amount) {
        this.se_amount = se_amount;
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

    public int getTrade_is_cash() {
        return trade_is_cash;
    }

    public void setTrade_is_cash(int trade_is_cash) {
        this.trade_is_cash = trade_is_cash;
    }

    public int getTrade_qty() {
        return trade_qty;
    }

    public void setTrade_qty(int trade_qty) {
        this.trade_qty = trade_qty;
    }

    public TimestampType getDue_date() {
        return due_date;
    }

    public void setDue_date(TimestampType due_date) {
        this.due_date = due_date;
    }

    public TimestampType getTrade_dts() {
        return trade_dts;
    }

    public void setTrade_dts(TimestampType trade_dts) {
        this.trade_dts = trade_dts;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
