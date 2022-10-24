package com.oltpbenchmark.benchmarks.tpce.outputdo.traderesult;

public class TTradeResultTxnOutput {
    double      acct_bal;
    long      acct_id;
    int       load_unit;
    int       status;

    public TTradeResultTxnOutput() {
    }

    public double getAcct_bal() {
        return acct_bal;
    }

    public void setAcct_bal(double acct_bal) {
        this.acct_bal = acct_bal;
    }

    public long getAcct_id() {
        return acct_id;
    }

    public void setAcct_id(long acct_id) {
        this.acct_id = acct_id;
    }

    public int getLoad_unit() {
        return load_unit;
    }

    public void setLoad_unit(int load_unit) {
        this.load_unit = load_unit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
