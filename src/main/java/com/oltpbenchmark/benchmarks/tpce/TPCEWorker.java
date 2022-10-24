package com.oltpbenchmark.benchmarks.tpce;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.TransactionType;
import com.oltpbenchmark.api.Worker;
import com.oltpbenchmark.benchmarks.tpcc.TPCCWorker;
import com.oltpbenchmark.types.TransactionStatus;
import com.oltpbenchmark.api.Procedure.UserAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TPCEWorker extends Worker<TPCEBenchmark> {

    private static final Logger LOG = LoggerFactory.getLogger(TPCEWorker.class);

    public TPCEWorker(TPCEBenchmark benchmarkModule, int id){
        super(benchmarkModule, id);
    }

    @Override
    protected TransactionStatus executeWork(Connection conn, TransactionType txnType) throws UserAbortException, SQLException {
        return null;
    }
}
