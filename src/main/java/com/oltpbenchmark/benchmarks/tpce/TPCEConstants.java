package com.oltpbenchmark.benchmarks.tpce;

import java.util.HashSet;
import java.util.Set;

public abstract class TPCEConstants {

    public static final String INPUT_DATA_PATH = "data/tpce";

    /*
     * Input Files names
     */
    private static final String AREA_FILE        = "AREACODE.txt";
    private static final String CHARGE_FILE      = "CHARGE.txt";
    private static final String COMMRATE_FILE    = "COMMISSION_RATE.txt";
    private static final String COMPANY_FILE     = "COMPANY.txt";
    private static final String COMPANYCOMP_FILE = "COMPANY_COMPETITOR.txt";
    private static final String COMPANYSP_FILE   = "COMPANYSPRATE.txt";
    private static final String EXCHANGE_FILE    = "EXCHANGE.txt";
    private static final String FEMFNAME_FILE    = "FEMALEFIRSTNAME.txt";
    private static final String INDUSTRY_FILE    = "INDUSTRY.txt";
    private static final String LNAME_FILE       = "LASTNAME.txt";
    private static final String MALEFNAME_FILE   = "MALEFIRSTNAME.txt";
    private static final String NONTAXACC_FILE   = "NONTAXABLEACCOUNTNAME.txt";
    private static final String SECTOR_FILE      = "SECTOR.txt";
    private static final String SECURITY_FILE    = "SECURITY.txt";
    private static final String STATUS_FILE      = "STATUS_TYPE.txt";
    private static final String STNAME_FILE      = "STREETNAME.txt";
    private static final String STSUFFIX_FILE    = "STREETSUFFIX.txt";
    private static final String TAXACC_FILE      = "TAXABLEACCOUNTNAME.txt";
    private static final String TAXCOUNTRY_FILE  = "TAXRATESCOUNTRY.txt";
    private static final String TAXDIV_FILE      = "TAXRATESDIVISION.txt";
    private static final String TRADETYPE_FILE   = "TRADE_TYPE.txt";
    private static final String ZIPCODE_FILE     = "ZIP_CODE.txt";


    /**
     *
     */
    // ----------------------------------------------------------------
    // STORED PROCEDURE EXECUTION FREQUENCIES (0-100)
    // TPC-E Specification 6.2.2.1
    // ----------------------------------------------------------------
    public static final int FREQUENCY_BROKER_VOLUME     = 5;    // READ-ONLY
    public static final int FREQUENCY_DATA_MAINTENANCE  = -1;   //
    public static final int FREQUENCY_CUSTOMER_POSITION = 13;   // READ-ONLY
    public static final int FREQUENCY_MARKET_FEED       = 1;    //
    public static final int FREQUENCY_MARKET_WATCH      = 18;   // READ-ONLY
    public static final int FREQUENCY_SECURITY_DETAIL   = 14;   // READ-ONLY
    public static final int FREQUENCY_TRADE_CLEANUP     = -1;   //
    public static final int FREQUENCY_TRADE_LOOKUP      = 8;    // READ-ONLY
    public static final int FREQUENCY_TRADE_ORDER       = 10;   //
    public static final int FREQUENCY_TRADE_RESULT      = 10;   //
    public static final int FREQUENCY_TRADE_STATUS      = 19;   // READ-ONLY
    public static final int FREQUENCY_TRADE_UPDATE      = 2;    //



    /**
     *
     */
    private static enum Transaction {
        BROKER_VOLUME("Broker Volume", "BrokerVolume", TPCEConstants.FREQUENCY_BROKER_VOLUME),

        CUSTOMER_POSITION("Customer Position", "CustomerPosition", TPCEConstants.FREQUENCY_CUSTOMER_POSITION),
//TODO not supported transaction
//        MARKET_FEED("Market Feed", "MarketFeed", TPCEConstants.FREQUENCY_MARKET_FEED),

        MARKET_WATCH("Market Watch", "MarketWatch", TPCEConstants.FREQUENCY_MARKET_WATCH),

        SECURITY_DETAIL("Security Detail", "SecurityDetail", TPCEConstants.FREQUENCY_SECURITY_DETAIL),

        TRADE_LOOKUP("Trade Lookup", "TradeLookup", TPCEConstants.FREQUENCY_TRADE_LOOKUP),

        TRADE_ORDER("Trade Order", "TradeOrder", TPCEConstants.FREQUENCY_TRADE_ORDER),

