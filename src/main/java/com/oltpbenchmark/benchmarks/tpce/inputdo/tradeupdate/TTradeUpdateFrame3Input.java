package com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.GregorianCalendar;

public class TTradeUpdateFrame3Input {
    long max_acct_id;
    int max_trades;
    int max_updates;
    TimestampType end_trade_dts;
    TimestampType start_trade_dts;
    String symbol;

    public TTradeUpdateFrame3Input() {
        symbol = new String();
        end_trade_dts = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
        start_trade_dts = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());

    }

    public long getMax_acct_id() {
        return max_acct_id;
    }

    public void setMax_acct_id(long max_acct_id) {
        this.max_acct_id = max_acct_id;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
