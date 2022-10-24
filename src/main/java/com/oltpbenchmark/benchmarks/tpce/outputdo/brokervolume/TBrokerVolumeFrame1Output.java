package com.oltpbenchmark.benchmarks.tpce.outputdo.brokervolume;

import com.oltpbenchmark.benchmarks.tpce.pojo.TxnHarnessStructs;

import static com.oltpbenchmark.benchmarks.tpce.pojo.TxnHarnessStructs.*;

public class TBrokerVolumeFrame1Output {
    double[] volume;
    int list_len;
    String[] broker_name;

    public TBrokerVolumeFrame1Output(){
        volume = new double[max_broker_list_len];
        broker_name = new String[max_broker_list_len];
        for(int i = 0; i < max_broker_list_len; i++){
            broker_name[i] = new String();
        }
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

    public String[] getBroker_name() {
        return broker_name;
    }

    public void setBroker_name(String[] broker_name) {
        this.broker_name = broker_name;
    }
}