        //       TRADE_RESULT("Trade Result", "TradeResult", TPCEConstants.FREQUENCY_TRADE_RESULT),

        TRADE_STATUS("Trade Status", "TradeStatus", TPCEConstants.FREQUENCY_TRADE_STATUS),

        TRADE_UPDATE("Trade Update", "TradeUpdate", TPCEConstants.FREQUENCY_TRADE_UPDATE);

        private Transaction(String displayName, String callName, int weight) {
            this.displayName = displayName;
            this.callName = callName;
            this.weight = weight;
        }

        public final String displayName;
        public final String callName;
        public final int weight;
    }


    /**
     * table names
     */
    //
    // Table Names
    //
    public static final String TABLENAME_ZIP_CODE = "ZIP_CODE";
    public static final String TABLENAME_ADDRESS = "ADDRESS";
    public static final String TABLENAME_STATUS_TYPE = "STATUS_TYPE";
    public static final String TABLENAME_TAXRATE = "TAXRATE";
    public static final String TABLENAME_ACCOUNT_PERMISSION = "ACCOUNT_PERMISSION";
    public static final String TABLENAME_CUSTOMER = "CUSTOMER";
    public static final String TABLENAME_EXCHANGE = "EXCHANGE";
    public static final String TABLENAME_SECTOR = "SECTOR";
    public static final String TABLENAME_INDUSTRY = "INDUSTRY";
    public static final String TABLENAME_COMPANY = "COMPANY";
    public static final String TABLENAME_COMPANY_COMPETITOR = "COMPANY_COMPETITOR";
    public static final String TABLENAME_SECURITY = "SECURITY";
    public static final String TABLENAME_DAILY_MARKET = "DAILY_MARKET";
    public static final String TABLENAME_FINANCIAL = "FINANCIAL";
    public static final String TABLENAME_LAST_TRADE = "LAST_TRADE";
    public static final String TABLENAME_NEWS_ITEM = "NEWS_ITEM";
    public static final String TABLENAME_NEWS_XREF = "NEWS_XREF";
    public static final String TABLENAME_BROKER = "BROKER";
    public static final String TABLENAME_CUSTOMER_ACCOUNT = "CUSTOMER_ACCOUNT";
    public static final String TABLENAME_CUSTOMER_TAXRATE = "CUSTOMER_TAXRATE";
    public static final String TABLENAME_TRADE_TYPE = "TRADE_TYPE";
    public static final String TABLENAME_TRADE = "TRADE";
    public static final String TABLENAME_SETTLEMENT = "SETTLEMENT";
    public static final String TABLENAME_TRADE_HISTORY = "TRADE_HISTORY";
    public static final String TABLENAME_HOLDING_SUMMARY = "HOLDING_SUMMARY";
    public static final String TABLENAME_HOLDING = "HOLDING";
    public static final String TABLENAME_HOLDING_HISTORY = "HOLDING_HISTORY";
    public static final String TABLENAME_WATCH_LIST = "WATCH_LIST";
    public static final String TABLENAME_WATCH_ITEM = "WATCH_ITEM";
    public static final String TABLENAME_CASH_TRANSACTION = "CASH_TRANSACTION";
    public static final String TABLENAME_CHARGE = "CHARGE";
    public static final String TABLENAME_COMMISSION_RATE = "COMMISSION_RATE";
    public static final String TABLENAME_TRADE_REQUEST = "TRADE_REQUEST";


    /**
     * table Categories
     */

    public static final Set<String> FIXED_TABLES = new HashSet<String>();
    static {
        FIXED_TABLES.add(TPCEConstants.TABLENAME_CHARGE);
        FIXED_TABLES.add(TPCEConstants.TABLENAME_COMMISSION_RATE);
        FIXED_TABLES.add(TPCEConstants.TABLENAME_EXCHANGE);
        FIXED_TABLES.add(TPCEConstants.TABLENAME_INDUSTRY);
        FIXED_TABLES.add(TPCEConstants.TABLENAME_SECTOR);
        FIXED_TABLES.add(TPCEConstants.TABLENAME_STATUS_TYPE);
        FIXED_TABLES.add(TPCEConstants.TABLENAME_TAXRATE);
        FIXED_TABLES.add(TPCEConstants.TABLENAME_TRADE_TYPE);
        FIXED_TABLES.add(TPCEConstants.TABLENAME_ZIP_CODE);
    };

