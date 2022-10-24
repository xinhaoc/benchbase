package com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

public class TCustomerPositionFrame1Output {
    private static final int max_acct_len = 10;
    private static final int max_hist_len = 10 * 3;

    public TCustomerPositionFrame1Output() {
        this.asset_total = new double[max_acct_len];
        this.cash_bal = new double[max_acct_len];
        this.acct_id = new long[max_acct_len];
        this.c_area_1 = new String();
        this.c_area_2 = new String();
        this.c_area_3 = new String();

        this.c_ctry_1 = new String();
        this.c_ctry_2 = new String();
        this.c_ctry_3 = new String();

        this.c_email_1 = new String();
        this.c_email_2 = new String();

        this.c_ext_1 = new String();
        this.c_ext_2 = new String();
        this.c_ext_3 = new String();

        this.c_local_1 = new String();
        this.c_local_2 = new String();
        this.c_local_3 = new String();

        this.c_f_name = new String();
        this.c_gndr = new String();
        this.c_l_name = new String();

        this.c_m_name = new String();
        this.c_st_id = new String();
    }

    double[] asset_total;
    double[] cash_bal;
    long[] acct_id;
    long c_ad_id;
    long cust_id;
    int acct_len;
    TimestampType c_dob;
    String c_area_1;
    String c_area_2;
    String c_area_3;
    String c_ctry_1;
    String c_ctry_2;
    String c_ctry_3;
    String c_email_1;
    String c_email_2;
    String c_ext_1;
    String c_ext_2;
    String c_ext_3;
    String c_f_name;
    String c_gndr;
    String c_l_name;
    String c_local_1;
    String c_local_2;
    String c_local_3;
    String c_m_name;
    String c_st_id;

    public char getC_tier() {
        return c_tier;
    }

    public void setC_tier(char c_tier) {
        this.c_tier = c_tier;
    }

    char c_tier;

    public double[] getAsset_total() {
        return asset_total;
    }

    public void setAsset_total(double[] asset_total) {
        this.asset_total = asset_total;
    }

    public double[] getCash_bal() {
        return cash_bal;
    }

    public void setCash_bal(double[] cash_bal) {
        this.cash_bal = cash_bal;
    }

    public long[] getAcct_id() {
        return acct_id;
    }

    public void setAcct_id(long[] acct_id) {
        this.acct_id = acct_id;
    }

    public long getC_ad_id() {
        return c_ad_id;
    }

    public void setC_ad_id(long c_ad_id) {
        this.c_ad_id = c_ad_id;
    }

    public long getCust_id() {
        return cust_id;
    }

    public void setCust_id(long cust_id) {
        this.cust_id = cust_id;
    }

    public int getAcct_len() {
        return acct_len;
    }

    public void setAcct_len(int acct_len) {
        this.acct_len = acct_len;
    }

    public TimestampType getC_dob() {
        return c_dob;
    }

    public void setC_dob(TimestampType c_dob) {
        this.c_dob = c_dob;
    }

    public String getC_area_1() {
        return c_area_1;
    }

    public void setC_area_1(String c_area_1) {
        this.c_area_1 = c_area_1;
    }

    public String getC_area_2() {
        return c_area_2;
    }

    public void setC_area_2(String c_area_2) {
        this.c_area_2 = c_area_2;
    }

    public String getC_area_3() {
        return c_area_3;
    }

    public void setC_area_3(String c_area_3) {
        this.c_area_3 = c_area_3;
    }

    public String getC_ctry_1() {
        return c_ctry_1;
    }

    public void setC_ctry_1(String c_ctry_1) {
        this.c_ctry_1 = c_ctry_1;
    }

    public String getC_ctry_2() {
        return c_ctry_2;
    }

    public void setC_ctry_2(String c_ctry_2) {
        this.c_ctry_2 = c_ctry_2;
    }

    public String getC_ctry_3() {
        return c_ctry_3;
    }

    public void setC_ctry_3(String c_ctry_3) {
        this.c_ctry_3 = c_ctry_3;
    }

    public String getC_email_1() {
        return c_email_1;
    }

    public void setC_email_1(String c_email_1) {
        this.c_email_1 = c_email_1;
    }

    public String getC_email_2() {
        return c_email_2;
    }

    public void setC_email_2(String c_email_2) {
        this.c_email_2 = c_email_2;
    }

    public String getC_ext_1() {
        return c_ext_1;
    }

    public void setC_ext_1(String c_ext_1) {
        this.c_ext_1 = c_ext_1;
    }

    public String getC_ext_2() {
        return c_ext_2;
    }

    public void setC_ext_2(String c_ext_2) {
        this.c_ext_2 = c_ext_2;
    }

    public String getC_ext_3() {
        return c_ext_3;
    }

    public void setC_ext_3(String c_ext_3) {
        this.c_ext_3 = c_ext_3;
    }

    public String getC_f_name() {
        return c_f_name;
    }

    public void setC_f_name(String c_f_name) {
        this.c_f_name = c_f_name;
    }

    public String getC_gndr() {
        return c_gndr;
    }

    public void setC_gndr(String c_gndr) {
        this.c_gndr = c_gndr;
    }

    public String getC_l_name() {
        return c_l_name;
    }

    public void setC_l_name(String c_l_name) {
        this.c_l_name = c_l_name;
    }

    public String getC_local_1() {
        return c_local_1;
    }

    public void setC_local_1(String c_local_1) {
        this.c_local_1 = c_local_1;
    }

    public String getC_local_2() {
        return c_local_2;
    }

    public void setC_local_2(String c_local_2) {
        this.c_local_2 = c_local_2;
    }

    public String getC_local_3() {
        return c_local_3;
    }

    public void setC_local_3(String c_local_3) {
        this.c_local_3 = c_local_3;
    }

    public String getC_m_name() {
        return c_m_name;
    }

    public void setC_m_name(String c_m_name) {
        this.c_m_name = c_m_name;
    }

    public String getC_st_id() {
        return c_st_id;
    }

    public void setC_st_id(String c_st_id) {
        this.c_st_id = c_st_id;
    }
}