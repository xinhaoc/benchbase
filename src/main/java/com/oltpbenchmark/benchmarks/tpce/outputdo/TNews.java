package com.oltpbenchmark.benchmarks.tpce.outputdo;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.GregorianCalendar;

public class TNews {
    TimestampType dts;
    String auth;
    String headline;
    String item;
    String src;
    String summary;
    int auth_ind;

    public TNews() {
        auth = new String();
        headline = new String();
        item = new String();
        src = new String();
        summary = new String();
        dts = new TimestampType(new GregorianCalendar(0, 0, 0, 0, 0, 0).getTime());
    }
}
