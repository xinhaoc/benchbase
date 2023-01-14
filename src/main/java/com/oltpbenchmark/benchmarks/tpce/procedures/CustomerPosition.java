package com.oltpbenchmark.benchmarks.tpce.procedures;

import com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition.TCustomerPositionTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition.TCustomerPositionTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerPosition extends TPCEProcedure{

    private TCustomerPositionTxnInput customerPositionTxnInput;
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(CustomerPosition.class);
    @Override
    public boolean run(Connection connection) throws SQLException {
        TCustomerPositionTxnOutput tCustomerPositionTxnOutput = new TCustomerPositionTxnOutput();
        sutInterfaces.CCustomerPosition(connection, customerPositionTxnInput, tCustomerPositionTxnOutput);
        if (tCustomerPositionTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Customer Position volume error" + tCustomerPositionTxnOutput.getStatus());
            return false;
        }
    }

    public CustomerPosition(TCustomerPositionTxnInput customerPositionTxnInput){
        this.customerPositionTxnInput = customerPositionTxnInput;
    }
}
