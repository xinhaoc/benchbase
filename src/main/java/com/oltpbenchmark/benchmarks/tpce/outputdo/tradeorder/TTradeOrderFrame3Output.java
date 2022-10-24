package com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder;

public class TTradeOrderFrame3Output {
    double acct_assets;
    double buy_value;
    double charge_amount;
    double comm_rate;
    double market_price;
    double requested_price;            // IN-OUT parameter
    double sell_value;
    double tax_amount;
    int type_is_market;
    int type_is_sell;
    String co_name;   // IN-OUT parameter
    String s_name;
    String status_id;
    String symbol;    // IN-OUT parameter

    public TTradeOrderFrame3Output() {
        co_name = new String();
        s_name = new String();
        status_id = new String();
        symbol = new String();
    }

    public double getAcct_assets() {
        return acct_assets;
    }

    public void setAcct_assets(double acct_assets) {
        this.acct_assets = acct_assets;
    }

    public double getBuy_value() {
        return buy_value;
    }

    public void setBuy_value(double buy_value) {
        this.buy_value = buy_value;
    }

    public double getCharge_amount() {
        return charge_amount;
    }

    public void setCharge_amount(double charge_amount) {
        this.charge_amount = charge_amount;
    }

    public double getComm_rate() {
        return comm_rate;
    }

    public void setComm_rate(double comm_rate) {
        this.comm_rate = comm_rate;
    }

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
    }

    public double getRequested_price() {
        return requested_price;
    }

    public void setRequested_price(double requested_price) {
        this.requested_price = requested_price;
    }

    public double getSell_value() {
        return sell_value;
    }

    public void setSell_value(double sell_value) {
        this.sell_value = sell_value;
    }

    public double getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(double tax_amount) {
        this.tax_amount = tax_amount;
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

    public String getCo_name() {
        return co_name;
    }

    public void setCo_name(String co_name) {
        this.co_name = co_name;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
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
}
