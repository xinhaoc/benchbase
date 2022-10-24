package com.oltpbenchmark.benchmarks.tpce.pojo;


public class TxnHarnessStructs {

    public static final int iFinYears = 5;
    public static final int iFinQtrPerYear = 4;
    public static final int iMaxDailyHistory = 10;
    public static final int iMaxNews = 10;

    public static final int min_broker_list_len = 20;   // for Broker-Volume
    public static final int max_broker_list_len = 40;   // for Broker-Volume
    public static final int max_feed_len = 20;      // for Market-Feed
    public static final int min_day_len = 5;        // for Security-Detail
    public static final int max_day_len = 20;       // for Security-Detail
    public static final int max_fin_len = 20;       // for Security-Detail
    public static final int max_news_len = 2;       // for Security-Detail
    public static final int max_comp_len = 3;       // for Security-Detail
    public static final int max_trade_status_len = 50;  // for Trade-Status

    public static final int max_table_name = 30;    // for Data Maintenance

    public static final int   iBaseCompanyCount           = 5000;                     // number of base companies in the flat file
    public static final int   iBaseCompanyCompetitorCount = 3 * iBaseCompanyCount;    // number of base company competitor rows
    public static final int   iOneLoadUnitCompanyCount    = 500;
    public static final int   iOneLoadUnitSecurityCount   = 685;
    public static final int   iOneLoadUnitCompanyCompetitorCount  = 3 * iOneLoadUnitCompanyCount;
}

