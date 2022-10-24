package com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder;

public class TTradeOrderFrame4Input {
    double charge_amount;
    double comm_amount;
    double requested_price;
    long acct_id;
    long broker_id;
    int is_cash;
    int is_lifo;
    int trade_qty;
    int type_is_market;
    String exec_name;
    String status_id;
    String symbol;
    String trade_type_id;

    public TTradeOrderFrame4Input() {
        exec_name = new String();
        status_id = new String();
        symbol = new String();
        trade_type_id = new String();
    }

    public double getCharge_amount() {
        return charge_amount;
    }

    public void setCharge_amount(double charge_amount) {
        this.charge_amount = charge_amount;
    }

    public double getComm_amount() {
        return comm_amount;
    }

    public void setComm_amount(double comm_amount) {
        this.comm_amount = comm_amount;
    }

    public double getRequested_price() {
        return requested_price;
    }

    public void setRequested_price(double requested_price) {
        this.requested_price = requested_price;
    }

    public long getAcct_id() {
        return acct_id;
    }

    public void setAcct_id(long acct_id) {
        this.acct_id = acct_id;
    }

    public long getBroker_id() {
        return broker_id;
    }

    public void setBroker_id(long broker_id) {
        this.broker_id = broker_id;
    }

    public int getIs_cash() {
        return is_cash;
    }

    public void setIs_cash(int is_cash) {
        this.is_cash = is_cash;
    }

    public int getIs_lifo() {
        return is_lifo;
    }

    public void setIs_lifo(int is_lifo) {
        this.is_lifo = is_lifo;
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

    public String getExec_name() {
        return exec_name;
    }

    public void setExec_name(String exec_name) {
        this.exec_name = exec_name;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
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
