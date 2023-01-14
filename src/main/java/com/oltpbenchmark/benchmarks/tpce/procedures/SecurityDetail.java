package com.oltpbenchmark.benchmarks.tpce.procedures;

import com.oltpbenchmark.benchmarks.tpce.inputdo.securitydetail.TSecurityDetailTxnInput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.securitydetail.TSecurityDetailTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class SecurityDetail extends TPCEProcedure{
    private TSecurityDetailTxnInput securityDetailTxnInput;
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(SecurityDetail.class);
    @Override
    public boolean run(Connection connection) throws SQLException {
        TSecurityDetailTxnOutput tSecurityDetailTxnOutput = new TSecurityDetailTxnOutput();
        sutInterfaces.CSecurityDetail(connection, securityDetailTxnInput, tSecurityDetailTxnOutput);
        if (tSecurityDetailTxnOutput.getStatus() == ErrorCode.SUCCESS) {
            return true;
        } else {
            LOG.error("Security Detail volume error" + tSecurityDetailTxnOutput.getStatus());
            return false;
        }
    }

    public SecurityDetail(TSecurityDetailTxnInput securityDetailTxnInput){
        this.securityDetailTxnInput = securityDetailTxnInput;
    }
}
