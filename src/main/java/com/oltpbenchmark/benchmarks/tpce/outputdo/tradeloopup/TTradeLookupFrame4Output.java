package com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.TradeLookupFrame4MaxRows;

public class TTradeLookupFrame4Output {
    long trade_id;
    int num_found;
    int num_trades_found;
    TTradeLookupFrame4TradeInfo[] trade_info;

    public TTradeLookupFrame4Output() {
        trade_info = new TTradeLookupFrame4TradeInfo[TradeLookupFrame4MaxRows];
    }

    public long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long trade_id) {
        this.trade_id = trade_id;
    }

    public int getNum_found() {
        return num_found;
    }

    public void setNum_found(int num_found) {
        this.num_found = num_found;
    }

    public int getNum_trades_found() {
        return num_trades_found;
    }

    public void setNum_trades_found(int num_trades_found) {
        this.num_trades_found = num_trades_found;
    }

    public TTradeLookupFrame4TradeInfo[] getTrade_info() {
        return trade_info;
    }

    public void setTrade_info(TTradeLookupFrame4TradeInfo[] trade_info) {
        this.trade_info = trade_info;
    }
}
