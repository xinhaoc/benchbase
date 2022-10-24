package com.oltpbenchmark.benchmarks.tpce.settings;

import com.oltpbenchmark.benchmarks.tpce.TPCEConstants;
import com.oltpbenchmark.benchmarks.tpce.exceptions.CheckException;

public class TDriverCETxnSettings {

    public BrokerVolumeSettings       BV_settings;
    public CustomerPositionSettings   CP_settings;
    public MarketWatchSettings        MW_settings;
    public SecurityDetailSettings     SD_settings;
    public TradeLookupSettings        TL_settings;
    public TradeOrderSettings         TO_settings;
    public TradeUpdateSettings        TU_settings;

    public TxnMixGeneratorSettings    TxnMixGenerator_settings;

    public TDriverCETxnSettings(){
        BV_settings = new BrokerVolumeSettings();
        CP_settings = new CustomerPositionSettings();
        MW_settings = new MarketWatchSettings();
        SD_settings = new SecurityDetailSettings();
        TL_settings = new TradeLookupSettings();
        TO_settings = new TradeOrderSettings();
        TU_settings = new TradeUpdateSettings();
        TxnMixGenerator_settings = new TxnMixGeneratorSettings();
    }
    public boolean isValid(){
        boolean isValid = true;
        isValid &= BV_settings.checkValid();
        isValid &= CP_settings.checkValid();
        isValid &= MW_settings.checkValid();
        isValid &= SD_settings.checkValid();
        isValid &= TL_settings.checkValid();
        isValid &= TO_settings.checkValid();
        isValid &= TU_settings.checkValid();
        isValid &= TxnMixGenerator_settings.checkValid();
        return isValid;

    }

    public void checkCompliant(){

        BV_settings.checkCompliant();
        CP_settings.checkCompliant();
        MW_settings.checkCompliant();
        SD_settings.checkCompliant();
        TL_settings.checkCompliant();
        TO_settings.checkCompliant();
        TU_settings.checkCompliant();
        TxnMixGenerator_settings.checkCompliant();
    }
}

