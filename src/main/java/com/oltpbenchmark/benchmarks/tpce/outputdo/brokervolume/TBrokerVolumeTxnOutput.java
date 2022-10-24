package com.oltpbenchmark.benchmarks.tpce.outputdo.brokervolume;

import static com.oltpbenchmark.benchmarks.tpce.pojo.TxnHarnessStructs.max_broker_list_len;

public class TBrokerVolumeTxnOutput {
    double[] volume;
    int list_len;
    int status;

    public TBrokerVolumeTxnOutput() {
        this.volume = new double[max_broker_list_len];
    }

    public double[] getVolume() {
        return volume;
    }

    public void setVolume(double[] volume) {
        this.volume = volume;
    }

    public int getList_len() {
        return list_len;
    }

    public void setList_len(int list_len) {
        this.list_len = list_len;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
