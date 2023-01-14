package com.oltpbenchmark.benchmarks.tpce;

import com.oltpbenchmark.WorkloadConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;

public class TPCEConfiguration {
    private final XMLConfiguration xmlConfig;

    public TPCEConfiguration(WorkloadConfiguration workConf) {
        this.xmlConfig = workConf.getXmlConfig();
    }

    public String getLoadFiles() {
        return xmlConfig.getString("loadfiles", null);
    }

    public String getTotalCustomers() {
        return xmlConfig.getString("totalcustomers", null);
    }

    public String getInitialDays(){return xmlConfig.getString("initialdays", null);}

    public String getScaleFactor(){return xmlConfig.getString("scalefactor", null);}

    public String getFunctionPath(){return xmlConfig.getString("functionpath", null);}
}
