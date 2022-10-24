package com.oltpbenchmark.benchmarks.tpce.outputdo.securitydetail;

public class TSecurityDetailTxnOutput {
    long last_vol;
    int news_len;
    int status;

    public TSecurityDetailTxnOutput() {
    }

    public long getLast_vol() {
        return last_vol;
    }

    public void setLast_vol(long last_vol) {
        this.last_vol = last_vol;
    }

    public int getNews_len() {
        return news_len;
    }

    public void setNews_len(int news_len) {
        this.news_len = news_len;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