    public static final Set<String> SCALING_TABLES = new HashSet<String>();
    static {
        SCALING_TABLES.add(TPCEConstants.TABLENAME_ACCOUNT_PERMISSION);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_ADDRESS);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_COMPANY_COMPETITOR);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_COMPANY);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_CUSTOMER_ACCOUNT);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_CUSTOMER_TAXRATE);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_CUSTOMER);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_DAILY_MARKET);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_FINANCIAL);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_LAST_TRADE);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_NEWS_ITEM);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_NEWS_XREF);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_SECURITY);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_WATCH_ITEM);
        SCALING_TABLES.add(TPCEConstants.TABLENAME_WATCH_LIST);
    };

    public static final Set<String> GROWING_TABLES = new HashSet<String>();
    static {
        GROWING_TABLES.add(TPCEConstants.TABLENAME_BROKER);
        GROWING_TABLES.add(TPCEConstants.TABLENAME_CASH_TRANSACTION);
        GROWING_TABLES.add(TPCEConstants.TABLENAME_HOLDING_HISTORY);
        GROWING_TABLES.add(TPCEConstants.TABLENAME_HOLDING_SUMMARY);
        GROWING_TABLES.add(TPCEConstants.TABLENAME_HOLDING);
        GROWING_TABLES.add(TPCEConstants.TABLENAME_SETTLEMENT);
        GROWING_TABLES.add(TPCEConstants.TABLENAME_TRADE_HISTORY);
        GROWING_TABLES.add(TPCEConstants.TABLENAME_TRADE);
    };

    /*
     * Miscellaneous loader parameters
     */
    public static final int  DEFAULT_INITIAL_DAYS = 300;
    public static final int  DEFAULT_LOAD_UNIT    = 1000; // unit size in customers
    public static final long IDENT_SHIFT = 4300000000L;  // All ids are shifted by this
    public static final long TRADE_SHIFT = 200000000000000L;  // 200 trillion (2 * 10^14); trade ids shift

    public static final long DEFAULT_START_CUSTOMER_ID = 1;
    public static final long ACTIVECUSTOMERCOUNT = 5000;


    /*
     * Parameters for scaling tables
     */
    public static final long DEFAULT_COMPANIES_PER_UNIT = 500;
    public static final long DEFAULT_COMPANY_COMPETITORS_PER_UNIT  = 3 * DEFAULT_COMPANIES_PER_UNIT;
    public static final long DEFAULT_SECURITIES_PER_UNIT = 685;

    public static final int  BROKERS_DIV = 100;  // by what number to divide the customer count to get the broker count
    public static final long STARTING_BROKER_ID = 1;
    public static final int  AbortTrade = 101;
    public static final int  MAXHOSTNAME = 64;
    public static final int  MAXDBNAME = 64;
    public static final int  MAXPATH = 512;


    /**
     *
     */
    public static final int newsItemsPerCompany = 2;

    public static final int initialTradePopulationBaseYear      = 2005;
    public static final int initialTradePopulationBaseMonth     = 0; // January, since months are zero-based in Java
    public static final int initialTradePopulationBaseDay       = 3;
    public static final int initialTradePopulationBaseHour      = 9;
    public static final int initialTradePopulationBaseMinute    = 0;
    public static final int initialTradePopulationBaseSecond    = 0;
    public static final int initialTradePopulationBaseFraction  = 0;


    public static final int daysPerWorkWeek = 5;

    /*
     * Constants for securities
     */
    public static final double minSecPrice = 20.00;
    public static final double maxSecPrice = 30.00;

    /*
     * Some importand dates for the generator
     */
    public static final int dailyMarketBaseYear    = 2000;
    public static final int dailyMarketBaseMonth   = 0; // January, since months are zero-based in Java
    public static final int dailyMarketBaseDay     = 3; // it should be Monday, since skipping weekends depends on this
    public static final int dailyMarketBaseHour    = 0;
    public static final int dailyMarketBaseMinute  = 0;
    public static final int dailyMarketBaseSecond  = 0;
    public static final int dailyMarketBaseMsec    = 0;
    public static final int dailyMarketYears = 5;    //number of years of history in DAILY_MARKET


    /*
     *  Trade-Update constants
     */

    public static final int     TradeUpdateMaxTradeHistoryRowsReturned  = 3;    //Based on the maximum number of status changes a trade can go through.
    public static final int     TradeUpdateMaxRows                      = 20;   // Max number of rows for the frames
    public static final int     TradeUpdateFrame1MaxRows                = TradeUpdateMaxRows;
    public static final int     TradeUpdateFrame2MaxRows                = TradeUpdateMaxRows;
    public static final int     TradeUpdateFrame3MaxRows                = TradeUpdateMaxRows;

    /*
     * Trade-Lookup constants
     */
    public static final int     TradeLookupMaxTradeHistoryRowsReturned  = 3;    //Based on the maximum number of status changes a trade can go through.
    public static final int     TradeLookupMaxRows                      = 20;   // Max number of rows for the frames
    public static final int     TradeLookupFrame1MaxRows                = TradeLookupMaxRows;
    public static final int     TradeLookupFrame2MaxRows                = TradeLookupMaxRows;
    public static final int     TradeLookupFrame3MaxRows                = TradeLookupMaxRows;
    public static final int     TradeLookupFrame4MaxRows                = TradeLookupMaxRows;

    // Trade Lookup
    public static final int     TradeLookupAValueForTradeIDGenFrame1    = 65535;
    public static final int     TradeLookupSValueForTradeIDGenFrame1    = 7;
    public static final int     TradeLookupAValueForTimeGenFrame2       = 4095;
    public static final int     TradeLookupSValueForTimeGenFrame2       = 16;
    public static final int     TradeLookupAValueForSymbolFrame3        = 0;
    public static final int     TradeLookupSValueForSymbolFrame3        = 0;
    public static final int     TradeLookupAValueForTimeGenFrame3       = 4095;
    public static final int     TradeLookupSValueForTimeGenFrame3       = 16;
    public static final int     TradeLookupAValueForTimeGenFrame4       = 4095;
    public static final int     TradeLookupSValueForTimeGenFrame4       = 16;
    // Trade Update
    public static final int     TradeUpdateAValueForTradeIDGenFrame1    = 65535;
    public static final int     TradeUpdateSValueForTradeIDGenFrame1    = 7;
    public static final int     TradeUpdateAValueForTimeGenFrame2       = 4095;
    public static final int     TradeUpdateSValueForTimeGenFrame2       = 16;
    public static final int     TradeUpdateAValueForSymbolFrame3        = 0;
    public static final int     TradeUpdateSValueForSymbolFrame3        = 0;
    public static final int     TradeUpdateAValueForTimeGenFrame3       = 4095;
    public static final int     TradeUpdateSValueForTimeGenFrame3       = 16;

    //CUSTOMER table
    public static final int cL_NAME_len       = 25;
    public static final int cF_NAME_len       = 20;
    public static final int cM_NAME_len       = 1;
    public static final int cDOB_len      = 30;
    public static final int cTAX_ID_len       = 20;
    public static final int cGNDR_len     = 1;
    public static final int cCTRY_len = 3;
    public static final int cAREA_len = 3;
    public static final int cLOCAL_len    = 10;
    public static final int cEXT_len  = 5;
    public static final int cEMAIL_len    = 50;


    //COMPANY table
    public static final int cCO_NAME_len = 60;
    public static final int cSP_RATE_len = 4;
    public static final int cCEO_NAME_len = cF_NAME_len + cL_NAME_len + 1;        // one space
    public static final int cCO_DESC_len = 150;
    public static final int cCO_SP_RATE_len = 4;

    public enum eMEETradeRequestAction
    {
        eMEEProcessOrder(0),
        eMEESetLimitOrderTrigger(1);
        private eMEETradeRequestAction(int index){
            this.index = index;
        }
        public int getVal(){
            return index;
        }
        private int index;
    }

    public static enum eStatusTypeID{
        eCompleted(0),
        eActive(1),
        eSubmitted(2),
        ePending(3),
        eCanceled(4),

        eMaxStatusTypeID(5);    // should be the last - contains the number of items in the enumeration
        private eStatusTypeID(int index){
            this.index = index;
        }
        public int getVal(){
            return index;
        }
        private int index;
    }

    // Range of financial rows to return from Security Detail
    public static final int iSecurityDetailMinRows = 5;
    public static final int iSecurityDetailMaxRows = 20;    // max_fin_len

    //DriverType
    public static enum DriverType{
        eDriverEGenLoader(0),
        eDriverAll(1),
        eDriverCE(2),
        eDriverMEE(3),
        eDriverDM(4),
        eDriverMax(5);

        private DriverType(int index){
            this.index = index;
        }
        public int getVal(){
            return index;
        }
        private int index;
    }
    public static String[] szDriverTypeNames = {
        "EGenLoader",
        "EGenDriverAll",
        "EGenDriverCE",
        "EGenDriverMEE",
        "EGenDriverDM"
    };





}
