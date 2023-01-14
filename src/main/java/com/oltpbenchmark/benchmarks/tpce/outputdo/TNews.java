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

    public TimestampType getDts() {
        return dts;
    }

    public void setDts(TimestampType dts) {
        this.dts = dts;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getAuth_ind() {
        return auth_ind;
    }

    public void setAuth_ind(int auth_ind) {
        this.auth_ind = auth_ind;
    }
}
