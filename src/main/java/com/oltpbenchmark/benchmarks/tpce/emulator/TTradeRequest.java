package com.oltpbenchmark.benchmarks.tpce.emulator;

import com.oltpbenchmark.benchmarks.tpce.TPCEConstants;

public class TTradeRequest {
    public double price_quote;
    public long trade_id;
    public int trade_qty;
    public TPCEConstants.eMEETradeRequestAction eAction;
    public String symbol;
    public String trade_type_id;

    public TTradeRequest() {
        symbol = new String();
        trade_type_id = new String();
    }

    public double getPrice_quote() {
        return price_quote;
    }

    public void setPrice_quote(double price_quote) {
        this.price_quote = price_quote;
    }

    public long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long trade_id) {
        this.trade_id = trade_id;
    }

    public int getTrade_qty() {
        return trade_qty;
    }

    public void setTrade_qty(int trade_qty) {
        this.trade_qty = trade_qty;
    }

    public TPCEConstants.eMEETradeRequestAction geteAction() {
        return eAction;
    }

    public void seteAction(TPCEConstants.eMEETradeRequestAction eAction) {
        this.eAction = eAction;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTrade_type_id() {
        return trade_type_id;
    }

    public void setTrade_type_id(String trade_type_id) {
        this.trade_type_id = trade_type_id;
    }
}
