package com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder;

public class TTradeOrderFrame2Input {
    long acct_id;
    String exec_f_name;
    String exec_l_name;
    String exec_tax_id;

    public TTradeOrderFrame2Input() {
        exec_f_name = new String();
        exec_l_name = new String();
        exec_tax_id = new String();
    }

    public long getAcct_id() {
        return acct_id;
    }

    public void setAcct_id(long acct_id) {
        this.acct_id = acct_id;
    }

    public String getExec_f_name() {
        return exec_f_name;
    }

    public void setExec_f_name(String exec_f_name) {
        this.exec_f_name = exec_f_name;
    }

    public String getExec_l_name() {
        return exec_l_name;
    }

    public void setExec_l_name(String exec_l_name) {
        this.exec_l_name = exec_l_name;
    }

    public String getExec_tax_id() {
        return exec_tax_id;
    }

    public void setExec_tax_id(String exec_tax_id) {
        this.exec_tax_id = exec_tax_id;
    }
}
