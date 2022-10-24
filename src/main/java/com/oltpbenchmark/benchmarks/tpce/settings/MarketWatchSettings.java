package com.oltpbenchmark.benchmarks.tpce.settings;

import com.oltpbenchmark.benchmarks.tpce.exceptions.CheckException;

public class MarketWatchSettings extends ParametersWithDefaults {

    public int dft_by_acct_id;
    public int dft_by_industry;
    public int dft_by_watch_list;
    public int cur_by_acct_id;
    public int cur_by_industry;
    public int cur_by_watch_list;
    public boolean state_by_acct_id;
    public boolean state_by_industry;
    public boolean state_by_watch_list;


    MarketWatchSettings() {
        initialize();
    }

    public void initializeDefaults() {
        dft_by_acct_id = 35;
        dft_by_industry = 5;
        dft_by_watch_list = 60;
    }

    public void setToDefaults() {
        cur_by_acct_id = dft_by_acct_id;
        cur_by_industry = dft_by_industry;
        cur_by_watch_list = dft_by_watch_list;
        checkDefaults();
    }

    public void checkDefaults() {
        state_by_acct_id = (cur_by_acct_id == dft_by_acct_id);
        state_by_industry = (cur_by_industry == dft_by_industry);
        state_by_watch_list = (cur_by_watch_list == dft_by_watch_list);
    }

    public boolean checkValid() {
        try {
            driverParamCheckBetween("by_acct_id", cur_by_acct_id, 0, 100);
            driverParamCheckBetween("by_industry", cur_by_industry, 0, 100);
            driverParamCheckBetween("by_watch_list", cur_by_watch_list, 0, 100);
            driverParamCheckEqual("by_* total", cur_by_acct_id + cur_by_industry + cur_by_watch_list, 100);
            return true;
        } catch (CheckException e) {
            return false;
        }
    }

    public boolean checkCompliant() {
        checkValid();
        try {
            driverParamCheckDefault(dft_by_acct_id, cur_by_acct_id, "by_cust_id");
            driverParamCheckDefault(dft_by_industry, cur_by_industry, "by_industry");
            driverParamCheckDefault(dft_by_watch_list, cur_by_watch_list, "by_watch_list");
            return true;
        } catch (CheckException e) {
            return false;
        }
    }
}
