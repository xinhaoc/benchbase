package com.oltpbenchmark.benchmarks.tpce;

import com.oltpbenchmark.WorkloadConfiguration;
import com.oltpbenchmark.api.BenchmarkModule;
import com.oltpbenchmark.api.Loader;
import com.oltpbenchmark.api.Worker;
import com.oltpbenchmark.benchmarks.tpce.procedures.BrokerVolume;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TPCEBenchmark extends BenchmarkModule {

    public TPCEBenchmark(WorkloadConfiguration workConf) {
        super(workConf);
    }

    @Override
    protected List<Worker<? extends BenchmarkModule>> makeWorkersImpl() throws IOException {
        List<Worker<? extends BenchmarkModule>> workers = new ArrayList<>();
        for(int i = 0; i < workConf.getTerminals(); i++){
            workers.add(new TPCEWorker(this, 0));
        }
        return workers;
    }

    @Override
    protected Loader<? extends BenchmarkModule> makeLoaderImpl() {
        return new TPCELoader(this);
    }

    @Override
    protected Package getProcedurePackageImpl() {
        return BrokerVolume.class.getPackage();
    }
}
