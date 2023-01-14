package com.oltpbenchmark.benchmarks.tpce.procedures;

import java.sql.Connection;
import java.sql.SQLException;

public class MarketFeed extends TPCEProcedure{
    @Override
    public boolean run(Connection connection) throws SQLException {
        return true;
    }
}
