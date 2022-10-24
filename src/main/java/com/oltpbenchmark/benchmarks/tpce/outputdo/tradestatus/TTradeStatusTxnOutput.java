package com.oltpbenchmark.benchmarks.tpce.outputdo.tradestatus;

import static com.oltpbenchmark.benchmarks.tpce.pojo.TxnHarnessStructs.max_trade_status_len;

public class TTradeStatusTxnOutput {
    long[] trade_id;
    int status;
    String[] status_name;

    public TTradeStatusTxnOutput() {
        this.trade_id = new long[max_trade_status_len];
        this.status_name = new String[max_trade_status_len];
        for(int i = 0; i < max_trade_status_len; i++) status_name[i] = new String();
    }

    public long[] getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long[] trade_id) {
        this.trade_id = trade_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String[] getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String[] status_name) {
        this.status_name = status_name;
    }
}
