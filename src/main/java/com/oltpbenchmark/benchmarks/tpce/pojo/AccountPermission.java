package com.oltpbenchmark.benchmarks.tpce.pojo;

public class AccountPermission {
    private char zc_code;
    private String zc_town;
    private String zc_div;

    public char getZc_code() {
        return zc_code;
    }

    public String getZc_town() {
        return zc_town;
    }

    public String getZc_div() {
        return zc_div;
    }

    @Override
    public String toString() {
        return "AccountPermission{" +
            "zc_code=" + zc_code +
            ", zc_town='" + zc_town + '\'' +
            ", zc_div='" + zc_div + '\'' +
            '}';
    }
}

