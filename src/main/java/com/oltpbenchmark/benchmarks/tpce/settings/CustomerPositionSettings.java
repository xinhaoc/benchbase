package com.oltpbenchmark.benchmarks.tpce.settings;

import com.oltpbenchmark.benchmarks.tpce.exceptions.CheckException;

public class CustomerPositionSettings extends ParametersWithDefaults {

    public int dft_by_cust_id;
    public int dft_by_tax_id;
    public int dft_get_history;
    public int cur_by_cust_id;
    public int cur_by_tax_id;
    public int cur_get_history;
    public boolean state_by_cust_id;
    public boolean state_by_tax_id;
    public boolean state_get_history;

    public CustomerPositionSettings() {
        initialize();
    }

    public void initializeDefaults() {
        dft_by_cust_id = 50;
        dft_by_tax_id = 50;
        dft_get_history = 50;
    }

    public void setToDefaults() {
        cur_by_cust_id = dft_by_cust_id;
        cur_by_tax_id = dft_by_tax_id;
        cur_get_history = dft_get_history;
        checkDefaults();
    }

    public void checkDefaults() {
        state_by_cust_id = (cur_by_cust_id == dft_by_cust_id);
        state_by_tax_id = (cur_by_tax_id == dft_by_tax_id);
        state_get_history = (cur_get_history == dft_get_history);
    }

    public boolean checkValid() {
        try {
            driverParamCheckBetween("by_cust_id", cur_by_cust_id, 0, 100);
            driverParamCheckBetween("by_tax_id", cur_by_tax_id, 0, 100);
            driverParamCheckEqual("by_*_id total", cur_by_cust_id + cur_by_tax_id, 100);
            driverParamCheckBetween("get_history", cur_get_history, 0, 100);
            return true;
        } catch (CheckException e) {
            return false;
        }
    }

    public boolean checkCompliant() {
        checkValid();
        try {
            driverParamCheckDefault(dft_by_cust_id, cur_by_cust_id, "by_cust_id");
            driverParamCheckDefault(dft_by_tax_id, cur_by_tax_id, "by_tax_id");
            driverParamCheckDefault(dft_get_history, cur_get_history, "get_history");
            return true;
        } catch (CheckException e) {
            return false;
        }
    }
}
