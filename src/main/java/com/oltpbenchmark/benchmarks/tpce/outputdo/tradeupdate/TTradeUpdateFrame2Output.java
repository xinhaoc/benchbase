package com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.TradeUpdateFrame2MaxRows;

public class TTradeUpdateFrame2Output {
    int num_found;
    int num_updated;
    TTradeUpdateFrame2TradeInfo[] trade_info;

    public TTradeUpdateFrame2Output() {
        trade_info = new TTradeUpdateFrame2TradeInfo[TradeUpdateFrame2MaxRows];
        for (int i = 0; i < TradeUpdateFrame2MaxRows; i++) trade_info[i] = new TTradeUpdateFrame2TradeInfo();
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

    public TTradeUpdateFrame2TradeInfo[] getTrade_info() {
        return trade_info;
    }

    public void setTrade_info(TTradeUpdateFrame2TradeInfo[] trade_info) {
        this.trade_info = trade_info;
    }
}
