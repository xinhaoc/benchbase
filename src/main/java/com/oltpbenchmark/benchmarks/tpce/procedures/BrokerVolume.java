package com.oltpbenchmark.benchmarks.tpce.procedures;

import com.oltpbenchmark.benchmarks.tpce.inputdo.brokervolume.TBrokerVolumeTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.brokervolume.TBrokerVolumeTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class BrokerVolume extends TPCEProcedure{

    private TBrokerVolumeTxnInput brokerVolumeTxnInput;

    private static final org.apache.log4j.Logger LOG = Logger.getLogger(BrokerVolume.class);
    @Override
    public boolean run(Connection connection) throws SQLException {
        TBrokerVolumeTxnOutput tBrokerVolumeTxnOutput = new TBrokerVolumeTxnOutput();
        sutInterfaces.CBrokerVolume(connection, brokerVolumeTxnInput, tBrokerVolumeTxnOutput);
        if (tBrokerVolumeTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Broker volume error" + tBrokerVolumeTxnOutput.getStatus());
            return false;
        }
    }

    public BrokerVolume(TBrokerVolumeTxnInput brokerVolumeTxnInput){
        this.brokerVolumeTxnInput = brokerVolumeTxnInput;
    }
}
