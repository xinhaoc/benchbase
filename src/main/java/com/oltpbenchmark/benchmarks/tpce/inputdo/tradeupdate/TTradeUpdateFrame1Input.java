package com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.TradeUpdateFrame1MaxRows;

public class TTradeUpdateFrame1Input {
    long[] trade_id;
    int max_trades;
    int max_updates;

    public TTradeUpdateFrame1Input() {
        trade_id = new long[TradeUpdateFrame1MaxRows];
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

    public int getMax_updates() {
        return max_updates;
    }

    public void setMax_updates(int max_updates) {
        this.max_updates = max_updates;
    }
}
