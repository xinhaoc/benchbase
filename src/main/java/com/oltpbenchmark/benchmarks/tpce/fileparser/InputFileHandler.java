package com.oltpbenchmark.benchmarks.tpce.fileparser;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

public abstract class InputFileHandler {

    private final File inputFile;
    private BufferedReader reader;
    private static final Logger LOG = Logger.getLogger(InputFileHandler.class);

    public InputFileHandler(File inputFile){
        this.inputFile = inputFile;
    }

    public void parseFile() {
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String tupleString = reader.readLine();

            while (tupleString != null && !tupleString.equals("")) {
                insertTuple(tupleString.split("\t"));
                tupleString = reader.readLine();
            }

            LOG.trace("Input file parsed: '" + inputFile + "', maxKey = " + getMaxKey() + ", RecordsNum = " + getRecordsNum());
        }
        catch (FileNotFoundException e) {
            LOG.error("Unable to start benchmark. Missing '" + inputFile.toString() + "' input file");
            System.exit(1);
        }
        catch (IOException e) {
            LOG.error("Unable to read input file '" + inputFile.toString() + "'");
            System.exit(1);
        }
    }

    /*
     * Inserts a tuple into in-memory structure
     */
    protected abstract void insertTuple(String[] tuple);

    /*
     * Returns a tuple by ordinal number
     *
     * The id here is usually strictly defined. See the next function in contrast.
     */
    public abstract String[] getTupleByIndex(int index);

    /*
     * Returns a list of tuples by index.
     *
     * The index here is strictly defined. A list of tuples is returned
     */
    public abstract List<String[]> getTuplesByIndex(int index);

    /*
     * Returns a tuple by key
     *
     * That means the weights (e.g., StreetName, ZipCode, etc.) come into play.
     * Usually it is used when the key is randomly generated.
     *
     *  By default, weights are assumed to be 1.
     */
    public String[] getTupleByKey(int key) {
        return getTupleByIndex(key);
    }

    /*
     * Returns the number of records (tuple or lists of tuples).
     *
     * Can be used to determine the maximum index number
     */
    public abstract int getRecordsNum();

    /*
     * Returns the maximum key number
     *
     * Used for specifying the maximum in random functions
     */
    public int getMaxKey() {
        return getRecordsNum() - 1;
    }
}
