package com.oltpbenchmark.benchmarks.tpce.settings;

import com.oltpbenchmark.benchmarks.tpce.TPCEConstants;
import com.oltpbenchmark.benchmarks.tpce.exceptions.CheckException;

public class TradeUpdateSettings extends ParametersWithDefaults {

    public int dft_do_frame1;
    public int dft_do_frame2;
    public int dft_do_frame3;

    public int dft_MaxRowsFrame1;
    public int dft_MaxRowsToUpdateFrame1;

    public int dft_BackOffFromEndTimeFrame2;
    public int dft_MaxRowsFrame2;
    public int dft_MaxRowsToUpdateFrame2;

    public int dft_BackOffFromEndTimeFrame3;
    public int dft_MaxRowsFrame3;
    public int dft_MaxRowsToUpdateFrame3;

    public int cur_do_frame1;
    public int cur_do_frame2;
    public int cur_do_frame3;

    public int cur_MaxRowsFrame1;
    public int cur_MaxRowsToUpdateFrame1;

    public int cur_BackOffFromEndTimeFrame2;
    public int cur_MaxRowsFrame2;
    public int cur_MaxRowsToUpdateFrame2;

    public int cur_BackOffFromEndTimeFrame3;
    public int cur_MaxRowsFrame3;
    public int cur_MaxRowsToUpdateFrame3;

    public boolean state_do_frame1;
    public boolean state_do_frame2;
    public boolean state_do_frame3;

    public boolean state_MaxRowsFrame1;
    public boolean state_MaxRowsToUpdateFrame1;

    public boolean state_BackOffFromEndTimeFrame2;
    public boolean state_MaxRowsFrame2;
    public boolean state_MaxRowsToUpdateFrame2;

    public boolean state_BackOffFromEndTimeFrame3;
    public boolean state_MaxRowsFrame3;
    public boolean state_MaxRowsToUpdateFrame3;

    TradeUpdateSettings() {
        initialize();
    }

    public void initializeDefaults() {
        dft_do_frame1 = 33;
        dft_do_frame2 = 33;
        dft_do_frame3 = 34;
        dft_MaxRowsFrame1 = 20;
        dft_MaxRowsToUpdateFrame1 = 20;
        dft_MaxRowsFrame2 = 20;
        dft_MaxRowsToUpdateFrame2 = 20;
        dft_BackOffFromEndTimeFrame2 = 4 * 8 * 3600;
        dft_MaxRowsFrame3 = 20;
        dft_MaxRowsToUpdateFrame3 = 20;
        dft_BackOffFromEndTimeFrame3 = 200 * 60;
    }

    public void setToDefaults() {
        cur_do_frame1 = dft_do_frame1;
        cur_do_frame2 = dft_do_frame2;
        cur_do_frame3 = dft_do_frame3;
        cur_MaxRowsFrame1 = dft_MaxRowsFrame1;
        cur_MaxRowsToUpdateFrame1 = dft_MaxRowsToUpdateFrame1;
        cur_MaxRowsFrame2 = dft_MaxRowsFrame2;
        cur_MaxRowsToUpdateFrame2 = dft_MaxRowsToUpdateFrame2;
        cur_BackOffFromEndTimeFrame2 = dft_BackOffFromEndTimeFrame2;
        cur_MaxRowsFrame3 = dft_MaxRowsFrame3;
        cur_MaxRowsToUpdateFrame3 = dft_MaxRowsToUpdateFrame3;
        cur_BackOffFromEndTimeFrame3 = dft_BackOffFromEndTimeFrame3;
        checkDefaults();
    }

