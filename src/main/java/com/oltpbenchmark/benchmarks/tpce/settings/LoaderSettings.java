package com.oltpbenchmark.benchmarks.tpce.settings;

import com.oltpbenchmark.benchmarks.tpce.exceptions.CheckException;

public class LoaderSettings extends ParametersWithDefaults {

    public long dft_iConfiguredCustomerCount;
    public long dft_iActiveCustomerCount;
    public int dft_iScaleFactor;
    public int dft_iDaysOfInitialTrades;
    public long dft_iStartingCustomer;
    public long dft_iCustomerCount;

    public long cur_iConfiguredCustomerCount;
    public long cur_iActiveCustomerCount;
    public int cur_iScaleFactor;
    public int cur_iDaysOfInitialTrades;
    public long cur_iStartingCustomer;
    public long cur_iCustomerCount;

    public boolean state_iConfiguredCustomerCount;
    public boolean state_iActiveCustomerCount;
    public boolean state_iScaleFactor;
    public boolean state_iDaysOfInitialTrades;
    public boolean state_iStartingCustomer;
    public boolean state_iCustomerCount;

    public LoaderSettings(long iConfiguredCustomerCount, long iActiveCustomerCount,
                          long iStartingCustomer, long iCustomerCount,
                          int iScaleFactor, int iDaysOfInitialTrades) {
        initialize();

        cur_iConfiguredCustomerCount = iConfiguredCustomerCount;
        cur_iActiveCustomerCount = iActiveCustomerCount;
        cur_iStartingCustomer = iStartingCustomer;
        cur_iCustomerCount = iCustomerCount;
        cur_iScaleFactor = iScaleFactor;
        cur_iDaysOfInitialTrades = iDaysOfInitialTrades;

        checkDefaults();
    }

    public LoaderSettings() {
        initialize();
    }

    public void initializeDefaults() {
        dft_iConfiguredCustomerCount = 5000;
        dft_iActiveCustomerCount = 5000;
        dft_iStartingCustomer = 1;
        dft_iCustomerCount = 5000;
        dft_iScaleFactor = 500;
        dft_iDaysOfInitialTrades = 300;
    }

    public void setToDefaults() {
        cur_iConfiguredCustomerCount = dft_iConfiguredCustomerCount;
        cur_iActiveCustomerCount = dft_iActiveCustomerCount;
        cur_iScaleFactor = dft_iScaleFactor;
        cur_iDaysOfInitialTrades = dft_iDaysOfInitialTrades;
        cur_iStartingCustomer = dft_iStartingCustomer;
        cur_iCustomerCount = dft_iCustomerCount;

        checkDefaults();
    }

    public void checkDefaults() {
        state_iConfiguredCustomerCount = true;
        state_iActiveCustomerCount = true;
        state_iStartingCustomer = true;
        state_iCustomerCount = true;
        state_iScaleFactor = (cur_iScaleFactor == dft_iScaleFactor);
        state_iDaysOfInitialTrades = (cur_iDaysOfInitialTrades == dft_iDaysOfInitialTrades);
    }

    public boolean checkValid() {
        try {
            driverParamCheckGE("iConfiguredCustomerCount", (int) cur_iConfiguredCustomerCount, 1000);
            driverParamCheckGE("iActiveCustomerCount", (int) cur_iActiveCustomerCount, 1000);
            driverParamCheckLE("iActiveCustomerCount", (int) cur_iActiveCustomerCount, (int) cur_iConfiguredCustomerCount);
            driverParamCheckEqual("iConfiguredCustomerCount", (int) cur_iConfiguredCustomerCount % 1000, 0);
            driverParamCheckGE("iStartingCustomer", (int) cur_iStartingCustomer, 1);
            driverParamCheckEqual("iStartingCustomer", (int) cur_iStartingCustomer % 1000, 1);
            driverParamCheckEqual("iCustomerCount", (int) cur_iCustomerCount % 1000, 0);
            driverParamCheckLE("iCustomerCount", (int) (cur_iCustomerCount + cur_iStartingCustomer - 1), (int) cur_iConfiguredCustomerCount);
            return true;
        } catch (CheckException e) {
            return false;
        }

    }

    public boolean checkCompliant() {
        checkValid();
        try {
            driverParamCheckGE("iConfiguredCustomerCount", (int) cur_iConfiguredCustomerCount, 5000);
            driverParamCheckGE("iActiveCustomerCount", (int) cur_iActiveCustomerCount, 5000);
            driverParamCheckEqual("iActiveCustomerCount", (int) cur_iActiveCustomerCount, (int) cur_iConfiguredCustomerCount);
            driverParamCheckDefault(dft_iScaleFactor, cur_iScaleFactor, "iScaleFactor");
            driverParamCheckDefault(dft_iDaysOfInitialTrades, cur_iDaysOfInitialTrades, "iDaysOfInitialTrades");
            return true;
        } catch (CheckException e) {
            return false;
        }

    }
}
