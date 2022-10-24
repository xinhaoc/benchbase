package com.oltpbenchmark.benchmarks.tpce.settings;

public class DriverDMSettings extends ParametersWithoutDefaults {

    public long cur_UniqueId;
    public long cur_RNGSeed;

    public DriverDMSettings(long uniqueID, long RNGSeed) {
        cur_UniqueId = uniqueID;
        cur_RNGSeed = RNGSeed;
    }

    public DriverDMSettings() {
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
