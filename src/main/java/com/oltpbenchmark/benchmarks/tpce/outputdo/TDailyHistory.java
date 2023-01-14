package com.oltpbenchmark.benchmarks.tpce.outputdo;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.GregorianCalendar;

public class TDailyHistory {

    double close;
    double high;
    double low;
    long vol;
    TimestampType date;
    int close_ind;
    int date_ind;
    int high_ind;
    int low_ind;
    int vol_ind;

    public TDailyHistory() {
        date = new TimestampType(new GregorianCalendar(0, 0, 0, 0, 0, 0).getTime());
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public long getVol() {
        return vol;
    }

    public void setVol(long vol) {
        this.vol = vol;
    }

    public TimestampType getDate() {
        return date;
    }

    public void setDate(TimestampType date) {
        this.date = date;
    }

    public int getClose_ind() {
        return close_ind;
    }

    public void setClose_ind(int close_ind) {
        this.close_ind = close_ind;
    }

    public int getDate_ind() {
        return date_ind;
    }

    public void setDate_ind(int date_ind) {
        this.date_ind = date_ind;
    }

    public int getHigh_ind() {
        return high_ind;
    }

    public void setHigh_ind(int high_ind) {
        this.high_ind = high_ind;
    }

    public int getLow_ind() {
        return low_ind;
    }

    public void setLow_ind(int low_ind) {
        this.low_ind = low_ind;
    }

    public int getVol_ind() {
        return vol_ind;
    }

    public void setVol_ind(int vol_ind) {
        this.vol_ind = vol_ind;
    }
}
