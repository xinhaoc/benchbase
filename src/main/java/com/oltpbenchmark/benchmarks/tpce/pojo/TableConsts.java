package com.oltpbenchmark.benchmarks.tpce.pojo;

public class TableConsts {
    public static int cTOWN_len = 80;
    public static int cDIV_len = 80;
    public static int cCODE_len = 12;

    //ACCOUNT_PERMISSION table
    public static int cACL_len = 4;

    //ADDRESS table
    public static int cAD_NAME_len = 80;
    public static int cAD_LINE_len = 80;
    public static int cAD_TOWN_len = cTOWN_len;
    public static int cAD_DIV_len = cDIV_len;   //state/provice abreviation
    public static int cAD_ZIP_len = cCODE_len;
    public static int cAD_CTRY_len = 80;

    //CASH_TRANSACTION table
    public static int cCT_NAME_len = 100;

    //CUSTOMER table
    public static int cL_NAME_len = 25;
    public static int cF_NAME_len = 20;
    public static int cM_NAME_len = 1;
    public static int cDOB_len = 30;
    public static int cTAX_ID_len = 20;
    public static int cGNDR_len = 1;
    public static int cCTRY_len = 3;
    public static int cAREA_len = 3;
    public static int cLOCAL_len = 10;
    public static int cEXT_len = 5;
    public static int cEMAIL_len = 50;

    //BROKER table
    public static int cB_NAME_len = cF_NAME_len + cM_NAME_len + cL_NAME_len + 3;    // two spaces and one period

    //COMPANY table
    public static int cCO_NAME_len = 60;
    public static int cSP_RATE_len = 4;
    public static int cCEO_NAME_len = cF_NAME_len + cL_NAME_len + 1;        // one space
    public static int cCO_DESC_len = 150;
    public static int cCO_SP_RATE_len = 4;

    //CUSTOMER_ACCOUNT table
    public static int cCA_NAME_len = 50;

    //EXCHANGE table
    public static int cEX_ID_len = 6;
    public static int cEX_NAME_len = 100;
    public static int cEX_DESC_len = 150;
    //public static int cEX_OPEN_len = 8;
    //public static int cEX_CLOSE_len = 8;

    //HOLDING table
    public static int cH_BUY_DTS_len = 30;  //date of purchase

    //INDUSTRY table
    public static int cIN_ID_len = 2;
    public static int cIN_NAME_len = 50;

    //NEWS_ITEM table
    public static int cNI_HEADLINE_len = 80;
    public static int cNI_SUMMARY_len = 255;
    public static int cNI_ITEM_len = 100 * 1000;
    public static int cNI_SOURCE_len = 30;
    public static int cNI_AUTHOR_len = 30;

    //SECURITY table
    public static int cS_NAME_len = 70;
    public static int cSYMBOL_len = 7 + 1 + 7;  // base + separator + extended
    public static int cS_ISSUE_len = 6;

    //SETTLEMENT table
    public static int cSE_CASH_TYPE_len = 40;

    //SECTOR table
    public static int cSC_NAME_len = 30;
    public static int cSC_ID_len = 2;

    //STATUS_TYPE table
    public static int cST_ID_len = 4;
    public static int cST_NAME_len = 10;

    //TAX RATE table
    public static int cTX_ID_len = 4;
    public static int cTX_NAME_len = 50;

    //TRADE table
    public static int cEXEC_NAME_len = cF_NAME_len + cM_NAME_len + cL_NAME_len + 3; // two spaces and one extra

    //TRADE_HISTORY table
    public static int cTH_ST_ID_len = cST_ID_len;

    //TRADE TYPE table
    public static int cTT_ID_len = 3;
    public static int cTT_NAME_len = 12;

    //ZIP_CODE table
    public static int cZC_TOWN_len = cTOWN_len;
    public static int cZC_DIV_len = cDIV_len;
    public static int cZC_CODE_len = cCODE_len;


}
