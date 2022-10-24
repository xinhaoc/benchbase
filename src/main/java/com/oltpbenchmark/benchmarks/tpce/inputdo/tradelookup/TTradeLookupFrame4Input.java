package com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.GregorianCalendar;

public class TTradeLookupFrame4Input {
    long acct_id;
    TimestampType trade_dts;

    public TTradeLookupFrame4Input() {
        trade_dts = new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
    }

    public long getAcct_id() {
        return acct_id;
    }

    public void setAcct_id(long acct_id) {
        this.acct_id = acct_id;
    }

    public TimestampType getTrade_dts() {
        return trade_dts;
    }

    public void setTrade_dts(TimestampType trade_dts) {
        this.trade_dts = trade_dts;
    }
}
