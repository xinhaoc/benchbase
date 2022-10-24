package com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

public class TCustomerPositionTxnOutput {
    public static final int max_acct_len = 10;
    public static final int max_hist_len = 10 * 3;
    public static final int  min_hist_len = 10 * 1;

    public TCustomerPositionTxnOutput(){
        this.asset_total = new double[max_acct_len];
        this.cash_bal = new double[max_acct_len];
        this.acct_id = new long[max_acct_len];
        this.trade_id = new long[max_hist_len];
        this.qty = new int[max_hist_len];
        this.hist_dts = new TimestampType[max_hist_len];
        this.symbol = new String[max_hist_len];
        for(int i = 0; i < max_hist_len; i++) symbol[i] = new String();
        this.trade_status = new String[max_hist_len];
        for(int i = 0; i < max_hist_len; i++) trade_status[i] = new String();
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
    long[] trade_id;
    long c_ad_id;
    int[] qty;
    int acct_len;
    int hist_len;
    int status;
    TimestampType[] hist_dts;
    TimestampType c_dob;
    String[] symbol;
    String[] trade_status;
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

    public long[] getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long[] trade_id) {
        this.trade_id = trade_id;
    }

    public long getC_ad_id() {
        return c_ad_id;
    }

    public void setC_ad_id(long c_ad_id) {
        this.c_ad_id = c_ad_id;
    }

    public int[] getQty() {
        return qty;
    }

    public void setQty(int[] qty) {
        this.qty = qty;
    }

    public int getAcct_len() {
        return acct_len;
    }

    public void setAcct_len(int acct_len) {
        this.acct_len = acct_len;
    }

    public int getHist_len() {
        return hist_len;
    }

    public void setHist_len(int hist_len) {
        this.hist_len = hist_len;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TimestampType[] getHist_dts() {
        return hist_dts;
    }

    public void setHist_dts(TimestampType[] hist_dts) {
        this.hist_dts = hist_dts;
    }

    public TimestampType getC_dob() {
        return c_dob;
    }

    public void setC_dob(TimestampType c_dob) {
        this.c_dob = c_dob;
    }

    public String[] getSymbol() {
        return symbol;
    }

    public void setSymbol(String[] symbol) {
        this.symbol = symbol;
    }

    public String[] getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String[] trade_status) {
        this.trade_status = trade_status;
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
