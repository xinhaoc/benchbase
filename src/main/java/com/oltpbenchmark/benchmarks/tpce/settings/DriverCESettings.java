package com.oltpbenchmark.benchmarks.tpce.settings;

public class DriverCESettings extends ParametersWithoutDefaults {

    public long cur_UniqueId;
    public long cur_TxnMixRNGSeed;
    public long cur_TxnInputRNGSeed;

    public DriverCESettings(long uniqueID, long TxnMixRNGSeed, long TxnInputRNGSeed) {
        cur_UniqueId = uniqueID;
        cur_TxnMixRNGSeed = TxnMixRNGSeed;
        cur_TxnInputRNGSeed = TxnInputRNGSeed;
    }

    public DriverCESettings() {
    }

    public void checkValid() {
    }

    public void checkCompliant() {
    }

    public boolean isValid() {
        return true;
    }

    public boolean isCompliant() {
        return true;
    }
}
