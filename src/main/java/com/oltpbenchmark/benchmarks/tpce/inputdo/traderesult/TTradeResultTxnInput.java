package com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult;

import java.util.ArrayList;

public class TTradeResultTxnInput {
     public double      trade_price;
     public long      trade_id;

     public ArrayList<Object>InputParameters(){
            ArrayList<Object> para = new ArrayList<Object>();
            para.add(trade_price);
            para.add(trade_id);
            return para;
        }

    public double getTrade_price() {
        return trade_price;
    }

    public void setTrade_price(double trade_price) {
        this.trade_price = trade_price;
    }

    public long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long trade_id) {
        this.trade_id = trade_id;
    }
}
