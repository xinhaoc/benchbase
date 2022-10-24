package com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.TradeLookupFrame1MaxRows;

public class TTradeLookupFrame1Output {
    int num_found;
    TTradeLookupFrame1TradeInfo[] trade_info;

    public TTradeLookupFrame1Output() {
        trade_info = new TTradeLookupFrame1TradeInfo[TradeLookupFrame1MaxRows];
        for(int i = 0; i < TradeLookupFrame1MaxRows; i++) trade_info[i] = new TTradeLookupFrame1TradeInfo();
    }

    public int getNum_found() {
        return num_found;
    }

    public void setNum_found(int num_found) {
        this.num_found = num_found;
    }

    public TTradeLookupFrame1TradeInfo[] getTrade_info() {
        return trade_info;
    }

    public void setTrade_info(TTradeLookupFrame1TradeInfo[] trade_info) {
        this.trade_info = trade_info;
    }
}
