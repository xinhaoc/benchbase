package com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;


public class TCustomerPositionFrame2Output {
    private static final int max_acct_len = 10;
    private static final int max_hist_len = 10 * 3;

    long[] trade_id;
    int[] qty;
    int hist_len;
    TimestampType[] hist_dts;
    String[] symbol;
    String[] trade_status;

    public TCustomerPositionFrame2Output(){
        this.trade_id = new long[max_hist_len];
        this.qty = new int[max_hist_len];
        this.hist_dts = new TimestampType[max_hist_len];
        this.symbol = new String[max_hist_len];
        for(int i = 0; i < max_hist_len; i++) symbol[i] = new String();
        this.trade_status = new String[max_hist_len];
        for(int i = 0; i < max_hist_len; i++) trade_status[i] = new String();
    }

    public long[] getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long[] trade_id) {
        this.trade_id = trade_id;
    }

    public int[] getQty() {
        return qty;
    }

    public void setQty(int[] qty) {
        this.qty = qty;
    }

    public int getHist_len() {
        return hist_len;
    }

    public void setHist_len(int hist_len) {
        this.hist_len = hist_len;
    }

    public TimestampType[] getHist_dts() {
        return hist_dts;
    }

    public void setHist_dts(TimestampType[] hist_dts) {
        this.hist_dts = hist_dts;
    }

    public String[] getSymbol() {
        return symbol;
    }

    public void setSymbol(String[] symbol) {
        this.symbol = symbol;
    }

    public String[] getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String[] trade_status) {
        this.trade_status = trade_status;
    }
}
