CREATE OR REPLACE FUNCTION CustomerPositionFrame1 (
		INOUT cust_id BIGINT,
		IN tax_id VARCHAR(20),
		OUT acct_id BIGINT[],
		OUT acct_len INTEGER,
		OUT asset_total NUMERIC(14,2)[],
		OUT c_ad_id BIGINT,
		OUT c_area_1 VARCHAR(3),
		OUT c_area_2 VARCHAR(3),
		OUT c_area_3 VARCHAR(3),
		OUT c_ctry_1 VARCHAR(3),
		OUT c_ctry_2 VARCHAR(3),
		OUT c_ctry_3 VARCHAR(3),
		OUT c_dob DATE,
		OUT c_email_1 VARCHAR(50),
		OUT c_email_2 VARCHAR(50),
		OUT c_ext_1 VARCHAR(5),
		OUT c_ext_2 VARCHAR(5),
		OUT c_ext_3 VARCHAR(5),
		OUT c_f_name VARCHAR(30),
		OUT c_gndr VARCHAR(1),
		OUT c_l_name VARCHAR(30),
		OUT c_local_1 VARCHAR(10),
		OUT c_local_2 VARCHAR(10),
		OUT c_local_3 VARCHAR(10),
		OUT c_m_name VARCHAR(1),
		OUT c_st_id VARCHAR(4),
		OUT c_tier SMALLINT,
		OUT cash_bal NUMERIC(12, 2)[],
		OUT status INTEGER)
RETURNS SETOF record AS $$
DECLARE
r RECORD;
BEGIN
	IF cust_id = 0 THEN
SELECT	C_ID
INTO	cust_id
FROM	CUSTOMER
WHERE	C_TAX_ID = tax_id;
END IF;

SELECT	CUSTOMER.C_ST_ID,
        CUSTOMER.C_L_NAME,
        CUSTOMER.C_F_NAME,
        CUSTOMER.C_M_NAME,
        CUSTOMER.C_GNDR,
        CUSTOMER.C_TIER,
        CUSTOMER.C_DOB,
        CUSTOMER.C_AD_ID,
        CUSTOMER.C_CTRY_1,
        CUSTOMER.C_AREA_1,
        CUSTOMER.C_LOCAL_1,
        CUSTOMER.C_EXT_1,
        CUSTOMER.C_CTRY_2,
        CUSTOMER.C_AREA_2,
        CUSTOMER.C_LOCAL_2,
        CUSTOMER.C_EXT_2,
        CUSTOMER.C_CTRY_3,
        CUSTOMER.C_AREA_3,
        CUSTOMER.C_LOCAL_3,
        CUSTOMER.C_EXT_3,
        CUSTOMER.C_EMAIL_1,
        CUSTOMER.C_EMAIL_2
INTO	C_ST_ID,
    C_L_NAME,
    C_F_NAME,
    C_M_NAME,
    C_GNDR,
    C_TIER,
    C_DOB,
    C_AD_ID,
    C_CTRY_1,
    C_AREA_1,
    C_LOCAL_1,
    C_EXT_1,
    C_CTRY_2,
    C_AREA_2,
    C_LOCAL_2,
    C_EXT_2,
    C_CTRY_3,
    C_AREA_3,
    C_LOCAL_3,
    C_EXT_3,
    C_EMAIL_1,
    C_EMAIL_2
FROM	CUSTOMER
WHERE	c_id = cust_id;

-- Should return 1 to max_acct_len.
acct_len := 0;
FOR r IN
SELECT CA_ID,
       CA_BAL,
       sum(HS_QTY * LT_PRICE) as soma
FROM CUSTOMER_ACCOUNT left outer join
     HOLDING_SUMMARY on HS_CA_ID = CA_ID,
     LAST_TRADE
WHERE CA_C_ID = cust_id
  AND LT_S_SYMB = HS_S_SYMB
GROUP BY CA_ID, CA_BAL
ORDER BY 3 asc
    LIMIT 10
	LOOP
		acct_len := acct_len + 1;
        acct_id[acct_len] := r.CA_ID;
		cash_bal[acct_len] := r.CA_BAL;

		IF r.soma is null THEN
			asset_total[acct_len] := 0.00;
ELSE
			asset_total[acct_len] := r.soma;
END IF;
END LOOP;

	status := 0;

	RETURN NEXT;
END;
$$ LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION CustomerPositionFrame2(
		IN acct_id BIGINT,
		OUT hist_dts TIMESTAMP[],
		OUT hist_len INTEGER,
		OUT qty INTEGER[],
		OUT status INTEGER,
		OUT symbol VARCHAR(15)[],
		OUT trade_id BIGINT[],
		OUT trade_status VARCHAR(10)[])
RETURNS SETOF record AS $$
DECLARE
r		RECORD;
BEGIN
	hist_len := 0;
FOR r IN
SELECT T_ID,
       T_S_SYMB,
       T_QTY,
       ST_NAME,
       TH_DTS
FROM (SELECT T_ID as ID
      FROM TRADE
      WHERE T_CA_ID = acct_id
      ORDER BY T_DTS desc LIMIT 10) as T,
     TRADE,
     TRADE_HISTORY,
     STATUS_TYPE
WHERE T_ID = ID
  AND TH_T_ID = T_ID
  AND ST_ID = TH_ST_ID
ORDER BY TH_DTS desc
    LIMIT 30
	LOOP
        hist_len = hist_len + 1;
		trade_id[hist_len] := r.t_id;
        symbol[hist_len] := r.t_s_symb;
		qty[hist_len] := r.t_qty;
		trade_status[hist_len] := r.st_name;
		hist_dts[hist_len] := r.th_dts;
END LOOP;

	status := 0;

	RETURN NEXT;
END;
$$ LANGUAGE 'plpgsql';