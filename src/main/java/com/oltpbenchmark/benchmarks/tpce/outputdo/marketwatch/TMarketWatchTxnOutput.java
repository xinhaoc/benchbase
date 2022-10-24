package com.oltpbenchmark.benchmarks.tpce.outputdo.marketwatch;

public class TMarketWatchTxnOutput {
    double pct_change;
    int status;

    public TMarketWatchTxnOutput() {
    }

    public double getPct_change() {
        return pct_change;
    }

    public void setPct_change(double pct_change) {
        this.pct_change = pct_change;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
