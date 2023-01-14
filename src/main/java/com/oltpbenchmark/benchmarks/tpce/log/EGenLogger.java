package com.oltpbenchmark.benchmarks.tpce.log;


import com.oltpbenchmark.benchmarks.tpce.TPCEConstants;
import org.apache.log4j.Logger;

public class EGenLogger extends BaseLogger{

    public EGenLogger(TPCEConstants.DriverType drvType, long uniqueID, BaseLogFormatter logFormatter){
        super(drvType, uniqueID, logFormatter);
    }

    protected boolean sendToLoggerImpl(final String szPrefix, String szTimestamp, final String szMsg){

    	LOG.debug(szPrefix + " " + szTimestamp + " " + szMsg);
        return true;
    }

    private static final Logger LOG = Logger.getLogger(EGenLogger.class);
}
