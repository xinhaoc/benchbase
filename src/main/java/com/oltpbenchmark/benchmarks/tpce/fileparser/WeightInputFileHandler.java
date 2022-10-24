package com.oltpbenchmark.benchmarks.tpce.fileparser;

import java.io.File;
import java.util.List;

public class WeightInputFileHandler extends InputFileHandler {
    public WeightInputFileHandler(File inputFile) {
        super(inputFile);
    }

    @Override
    protected void insertTuple(String[] tuple) {

    }

    @Override
    public String[] getTupleByIndex(int index) {
        return new String[0];
    }

    @Override
    public List<String[]> getTuplesByIndex(int index) {
        return null;
    }

    @Override
    public int getRecordsNum() {
        return 0;
    }
}
