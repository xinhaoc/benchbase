package com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.TradeLookupFrame2MaxRows;

public class TTradeLookupFrame2Output {
    int num_found;
    TTradeLookupFrame2TradeInfo[] trade_info;

    public TTradeLookupFrame2Output() {
        trade_info = new TTradeLookupFrame2TradeInfo[TradeLookupFrame2MaxRows];
        for (int i = 0; i < TradeLookupFrame2MaxRows; i++) {
            trade_info[i] = new TTradeLookupFrame2TradeInfo();
        }
    }

    public int getNum_found() {
        return num_found;
    }

    public void setNum_found(int num_found) {
        this.num_found = num_found;
    }

    public TTradeLookupFrame2TradeInfo[] getTrade_info() {
        return trade_info;
    }

    public void setTrade_info(TTradeLookupFrame2TradeInfo[] trade_info) {
        this.trade_info = trade_info;
    }
}
