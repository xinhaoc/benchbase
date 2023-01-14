package com.oltpbenchmark.benchmarks.tpce.procedures;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.benchmarks.tpce.emulator.CE;
import com.oltpbenchmark.benchmarks.tpce.sut.CESUTInterface;
import com.oltpbenchmark.benchmarks.tpce.transactionsinterface.SUTInterfaces;
import com.oltpbenchmark.benchmarks.tpce.transactionsinterface.SUTInterfacesImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TPCEProcedure extends Procedure {

    protected final SUTInterfaces sutInterfaces = SUTInterfacesImpl.getSutInterfaces();

    public boolean run(Connection connection) throws SQLException {
        return true;
    }

}
