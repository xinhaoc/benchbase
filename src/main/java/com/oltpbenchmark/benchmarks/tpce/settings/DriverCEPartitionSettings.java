package com.oltpbenchmark.benchmarks.tpce.settings;

import com.oltpbenchmark.benchmarks.tpce.exceptions.CheckException;

public class DriverCEPartitionSettings extends ParametersWithDefaults {

    public long dft_iMyStartingCustomerId;
    public long dft_iMyCustomerCount;
    public int dft_iPartitionPercent;

    public long cur_iMyStartingCustomerId;
    public long cur_iMyCustomerCount;
    public int cur_iPartitionPercent;

    public boolean state_iPartitionPercent;

    public DriverCEPartitionSettings(long iMyStartingCustomerId, long iMyCustomerCount, int iPartitionPercent) {
        initialize();

        cur_iMyStartingCustomerId = iMyStartingCustomerId;
        cur_iMyCustomerCount = iMyCustomerCount;
        cur_iPartitionPercent = iPartitionPercent;

        checkDefaults();
    }

    public DriverCEPartitionSettings() {
        initialize();

        cur_iMyStartingCustomerId = 0;
        cur_iMyCustomerCount = 0;
        cur_iPartitionPercent = 0;

        checkDefaults();
    }

    public void initializeDefaults() {
        dft_iMyStartingCustomerId = 1;
        dft_iMyCustomerCount = 5000;
        dft_iPartitionPercent = 50;
    }

    public void setToDefaults() {
        dft_iMyStartingCustomerId = cur_iMyStartingCustomerId;
        dft_iMyCustomerCount = cur_iMyCustomerCount;
        dft_iPartitionPercent = cur_iPartitionPercent;

        checkDefaults();
    }

    public void checkDefaults() {
        state_iPartitionPercent = (cur_iPartitionPercent == dft_iPartitionPercent);
    }


    public boolean checkValid() {
        try {
            driverParamCheckBetween("iPartitionPercent", cur_iPartitionPercent, 0, 100);
        } catch (CheckException e) {
            return false;
        }

        if (cur_iMyStartingCustomerId == 0 && cur_iMyCustomerCount == 0 && cur_iPartitionPercent == 0) {
            /* Partitioning Disabled:
             * - in this case, the default constructor would have been used and all values
             * are set to 0.  This must be considered valid.
             */
            return true;
        } else {

            try {
                driverParamCheckEqual("iMyStartingCustomerId", (int) cur_iMyStartingCustomerId % 1000, 1);
                driverParamCheckGE("iMyCustomerCount", (int) cur_iMyCustomerCount, 1000);
                driverParamCheckEqual("iMyCustomerCount", (int) cur_iMyCustomerCount % 1000, 0);
                return true;
            } catch (CheckException e) {
                return false;
            }

        }
    }

    public boolean checkCompliant() {
        checkValid();

        if (cur_iMyStartingCustomerId == 0 && cur_iMyCustomerCount == 0 && cur_iPartitionPercent == 0) {
            return true;
        } else {
            try {
                driverParamCheckDefault(dft_iPartitionPercent, cur_iPartitionPercent, "iPartitionPercent");
                return true;
            } catch (CheckException e) {
                return false;
            }

        }
    }
}
