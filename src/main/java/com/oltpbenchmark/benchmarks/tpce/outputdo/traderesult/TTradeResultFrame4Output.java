package com.oltpbenchmark.benchmarks.tpce.outputdo.traderesult;

public class TTradeResultFrame4Output {
    public double getComm_rate() {
        return comm_rate;
    }

    public void setComm_rate(double comm_rate) {
        this.comm_rate = comm_rate;
    }

    double  comm_rate;
    String    s_name;

    public TTradeResultFrame4Output() {
        s_name = new String();
    }


    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }
}
