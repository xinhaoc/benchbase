CREATE OR REPLACE FUNCTION TradeStatusFrame1(
    IN acct_id BIGINT,
    OUT broker_name VARCHAR(100),
    OUT charge_list NUMERIC(10, 2)[],
    OUT cust_f_name VARCHAR(30),
    OUT cust_l_name VARCHAR(30),
    OUT ex_name_list VARCHAR(100)[],
    OUT exec_name_list VARCHAR(64)[],
    OUT num_found BIGINT,
    OUT s_name_list VARCHAR(70)[],
    OUT st_name_list VARCHAR(10)[],
    OUT symbol VARCHAR(15)[],
    OUT trade_dts_list TIMESTAMP[],
    OUT trade_id_list BIGINT[],
    OUT trade_qty_list INTEGER[],
    OUT type_name_list VARCHAR(12)[])
    RETURNS SETOF record AS
$$
DECLARE
    -- output parameters
--     cust_l_name VARCHAR;
--     cust_f_name VARCHAR;
--     broker_name VARCHAR;

    -- variables
    rs          RECORD;
BEGIN
    -- Only want 50 rows, the 50 most recent trades for this customer account
    SELECT C_L_NAME,
           C_F_NAME,
           B_NAME
    INTO cust_l_name,
        cust_f_name,
        broker_name
    FROM CUSTOMER_ACCOUNT,
         CUSTOMER,
         BROKER
    WHERE CA_ID = acct_id
      AND C_ID = CA_C_ID
      AND B_ID = CA_B_ID;
    num_found := 0;
    FOR rs IN
        SELECT T_CHRG,
               T_EXEC_NAME,
               EX_NAME,
               S_NAME,
               ST_NAME,
               T_S_SYMB,
               T_DTS,
               T_ID,
               T_QTY,
               TT_NAME
        FROM TRADE,
             STATUS_TYPE,
             TRADE_TYPE,
             SECURITY,
             EXCHANGE
        WHERE T_CA_ID = acct_id
          AND ST_ID = T_ST_ID
          AND TT_ID = T_TT_ID
          AND S_SYMB = T_S_SYMB
          AND EX_ID = S_EX_ID
        ORDER BY T_DTS desc
        LIMIT 50

        LOOP
            num_found := num_found + 1;
            charge_list[num_found] = rs.T_CHRG;
            exec_name_list[num_found] = rs.T_EXEC_NAME;
            ex_name_list[num_found] = rs.EX_NAME;
            s_name_list[num_found] = rs.S_NAME;
            st_name_list[num_found] = rs.ST_NAME;
            symbol[num_found] =rs. T_S_SYMB;
            trade_dts_list[num_found] = rs.T_DTS;
            trade_id_list[num_found] = rs.T_ID;
            trade_qty_list[num_found] = rs.T_QTY;
            type_name_list[num_found] = rs.TT_NAME;
        END LOOP;
    RETURN NEXT;
END;
$$ LANGUAGE 'plpgsql';
