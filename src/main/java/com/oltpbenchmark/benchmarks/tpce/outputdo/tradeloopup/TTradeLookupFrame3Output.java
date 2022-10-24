package com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.TradeLookupFrame3MaxRows;

public class TTradeLookupFrame3Output {
    int num_found;
    TTradeLookupFrame3TradeInfo[] trade_info;

    public TTradeLookupFrame3Output() {
        trade_info = new TTradeLookupFrame3TradeInfo[TradeLookupFrame3MaxRows];
    }

    public int getNum_found() {
        return num_found;
    }

    public void setNum_found(int num_found) {
        this.num_found = num_found;
    }

    public TTradeLookupFrame3TradeInfo[] getTrade_info() {
        return trade_info;
    }

    public void setTrade_info(TTradeLookupFrame3TradeInfo[] trade_info) {
        this.trade_info = trade_info;
    }
}
