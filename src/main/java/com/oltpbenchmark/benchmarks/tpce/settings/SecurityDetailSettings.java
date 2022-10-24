package com.oltpbenchmark.benchmarks.tpce.settings;

import com.oltpbenchmark.benchmarks.tpce.exceptions.CheckException;

public class SecurityDetailSettings extends ParametersWithDefaults {

    public int dft_LOBAccessPercentage;
    public int cur_LOBAccessPercentage;
    public boolean state_LOBAccessPercentage;

    SecurityDetailSettings() {
        initialize();
    }

    public void initializeDefaults() {
        dft_LOBAccessPercentage = 1;
    }

    public void setToDefaults() {
        cur_LOBAccessPercentage = dft_LOBAccessPercentage;
        checkDefaults();
    }

    public void checkDefaults() {
        state_LOBAccessPercentage = (cur_LOBAccessPercentage == dft_LOBAccessPercentage);
    }

    public boolean checkValid() {
        try {
            driverParamCheckBetween("LOBAccessPercentage", cur_LOBAccessPercentage, 0, 100);
            return true;
        } catch (CheckException e) {
            return false;
        }

    }

    public boolean checkCompliant() {
        checkValid();
        try {
            driverParamCheckDefault(dft_LOBAccessPercentage, cur_LOBAccessPercentage, "LOBAccessPercentage");
            return true;
        } catch (CheckException e) {
            return false;
        }
    }
}
