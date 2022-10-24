package com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.TradeUpdateMaxRows;

public class TTradeUpdateTxnOutput {
    long[] trade_list;
    int frame_executed;                     // confirmation of which frame was executed
    int num_found;
    int num_updated;
    int status;
    boolean[] is_cash;
    boolean[] is_market;

    public TTradeUpdateTxnOutput() {
        trade_list = new long[TradeUpdateMaxRows];
        is_cash = new boolean[TradeUpdateMaxRows];
        is_market = new boolean[TradeUpdateMaxRows];
    }

    public long[] getTrade_list() {
        return trade_list;
    }

    public void setTrade_list(long[] trade_list) {
        this.trade_list = trade_list;
    }

    public int getFrame_executed() {
        return frame_executed;
    }

    public void setFrame_executed(int frame_executed) {
        this.frame_executed = frame_executed;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean[] getIs_cash() {
        return is_cash;
    }

    public void setIs_cash(boolean[] is_cash) {
        this.is_cash = is_cash;
    }

    public boolean[] getIs_market() {
        return is_market;
    }

    public void setIs_market(boolean[] is_market) {
        this.is_market = is_market;
    }
}
