package com.oltpbenchmark;
public class TestDBWorkLoad {

    public static void testLoadDataBase() throws Exception {

        String args[] = new String[7];
        args[0] = "-b";
        args[1] = "tpce";
        args[2] = "-c";
        args[3] = "config/postgres/sample_tpce_config.xml";
        args[4] = "--create=true";
        args[5] = "--load=true";
        args[6] = "--execute=true";
        DBWorkload.main(args);

    }

    public static void main(String[] args) throws Exception {
        testLoadDataBase();
    }

}
