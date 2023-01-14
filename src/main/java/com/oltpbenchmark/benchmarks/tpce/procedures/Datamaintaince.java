package com.oltpbenchmark.benchmarks.tpce.procedures;

import com.oltpbenchmark.benchmarks.tpce.inputdo.brokervolume.TBrokerVolumeTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.datamaintenance.TDataMaintenanceTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TDataMaintenanceTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;

import java.sql.Connection;
import java.sql.SQLException;

public class Datamaintaince extends TPCEProcedure{

    private TDataMaintenanceTxnInput tDataMaintenanceTxnInput;
    @Override
    public boolean run(Connection connection) throws SQLException {
        TDataMaintenanceTxnOutput tDataMaintenanceTxnOutput = new TDataMaintenanceTxnOutput();
        sutInterfaces.CDataMaintenance(connection, tDataMaintenanceTxnInput, tDataMaintenanceTxnOutput);
        return tDataMaintenanceTxnOutput.getStatus() == ErrorCode.SUCCESS;
    }

    public Datamaintaince(TDataMaintenanceTxnInput tDataMaintenanceTxnInput){
        this.tDataMaintenanceTxnInput = tDataMaintenanceTxnInput;
    }
}
