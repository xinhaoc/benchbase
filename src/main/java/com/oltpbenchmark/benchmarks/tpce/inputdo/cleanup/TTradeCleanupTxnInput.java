package com.oltpbenchmark.benchmarks.tpce.inputdo.cleanup;

import java.util.ArrayList;

public class TTradeCleanupTxnInput {
    long start_trade_id;
    String st_canceled_id;
    String st_pending_id;
    String st_submitted_id;

    public TTradeCleanupTxnInput() {
        st_canceled_id = new String();
        st_pending_id = new String();
        st_submitted_id = new String();
    }

    public ArrayList<Object> InputParameters() {
        ArrayList<Object> para = new ArrayList<Object>();
        para.add(start_trade_id);
        para.add(st_canceled_id);
        para.add(st_pending_id);
        para.add(st_submitted_id);
        return para;
    }

    public long getStart_trade_id() {
        return start_trade_id;
    }

    public void setStart_trade_id(long start_trade_id) {
        this.start_trade_id = start_trade_id;
    }

    public String getSt_canceled_id() {
        return st_canceled_id;
    }

    public void setSt_canceled_id(String st_canceled_id) {
        this.st_canceled_id = st_canceled_id;
    }

    public String getSt_pending_id() {
        return st_pending_id;
    }

    public void setSt_pending_id(String st_pending_id) {
        this.st_pending_id = st_pending_id;
    }

    public String getSt_submitted_id() {
        return st_submitted_id;
    }

    public void setSt_submitted_id(String st_submitted_id) {
        this.st_submitted_id = st_submitted_id;
    }
}
