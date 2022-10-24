package com.oltpbenchmark.benchmarks.tpce.procedures;

import com.oltpbenchmark.api.Procedure;

import java.sql.SQLException;

public abstract class TPCEProcedure extends Procedure {

    public abstract void run() throws SQLException;
}
