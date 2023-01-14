package com.oltpbenchmark.benchmarks.tpce.procedures;

import java.sql.Connection;
import java.sql.SQLException;

public class TradeResult extends TPCEProcedure{
    @Override
    public boolean run(Connection connection) throws SQLException {
        return true;
    }
}
