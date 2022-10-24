package com.oltpbenchmark.benchmarks.tpce.outputdo.traderesult;

public class TTradeResultFrame1Output {
    double charge;
    long acct_id;
    int hs_qty;
    int is_lifo;
    int num_found;
    int trade_is_cash;
    int trade_qty;
    int type_is_market;
    int type_is_sell;
    String symbol;
    String type_id;
    String type_name;

    public TTradeResultFrame1Output() {
        symbol = new String();
        type_id = new String();
        type_name = new String();
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public long getAcct_id() {
        return acct_id;
    }

    public void setAcct_id(long acct_id) {
        this.acct_id = acct_id;
    }

    public int getHs_qty() {
        return hs_qty;
    }

    public void setHs_qty(int hs_qty) {
        this.hs_qty = hs_qty;
    }

    public int getIs_lifo() {
        return is_lifo;
    }

    public void setIs_lifo(int is_lifo) {
        this.is_lifo = is_lifo;
    }

    public int getNum_found() {
        return num_found;
    }

    public void setNum_found(int num_found) {
        this.num_found = num_found;
    }

    public int getTrade_is_cash() {
        return trade_is_cash;
    }

    public void setTrade_is_cash(int trade_is_cash) {
        this.trade_is_cash = trade_is_cash;
    }

    public int getTrade_qty() {
        return trade_qty;
    }

    public void setTrade_qty(int trade_qty) {
        this.trade_qty = trade_qty;
    }

    public int getType_is_market() {
        return type_is_market;
    }

    public void setType_is_market(int type_is_market) {
        this.type_is_market = type_is_market;
    }

    public int getType_is_sell() {
        return type_is_sell;
    }

    public void setType_is_sell(int type_is_sell) {
        this.type_is_sell = type_is_sell;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
