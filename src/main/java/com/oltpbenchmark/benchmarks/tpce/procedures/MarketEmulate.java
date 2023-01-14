package com.oltpbenchmark.benchmarks.tpce.procedures;

import java.sql.Connection;
import java.sql.SQLException;

public class MarketEmulate extends TPCEProcedure{
    @Override
    public boolean run(Connection connection) throws SQLException {
        return true;
    }
}
