package com.oltpbenchmark.benchmarks.tpce;

import com.oltpbenchmark.WorkloadConfiguration;
import com.oltpbenchmark.api.BenchmarkModule;
import com.oltpbenchmark.api.Loader;
import com.oltpbenchmark.api.Worker;

import java.io.IOException;
import java.util.List;

public class TPCEBenchmark extends BenchmarkModule {

    public TPCEBenchmark(WorkloadConfiguration workConf) {
        super(workConf);
    }

    @Override
    protected List<Worker<? extends BenchmarkModule>> makeWorkersImpl() throws IOException {
        return null;
    }

    @Override
    protected Loader<? extends BenchmarkModule> makeLoaderImpl() {
        return new TPCELoader(this);
    }

    @Override
    protected Package getProcedurePackageImpl() {
        return null;
    }
}
