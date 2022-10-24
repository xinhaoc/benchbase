package com.oltpbenchmark.benchmarks.tpce.pojo;

public class ErrorCode {
    // Expected Transaction Status Values
    public static final int SUCCESS = 0;
    public static final int EXPECTED_ROLLBACK = 1;	// returned from Trade-Order Frame 5 to indicate transaction rollback

    // Unexpected Transaction Status Values
    // Negative values are errors
    // Positive values are warnings

    public static final int BVF1_ERROR1 = -111;	// list_len not in [0..max_broker_list_len]

    public static final int CPF1_ERROR1 = -211;	// acct_len not in [1..max_acct_len]
    public static final int CPF2_ERROR1 = -221;	// hist_len not in [min_hist_len..max_hist_len]

    public static final int MFF1_ERROR1 = -311;     // num_updated < unique symbols

    public static final int MWF1_ERROR1 = -411;     // invalid input

    public static final int SDF1_ERROR1 = -511;     // day_len not in [min_day_len..max_day_len]
    public static final int SDF1_ERROR2 = -512;     // fin_len <> max_fin_len
    public static final int SDF1_ERROR3 = -513;     // news_len <> max_news_len

    public static final int TLF1_ERROR1 = -611;     // num_found <> max_trades
    public static final int TLF2_ERROR1 = -621;     // num_found not in [0..max_trades]
    public static final int TLF2_WARN1  = +621;     // num_found == 0
    public static final int TLF3_ERROR1 = -631;     // num_found not in [0..max_trades]
    public static final int TLF3_WARN1  = +631;     // num_found == 0
    public static final int TLF4_ERROR1 = -641;     // num_trades_found not in [0..1]
    public static final int TLF4_WARN1  = +641;     // num_trades_found == 0
    public static final int TLF4_ERROR2 = -642;     // num_found not in [1..20]

    public static final int TOF1_ERROR1 = -711;     // num_found <> 1
    public static final int TOF2_ERROR1 = -721;     // ap_acl[0] == '\0'
    public static final int TOF3_ERROR1 = -731;     // tax_amount == 0 (for profitable; taxable trade)
    public static final int TOF3_ERROR2 = -732;     // comm_rate == 0
    public static final int TOF3_ERROR3 = -733;     // charge_amount == 0

    public static final int TRF1_ERROR1 = -811;     // num_found <> 1
    public static final int TRF3_ERROR1 = -831;     // tax_amount < 0
    public static final int TRF4_ERROR1 = -841;     // comm_rate <= 0

    public static final int TSF1_ERROR1 = -911;     // num_found <> max_trade_status_len

    public static final int TUF1_ERROR1 = -1011;    // num_found <> max_trades || num_updated <> max_updates
    public static final int TUF2_ERROR1 = -1021;    // num_updated <> num_found || num_found not in [0..max_trades]
    public static final int TUF2_WARN1  = +1021;    // num_updated == 0
    public static final int TUF3_ERROR1 = -1031;    // num_updated <> num_found || num_found not in [0..max_trades]
    public static final int TUF3_WARN1  = +1031;    // num_updated == 0
}
