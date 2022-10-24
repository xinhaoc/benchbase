package com.oltpbenchmark.benchmarks.tpce.settings;

public class DriverMEESettings extends ParametersWithoutDefaults {

    public long cur_UniqueId;
    public long cur_RNGSeed;
    public long cur_TickerTapeRNGSeed;
    public long cur_TradingFloorRNGSeed;

    public DriverMEESettings(long uniqueID, long RNGSeed, long TickerTapeRNGSeed, long TradingFloorRNGSeed) {
        cur_UniqueId = uniqueID;
        cur_RNGSeed = RNGSeed;
        cur_TickerTapeRNGSeed = TickerTapeRNGSeed;
        cur_TradingFloorRNGSeed = TradingFloorRNGSeed;
    }

    public DriverMEESettings() {
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
