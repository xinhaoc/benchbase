package com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder;

public class TTradeOrderFrame1Output {
    long broker_id;
    long cust_id;
    int cust_tier;
    int num_found;
    int tax_status;
    String acct_name;
    String broker_name;
    String cust_f_name;
    String cust_l_name;
    String tax_id;

    public TTradeOrderFrame1Output() {
        acct_name = new String();
        broker_name = new String();
        cust_f_name = new String();
        cust_l_name = new String();
        tax_id = new String();
    }

    public long getBroker_id() {
        return broker_id;
    }

    public void setBroker_id(long broker_id) {
        this.broker_id = broker_id;
    }

    public long getCust_id() {
        return cust_id;
    }

    public void setCust_id(long cust_id) {
        this.cust_id = cust_id;
    }

    public int getCust_tier() {
        return cust_tier;
    }

    public void setCust_tier(int cust_tier) {
        this.cust_tier = cust_tier;
    }

    public int getNum_found() {
        return num_found;
    }

    public void setNum_found(int num_found) {
        this.num_found = num_found;
    }

    public int getTax_status() {
        return tax_status;
    }

    public void setTax_status(int tax_status) {
        this.tax_status = tax_status;
    }

    public String getAcct_name() {
        return acct_name;
    }

    public void setAcct_name(String acct_name) {
        this.acct_name = acct_name;
    }

    public String getBroker_name() {
        return broker_name;
    }

    public void setBroker_name(String broker_name) {
        this.broker_name = broker_name;
    }

    public String getCust_f_name() {
        return cust_f_name;
    }

    public void setCust_f_name(String cust_f_name) {
        this.cust_f_name = cust_f_name;
    }

    public String getCust_l_name() {
        return cust_l_name;
    }

    public void setCust_l_name(String cust_l_name) {
        this.cust_l_name = cust_l_name;
    }

    public String getTax_id() {
        return tax_id;
    }

    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }
}
