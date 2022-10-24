package com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.GregorianCalendar;

public class TTradeUpdateFrame2Input {
    long acct_id;
    int max_trades;
    int max_updates;
    TimestampType end_trade_dts;
    TimestampType start_trade_dts;

    public TTradeUpdateFrame2Input() {
        end_trade_dts = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
        start_trade_dts = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
    }

    public long getAcct_id() {
        return acct_id;
    }

    public void setAcct_id(long acct_id) {
        this.acct_id = acct_id;
    }

    public int getMax_trades() {
        return max_trades;
    }

    public void setMax_trades(int max_trades) {
        this.max_trades = max_trades;
    }

    public int getMax_updates() {
        return max_updates;
    }

    public void setMax_updates(int max_updates) {
        this.max_updates = max_updates;
    }

    public TimestampType getEnd_trade_dts() {
        return end_trade_dts;
    }

    public void setEnd_trade_dts(TimestampType end_trade_dts) {
        this.end_trade_dts = end_trade_dts;
    }

    public TimestampType getStart_trade_dts() {
        return start_trade_dts;
    }

    public void setStart_trade_dts(TimestampType start_trade_dts) {
        this.start_trade_dts = start_trade_dts;
    }
}
