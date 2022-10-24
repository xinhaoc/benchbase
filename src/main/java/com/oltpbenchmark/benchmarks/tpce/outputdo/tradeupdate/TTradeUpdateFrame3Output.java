package com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.TradeUpdateFrame3MaxRows;

public class TTradeUpdateFrame3Output {
    int num_found;
    int num_updated;
    TTradeUpdateFrame3TradeInfo[] trade_info;

    public TTradeUpdateFrame3Output() {
        trade_info = new TTradeUpdateFrame3TradeInfo[TradeUpdateFrame3MaxRows];
        for (int i = 0; i < TradeUpdateFrame3MaxRows; i++) trade_info[i] = new TTradeUpdateFrame3TradeInfo();
    }

    public int getNum_found() {
        return num_found;
    }

    public void setNum_found(int num_found) {
        this.num_found = num_found;
    }

    public int getNum_updated() {
        return num_updated;
    }

    public void setNum_updated(int num_updated) {
        this.num_updated = num_updated;
    }

    public TTradeUpdateFrame3TradeInfo[] getTrade_info() {
        return trade_info;
    }

    public void setTrade_info(TTradeUpdateFrame3TradeInfo[] trade_info) {
        this.trade_info = trade_info;
    }
}