    public void checkDefaults() {
        state_do_frame1 = (cur_do_frame1 == dft_do_frame1);
        state_do_frame2 = (cur_do_frame2 == dft_do_frame2);
        state_do_frame3 = (cur_do_frame3 == dft_do_frame3);
        state_MaxRowsFrame1 = (cur_MaxRowsFrame1 == dft_MaxRowsFrame1);
        state_MaxRowsToUpdateFrame1 = (cur_MaxRowsToUpdateFrame1 == dft_MaxRowsToUpdateFrame1);
        state_MaxRowsFrame2 = (cur_MaxRowsFrame2 == dft_MaxRowsFrame2);
        state_MaxRowsToUpdateFrame2 = (cur_MaxRowsToUpdateFrame2 == dft_MaxRowsToUpdateFrame2);
        state_BackOffFromEndTimeFrame2 = (cur_BackOffFromEndTimeFrame2 == dft_BackOffFromEndTimeFrame2);
        state_MaxRowsFrame3 = (cur_MaxRowsFrame3 == dft_MaxRowsFrame3);
        state_MaxRowsToUpdateFrame3 = (cur_MaxRowsToUpdateFrame3 == dft_MaxRowsToUpdateFrame3);
        state_BackOffFromEndTimeFrame3 = (cur_BackOffFromEndTimeFrame3 == dft_BackOffFromEndTimeFrame3);
    }

    public boolean checkValid() {
        try {
            driverParamCheckBetween("do_frame1", cur_do_frame1, 0, 100);
            driverParamCheckBetween("do_frame2", cur_do_frame2, 0, 100);
            driverParamCheckBetween("do_frame3", cur_do_frame3, 0, 100);
            driverParamCheckEqual("do_frame* total", cur_do_frame1 + cur_do_frame2 + cur_do_frame3, 100);
            driverParamCheckLE("MaxRowsFrame1", cur_MaxRowsFrame1, TPCEConstants.TradeUpdateFrame1MaxRows);
            driverParamCheckLE("MaxRowsFrame2", cur_MaxRowsFrame2, TPCEConstants.TradeUpdateFrame2MaxRows);
            driverParamCheckLE("MaxRowsFrame3", cur_MaxRowsFrame3, TPCEConstants.TradeUpdateFrame3MaxRows);
            driverParamCheckLE("MaxRowsToUpdateFrame1", cur_MaxRowsToUpdateFrame1, TPCEConstants.TradeUpdateFrame1MaxRows);
            driverParamCheckLE("MaxRowsToUpdateFrame2", cur_MaxRowsToUpdateFrame2, TPCEConstants.TradeUpdateFrame2MaxRows);
            driverParamCheckLE("MaxRowsToUpdateFrame3", cur_MaxRowsToUpdateFrame3, TPCEConstants.TradeUpdateFrame3MaxRows);
            return true;
        } catch (CheckException e) {
            return false;
        }
    }

    public boolean checkCompliant() {

        checkValid();
        try {
            driverParamCheckDefault(dft_do_frame1, cur_do_frame1, "do_frame1");
            driverParamCheckDefault(dft_do_frame2, cur_do_frame2, "do_frame2");
            driverParamCheckDefault(dft_do_frame3, cur_do_frame3, "do_frame3");
            driverParamCheckDefault(dft_MaxRowsFrame1, cur_MaxRowsFrame1, "MaxRowsFrame1");
            driverParamCheckDefault(dft_MaxRowsToUpdateFrame1, cur_MaxRowsToUpdateFrame1, "MaxRowsToUpdateFrame1");
            driverParamCheckDefault(dft_MaxRowsFrame2, cur_MaxRowsFrame2, "MaxRowsFrame2");
            driverParamCheckDefault(dft_MaxRowsToUpdateFrame2, cur_MaxRowsToUpdateFrame2, "MaxRowsToUpdateFrame2");
            driverParamCheckDefault(dft_BackOffFromEndTimeFrame2, cur_BackOffFromEndTimeFrame2, "BackOffFromEndTimeFrame2");
            driverParamCheckDefault(dft_MaxRowsFrame3, cur_MaxRowsFrame3, "MaxRowsFrame3");
            driverParamCheckDefault(dft_MaxRowsToUpdateFrame3, cur_MaxRowsToUpdateFrame3, "MaxRowsToUpdateFrame3");
            driverParamCheckDefault(dft_BackOffFromEndTimeFrame3, cur_BackOffFromEndTimeFrame3, "BackOffFromEndTimeFrame3");
            return true;
        } catch (CheckException e) {
            return false;
        }
    }
}
