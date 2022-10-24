package com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.TradeLookupFrame1MaxRows;

public class TTradeLookupFrame1Input {
    long[] trade_id;
    int max_trades;

    public TTradeLookupFrame1Input() {
        trade_id = new long[TradeLookupFrame1MaxRows];
    }

    public long[] getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long[] trade_id) {
        this.trade_id = trade_id;
    }

    public int getMax_trades() {
        return max_trades;
    }

    public void setMax_trades(int max_trades) {
        this.max_trades = max_trades;
    }
}
