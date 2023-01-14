package com.oltpbenchmark.benchmarks.tpce.procedures;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerEmulate extends TPCEProcedure{

    private static final org.apache.log4j.Logger LOG = Logger.getLogger(BrokerVolume.class);
    @Override
    public boolean run(Connection connection) throws SQLException {
        return true;
    }
}
