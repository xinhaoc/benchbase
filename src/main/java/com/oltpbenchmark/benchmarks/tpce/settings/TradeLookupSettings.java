package com.oltpbenchmark.benchmarks.tpce.settings;

import com.oltpbenchmark.benchmarks.tpce.TPCEConstants;
import com.oltpbenchmark.benchmarks.tpce.exceptions.CheckException;

public class TradeLookupSettings extends ParametersWithDefaults {

    public int dft_do_frame1;
    public int dft_do_frame2;
    public int dft_do_frame3;
    public int dft_do_frame4;
    public int dft_MaxRowsFrame1;
    public int dft_BackOffFromEndTimeFrame2;
    public int dft_MaxRowsFrame2;
    public int dft_BackOffFromEndTimeFrame3;
    public int dft_MaxRowsFrame3;
    public int dft_BackOffFromEndTimeFrame4;
    public int dft_MaxRowsFrame4;

    public int cur_do_frame1;
    public int cur_do_frame2;
    public int cur_do_frame3;
    public int cur_do_frame4;
    public int cur_MaxRowsFrame1;
    public int cur_BackOffFromEndTimeFrame2;
    public int cur_MaxRowsFrame2;
    public int cur_BackOffFromEndTimeFrame3;
    public int cur_MaxRowsFrame3;
    public int cur_BackOffFromEndTimeFrame4;
    public int cur_MaxRowsFrame4;

    public boolean state_do_frame1;
    public boolean state_do_frame2;
    public boolean state_do_frame3;
    public boolean state_do_frame4;
    public boolean state_MaxRowsFrame1;
    public boolean state_BackOffFromEndTimeFrame2;
    public boolean state_MaxRowsFrame2;
    public boolean state_BackOffFromEndTimeFrame3;
    public boolean state_MaxRowsFrame3;
    public boolean state_BackOffFromEndTimeFrame4;
    public boolean state_MaxRowsFrame4;

    TradeLookupSettings() {
        initialize();
    }

    public void initializeDefaults() {
        dft_do_frame1 = 30;
        dft_do_frame2 = 30;
        dft_do_frame3 = 30;
        dft_do_frame4 = 10;
        dft_MaxRowsFrame1 = 20;
        dft_BackOffFromEndTimeFrame2 = 4 * 8 * 3600;
        dft_MaxRowsFrame2 = 20;
        dft_BackOffFromEndTimeFrame3 = 200 * 60;
        dft_MaxRowsFrame3 = 20;
        dft_BackOffFromEndTimeFrame4 = 500 * 60;
        dft_MaxRowsFrame4 = 20;
    }

    public void checkDefaults() {
        state_do_frame1 = (cur_do_frame1 == dft_do_frame1);
        state_do_frame2 = (cur_do_frame2 == dft_do_frame2);
        state_do_frame3 = (cur_do_frame3 == dft_do_frame3);
        state_do_frame4 = (cur_do_frame4 == dft_do_frame4);
        state_MaxRowsFrame1 = (cur_MaxRowsFrame1 == dft_MaxRowsFrame1);
        state_BackOffFromEndTimeFrame2 = (cur_BackOffFromEndTimeFrame2 == dft_BackOffFromEndTimeFrame2);
        state_MaxRowsFrame2 = (cur_MaxRowsFrame2 == dft_MaxRowsFrame2);
        state_BackOffFromEndTimeFrame3 = (cur_BackOffFromEndTimeFrame3 == dft_BackOffFromEndTimeFrame3);
        state_MaxRowsFrame3 = (cur_MaxRowsFrame3 == dft_MaxRowsFrame3);
        state_BackOffFromEndTimeFrame4 = (cur_BackOffFromEndTimeFrame4 == dft_BackOffFromEndTimeFrame4);
        state_MaxRowsFrame4 = (cur_MaxRowsFrame4 == dft_MaxRowsFrame4);
    }

    public void setToDefaults() {
        cur_do_frame1 = dft_do_frame1;
        cur_do_frame2 = dft_do_frame2;
        cur_do_frame3 = dft_do_frame3;
        cur_do_frame4 = dft_do_frame4;
        cur_MaxRowsFrame1 = dft_MaxRowsFrame1;
        cur_BackOffFromEndTimeFrame2 = dft_BackOffFromEndTimeFrame2;
        cur_MaxRowsFrame2 = dft_MaxRowsFrame2;
        cur_BackOffFromEndTimeFrame3 = dft_BackOffFromEndTimeFrame3;
        cur_MaxRowsFrame3 = dft_MaxRowsFrame3;
        cur_BackOffFromEndTimeFrame4 = dft_BackOffFromEndTimeFrame4;
        cur_MaxRowsFrame4 = dft_MaxRowsFrame4;
        checkDefaults();
    }

    public boolean checkValid() {
        try {
            driverParamCheckBetween("do_frame1", cur_do_frame1, 0, 100);
            driverParamCheckBetween("do_frame2", cur_do_frame2, 0, 100);
            driverParamCheckBetween("do_frame3", cur_do_frame3, 0, 100);
            driverParamCheckBetween("do_frame4", cur_do_frame4, 0, 100);
            driverParamCheckEqual("do_frame* total", cur_do_frame1 + cur_do_frame2 + cur_do_frame3 + cur_do_frame4, 100);
            driverParamCheckLE("MaxRowsFrame1", cur_MaxRowsFrame1, TPCEConstants.TradeLookupFrame1MaxRows);
            driverParamCheckLE("MaxRowsFrame2", cur_MaxRowsFrame2, TPCEConstants.TradeLookupFrame2MaxRows);
            driverParamCheckLE("MaxRowsFrame3", cur_MaxRowsFrame3, TPCEConstants.TradeLookupFrame3MaxRows);
            driverParamCheckLE("MaxRowsFrame4", cur_MaxRowsFrame4, TPCEConstants.TradeLookupFrame4MaxRows);
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
            driverParamCheckDefault(dft_do_frame4, cur_do_frame4, "do_frame4");
            driverParamCheckDefault(dft_MaxRowsFrame1, cur_MaxRowsFrame1, "MaxRowsFrame1");
            driverParamCheckDefault(dft_BackOffFromEndTimeFrame2, cur_BackOffFromEndTimeFrame2, "BackOffFromEndTimeFrame2");
            driverParamCheckDefault(dft_MaxRowsFrame2, cur_MaxRowsFrame2, "MaxRowsFrame2");
            driverParamCheckDefault(dft_BackOffFromEndTimeFrame3, cur_BackOffFromEndTimeFrame3, "BackOffFromEndTimeFrame3");
            driverParamCheckDefault(dft_MaxRowsFrame3, cur_MaxRowsFrame3, "MaxRowsFrame3");
            driverParamCheckDefault(dft_BackOffFromEndTimeFrame4, cur_BackOffFromEndTimeFrame4, "BackOffFromEndTimeFrame4");
            driverParamCheckDefault(dft_MaxRowsFrame4, cur_MaxRowsFrame4, "MaxRowsFrame4");
            return true;
        } catch (CheckException e) {
            return false;
        }
    }
}
