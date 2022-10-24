package com.oltpbenchmark.benchmarks.tpce.outputdo.marketfeed;

public class TMarketFeedTxnOutput {
    int send_len;
    int status;

    public TMarketFeedTxnOutput() {
    }

    public int getSend_len() {
        return send_len;
    }

    public void setSend_len(int send_len) {
        this.send_len = send_len;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
