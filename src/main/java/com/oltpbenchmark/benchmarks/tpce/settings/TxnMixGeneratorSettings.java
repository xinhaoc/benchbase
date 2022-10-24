package com.oltpbenchmark.benchmarks.tpce.settings;

import com.oltpbenchmark.benchmarks.tpce.exceptions.CheckException;

public class TxnMixGeneratorSettings extends ParametersWithDefaults {

    public int dft_BrokerVolumeMixLevel;
    public int dft_CustomerPositionMixLevel;
    public int dft_MarketFeedMixLevel;
    public int dft_MarketWatchMixLevel;
    public int dft_SecurityDetailMixLevel;
    public int dft_TradeLookupMixLevel;
    public int dft_TradeOrderMixLevel;
    public int dft_TradeResultMixLevel;
    public int dft_TradeStatusMixLevel;
    public int dft_TradeUpdateMixLevel;
    public int dft_TransactionMixTotal;

    public int cur_BrokerVolumeMixLevel;
    public int cur_CustomerPositionMixLevel;
    public int cur_MarketFeedMixLevel;
    public int cur_MarketWatchMixLevel;
    public int cur_SecurityDetailMixLevel;
    public int cur_TradeLookupMixLevel;
    public int cur_TradeOrderMixLevel;
    public int cur_TradeResultMixLevel;
    public int cur_TradeStatusMixLevel;
    public int cur_TradeUpdateMixLevel;
    public int cur_TransactionMixTotal;

    public boolean state_BrokerVolumeMixLevel;
    public boolean state_CustomerPositionMixLevel;
    public boolean state_MarketWatchMixLevel;
    public boolean state_SecurityDetailMixLevel;
    public boolean state_TradeLookupMixLevel;
    public boolean state_TradeOrderMixLevel;
    public boolean state_TradeStatusMixLevel;
    public boolean state_TradeUpdateMixLevel;


    public TxnMixGeneratorSettings() {
        initialize();
    }

    public void initializeDefaults() {
        dft_BrokerVolumeMixLevel = 49;
        dft_CustomerPositionMixLevel = 130;
        dft_MarketWatchMixLevel = 180;
        dft_SecurityDetailMixLevel = 140;
        dft_TradeLookupMixLevel = 80;
        dft_TradeOrderMixLevel = 101;
        dft_TradeStatusMixLevel = 190;
        dft_TradeUpdateMixLevel = 20;
    }

    public void setToDefaults() {
        cur_BrokerVolumeMixLevel = dft_BrokerVolumeMixLevel;
        cur_CustomerPositionMixLevel = dft_CustomerPositionMixLevel;
        cur_MarketFeedMixLevel = dft_MarketFeedMixLevel;
        cur_MarketWatchMixLevel = dft_MarketWatchMixLevel;
        cur_SecurityDetailMixLevel = dft_SecurityDetailMixLevel;
        cur_TradeLookupMixLevel = dft_TradeLookupMixLevel;
        cur_TradeOrderMixLevel = dft_TradeOrderMixLevel;
        cur_TradeStatusMixLevel = dft_TradeStatusMixLevel;
        cur_TradeResultMixLevel = dft_TradeResultMixLevel;
        cur_TradeStatusMixLevel = dft_TradeStatusMixLevel;
        cur_TradeUpdateMixLevel = dft_TradeUpdateMixLevel;
        cur_TransactionMixTotal = dft_TransactionMixTotal;

        checkDefaults();
    }

    public void checkDefaults() {
        state_BrokerVolumeMixLevel = (cur_BrokerVolumeMixLevel == dft_BrokerVolumeMixLevel);
        state_CustomerPositionMixLevel = (cur_CustomerPositionMixLevel == dft_CustomerPositionMixLevel);
        state_MarketWatchMixLevel = (cur_MarketWatchMixLevel == dft_MarketWatchMixLevel);
        state_SecurityDetailMixLevel = (cur_SecurityDetailMixLevel == dft_SecurityDetailMixLevel);
        state_TradeLookupMixLevel = (cur_TradeLookupMixLevel == dft_TradeLookupMixLevel);
        state_TradeOrderMixLevel = (cur_TradeOrderMixLevel == dft_TradeOrderMixLevel);
        state_TradeStatusMixLevel = (cur_TradeStatusMixLevel == dft_TradeStatusMixLevel);
        state_TradeUpdateMixLevel = (cur_TradeUpdateMixLevel == dft_TradeUpdateMixLevel);
    }

    public boolean checkValid() {
        try {
            driverParamCheckGE("BrokerVolumeMixLevel", cur_BrokerVolumeMixLevel, 0);
            driverParamCheckGE("CustomerPositionMixLevel", cur_CustomerPositionMixLevel, 0);
            driverParamCheckGE("MarketWatchMixLevel", cur_MarketWatchMixLevel, 0);
            driverParamCheckGE("SecurityDetailMixLevel", cur_SecurityDetailMixLevel, 0);
            driverParamCheckGE("TradeLookupMixLevel", cur_TradeLookupMixLevel, 0);
            driverParamCheckGE("TradeOrderMixLevel", cur_TradeOrderMixLevel, 0);
            driverParamCheckGE("TradeStatusMixLevel", cur_TradeStatusMixLevel, 0);
            driverParamCheckGE("TradeUpdateMixLevel", cur_TradeUpdateMixLevel, 0);
            return true;
        } catch (CheckException e) {
            return false;
        }

    }

    public boolean checkCompliant() {
        checkValid();
        try {
            driverParamCheckDefault(dft_BrokerVolumeMixLevel, cur_BrokerVolumeMixLevel, "BrokerVolumeMixLevel");
            driverParamCheckDefault(dft_CustomerPositionMixLevel, cur_CustomerPositionMixLevel, "CustomerPositionMixLevel");
            driverParamCheckDefault(dft_MarketWatchMixLevel, cur_MarketWatchMixLevel, "MarketWatchMixLevel");
            driverParamCheckDefault(dft_SecurityDetailMixLevel, cur_SecurityDetailMixLevel, "SecurityDetailMixLevel");
            driverParamCheckDefault(dft_TradeLookupMixLevel, cur_TradeLookupMixLevel, "TradeLookupMixLevel");
            driverParamCheckDefault(dft_TradeOrderMixLevel, cur_TradeOrderMixLevel, "TradeOrderMixLevel");
            driverParamCheckDefault(dft_TradeStatusMixLevel, cur_TradeStatusMixLevel, "TradeStatusMixLevel");
            driverParamCheckDefault(dft_TradeUpdateMixLevel, cur_TradeUpdateMixLevel, "TradeUpdateMixLevel");
            return true;
        } catch (CheckException e) {
            return false;
        }
    }
}
