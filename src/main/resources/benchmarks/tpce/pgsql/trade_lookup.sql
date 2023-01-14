CREATE OR REPLACE FUNCTION TradeLookupFrame1(
    IN max_trades integer,
    IN trade_id bigint[],
    OUT bid_price_list NUMERIC(8, 2)[],
    OUT exec_name_list varchar[],
    OUT is_cash_list smallint[],
    OUT is_market_list smallint[],
    OUT trade_price_list numeric(8, 2)[],
    OUT settlement_amount_list numeric(10, 2)[],
    OUT settlement_cash_due_date_list timestamp[],
    OUT settlement_cash_type_list varchar[],
    OUT cash_transaction_amount_list numeric(10, 2)[],
    OUT cash_transaction_dts_list timestamp[],
    OUT trade_history_dts_1_list timestamp[],
    OUT trade_history_dts_2_list timestamp[],
    OUT trade_history_dts_3_list timestamp[],
    OUT trade_history_status_id_1_list char(4)[],
    OUT trade_history_status_id_2_list char(4)[],
    OUT trade_history_status_id_3_list char(4)[],
    OUT cash_transaction_name_list varchar[],
    OUT num_found Integer) RETURNS SETOF record AS
$$
DECLARE
    -- output parameters
    bid_price                numeric(8, 2);
    exec_name                varchar;
    is_cash                  smallint;
    is_market                smallint;
    trade_price              numeric(8, 2);
    settlement_amount        numeric(10, 2);
    settlement_cash_due_date timestamp;
    settlement_cash_type     varchar;
    cash_transaction_amount  numeric(10, 2);
    cash_transaction_dts     timestamp;
    cash_transaction_name    varchar;
    trade_history_dts        timestamp[];
    trade_history_status_id  char(4)[];

    -- variables
    i                        integer;
    j                        integer;
-- 	num_found	integer;
    rs                       RECORD;
BEGIN
    num_found = max_trades;

    i = 1;
    WHILE i <= max_trades
        LOOP
        -- Get trade information
        -- Should only return one row for each trade

            SELECT T_BID_PRICE,
                   T_EXEC_NAME,
                   T_IS_CASH,
                   TT_IS_MRKT,
                   T_TRADE_PRICE
            INTO bid_price,
                exec_name,
                is_cash,
                is_market,
                trade_price
            FROM TRADE,
                 TRADE_TYPE
            WHERE T_ID = trade_id[i]
              AND T_TT_ID = TT_ID;

            -- Get settlement information
            -- Should only return one row for each trade

            SELECT SE_AMT,
                   SE_CASH_DUE_DATE,
                   SE_CASH_TYPE
            INTO settlement_amount,
                settlement_cash_due_date,
                settlement_cash_type
            FROM SETTLEMENT
            WHERE SE_T_ID = trade_id[i];

            -- get cash information if this is a cash transaction
            -- Should only return one row for each trade that was a cash transaction

            IF is_cash THEN
                SELECT CT_AMT,
                       CT_DTS,
                       CT_NAME
                INTO cash_transaction_amount,
                    cash_transaction_dts,
                    cash_transaction_name
                FROM CASH_TRANSACTION
                WHERE CT_T_ID = trade_id[i];
            END IF;

            -- read trade_history for the trades
            -- Should return 2 to 3 rows per trade

            j = 1;
            FOR rs IN SELECT TH_DTS, TH_ST_ID
                      FROM TRADE_HISTORY
                      WHERE TH_T_ID = trade_id[i]
                      ORDER BY TH_DTS
                LOOP
                    trade_history_dts[j] = rs.TH_DTS;
                    trade_history_status_id[j] = rs.TH_ST_ID;
                    j = j + 1;
                END LOOP;

            FOR rs IN
                select bid_price,
                       exec_name,
                       is_cash,
                       is_market,
                       trade_price,
                       settlement_amount,
                       settlement_cash_due_date,
                       settlement_cash_type,
                       cash_transaction_amount,
                       cash_transaction_dts,
                       cash_transaction_name,
                       trade_history_dts[1]       as trade_history_dts_1,
                       trade_history_status_id[1] as trade_history_status_id_1,
                       trade_history_dts[2]       as trade_history_dts_2,
                       trade_history_status_id[2] as trade_history_status_id_2,
                       trade_history_dts[3]       as trade_history_dts_3,
                       trade_history_status_id[3] as trade_history_status_id_3
                LOOP
                    cash_transaction_name_list[i] = rs.cash_transaction_name;
                    bid_price_list[i] = rs.bid_price;
                    exec_name_list[i] = rs.exec_name;
                    is_cash_list[i] = rs.is_cash;
                    is_market_list[i] = rs.is_market;
                    trade_price_list[i] = rs.trade_price;
                    settlement_amount_list[i] = rs.settlement_amount;
                    settlement_cash_due_date_list[i] = rs.settlement_cash_due_date;
                    settlement_cash_type_list[i] = rs.settlement_cash_type;
                    cash_transaction_amount_list[i] = rs.cash_transaction_amount;
                    cash_transaction_dts_list[i] = rs.cash_transaction_dts;
                    trade_history_dts_1_list[i] = rs.trade_history_dts_1;
                    trade_history_dts_2_list[i] = rs.trade_history_dts_2;
                    trade_history_dts_3_list[i] = rs.trade_history_dts_3;
                    trade_history_status_id_1_list[i] = rs.trade_history_status_id_1;
                    trade_history_status_id_2_list[i] = rs.trade_history_status_id_2;
                    trade_history_status_id_3_list[i] = rs.trade_history_status_id_3;
                END LOOP;

            i = i + 1;
        END LOOP;
    RETURN NEXT;
END;
$$ LANGUAGE 'plpgsql';


/*
 * Frame 2
 * returns information for the first N (max_trades) trades executed for the
 * specified customer account at or after the specified time.
 */

CREATE OR REPLACE FUNCTION TradeLookupFrame2(
    IN acct_id BIGINT,
    IN max_trades integer,
    IN start_trade_dts timestamp,
    IN end_trade_dts timestamp,
    OUT bid_price_list NUMERIC(8, 2)[],
    OUT cash_transaction_amount_list NUMERIC(10, 2)[],
    OUT cash_transaction_dts_list TIMESTAMP[],
    OUT cash_transaction_name_list VARCHAR(100)[],
    OUT exec_name_list VARCHAR(64)[],
    OUT is_cash_list SMALLINT[],
    OUT t_id_list BIGINT[],
    OUT num_found INTEGER,
    OUT settlement_amount_list NUMERIC(10, 2)[],
    OUT settlement_cash_due_date_list TIMESTAMP[],
    OUT settlement_cash_type_list VARCHAR(40)[],
    OUT trade_history_dts_1_list TIMESTAMP[],
    OUT trade_history_dts_2_list TIMESTAMP[],
    OUT trade_history_dts_3_list TIMESTAMP[],
    OUT trade_history_status_id_1_list VARCHAR(4)[],
    OUT trade_history_status_id_2_list VARCHAR(4)[],
    OUT trade_history_status_id_3_list VARCHAR(4)[],
    OUT trade_price_list NUMERIC(8, 2)[]) RETURNS SETOF record AS
$$
DECLARE
    -- output parameters
    settlement_amount        FLOAT;
    settlement_cash_due_date timestamp;
    settlement_cash_type     varchar;
    cash_transaction_amount  FLOAT;
    cash_transaction_dts     timestamp;
    cash_transaction_name    varchar;
    trade_history_dts        timestamp[];
    trade_history_status_id  char(4)[];

    -- variables
    i                        integer;
    j                        integer;
    rs                       RECORD;
    aux                      RECORD;
    index                    integer;
BEGIN
    -- Get trade information
    -- Should return between 0 and max_trades rows

    i = 0;
    index = 1;
    num_found = 0;
    FOR rs IN SELECT T_BID_PRICE,
                     T_EXEC_NAME,
                     T_IS_CASH,
                     T_ID,
                     T_TRADE_PRICE
              FROM TRADE
              WHERE T_CA_ID = acct_id
                AND T_DTS >= start_trade_dts
                AND T_DTS <= end_trade_dts
              ORDER BY T_DTS asc
              LIMIT max_trades
        LOOP
        -- Get settlement information
        -- Should return only one row for each trade

            SELECT SE_AMT,
                   SE_CASH_DUE_DATE,
                   SE_CASH_TYPE
            INTO settlement_amount,
                settlement_cash_due_date,
                settlement_cash_type
            FROM SETTLEMENT
            WHERE SE_T_ID = rs.T_ID;

            -- get cash information if this is a cash transaction
            -- Should return only one row for each trade that was a cash transaction

            IF rs.T_IS_CASH THEN
                SELECT CT_AMT,
                       CT_DTS,
                       CT_NAME
                INTO cash_transaction_amount,
                    cash_transaction_dts,
                    cash_transaction_name
                FROM CASH_TRANSACTION
                WHERE CT_T_ID = rs.T_ID;
            END IF;

            -- read trade_history for the trades
            -- Should return 2 to 3 rows per trade

            j = 1;
            FOR aux IN SELECT TH_DTS, TH_ST_ID
                       FROM TRADE_HISTORY
                       WHERE TH_T_ID = rs.T_ID
                       ORDER BY TH_DTS
                LOOP
                    trade_history_dts[j] = aux.TH_DTS;
                    trade_history_status_id[j] = aux.TH_ST_ID;
                    j = j + 1;
                END LOOP;

            FOR aux IN
                SELECT rs.T_BID_PRICE::FLOAT      as bid_price,
                       rs.T_EXEC_NAME             as exec_name,
                       rs.T_IS_CASH               as is_cash,
                       rs.T_TRADE_PRICE::FLOAT    as trade_price,
                       rs.T_ID::BIGINT            as t_id,
                       settlement_amount,
                       settlement_cash_due_date,
                       settlement_cash_type,
                       cash_transaction_amount,
                       cash_transaction_dts,
                       cash_transaction_name,
                       trade_history_dts[1]       as trade_history_dts_1,
                       trade_history_status_id[1] as trade_history_status_id_1,
                       trade_history_dts[2]       as trade_history_dts_2,
                       trade_history_status_id[2] as trade_history_status_id_2,
                       trade_history_dts[3]       as trade_history_dts_3,
                       trade_history_status_id[3] as trade_history_status_id_3
                LOOP
                    bid_price_list[index] = aux.bid_price;
                    cash_transaction_amount_list[index] = aux.cash_transaction_amount;
                    cash_transaction_dts_list[index] = aux.cash_transaction_dts;
                    cash_transaction_name_list[index] = aux.cash_transaction_name;
                    exec_name_list[index] = aux.exec_name;
                    is_cash_list[index] = aux.is_cash;
                    t_id_list[index] = aux.t_id;
                    settlement_amount_list[index] = aux.settlement_amount;
                    settlement_cash_due_date_list[index] = aux.settlement_cash_due_date;
                    settlement_cash_type_list[index] = aux.settlement_cash_type;
                    trade_history_dts_1_list[index] = aux.trade_history_dts_1;
                    trade_history_dts_2_list[index] = aux.trade_history_dts_2;
                    trade_history_dts_3_list[index] = aux.trade_history_dts_3;
                    trade_history_status_id_1_list[index] = aux.trade_history_status_id_1;
                    trade_history_status_id_2_list[index] = aux.trade_history_status_id_2;
                    trade_history_status_id_3_list[index] = aux.trade_history_status_id_3;
                    trade_price_list[index] = aux.trade_price;
                END LOOP;

            i = i + 1;
            index = index + 1;
            num_found = num_found + 1;
        END LOOP;
    RETURN next;
END;
$$ LANGUAGE 'plpgsql';


/*
 * Frame 3
 * returns up to N (max_trades) trades for a given security on or after a
 * specified point in time.
 */

CREATE OR REPLACE FUNCTION TradeLookupFrame3(
    IN max_acct_id BIGINT,
    IN max_trades integer,
    IN start_trade_dts timestamp,
    IN end_trade_dts timestamp,
    IN symbol char(15),
    OUT t_ca_id_list BIGINT[],
    OUT t_id_list BIGINT[],
    OUT t_tt_id_list CHAR(3)[],
    OUT cash_transaction_amount_list NUMERIC(10, 2)[],
    OUT cash_transaction_dts_list TIMESTAMP[],
    OUT cash_transaction_name_list VARCHAR(100)[],
    OUT exec_name_list VARCHAR(64)[],
    OUT is_cash_list SMALLINT[],
    OUT t_qty_list INTEGER[],
    OUT num_found INTEGER,
    OUT settlement_amount_list NUMERIC(10, 2)[],
    OUT settlement_cash_due_date_list TIMESTAMP[],
    OUT settlement_cash_type_list VARCHAR(40)[],
    OUT t_dts_list TIMESTAMP[],
    OUT trade_history_dts_1_list TIMESTAMP[],
    OUT trade_history_dts_2_list TIMESTAMP[],
    OUT trade_history_dts_3_list TIMESTAMP[],
    OUT trade_history_status_id_1_list VARCHAR(4)[],
    OUT trade_history_status_id_2_list VARCHAR(4)[],
    OUT trade_history_status_id_3_list VARCHAR(4)[],
    OUT trade_price_list NUMERIC(8, 2)[]) RETURNS SETOF record AS
$$
DECLARE
    -- output parameters
    settlement_amount        FLOAT;
    settlement_cash_due_date timestamp;
    settlement_cash_type     varchar;
    cash_transaction_amount  FLOAT;
    cash_transaction_dts     timestamp;
    cash_transaction_name    varchar;
    trade_history_dts        timestamp[];
    trade_history_status_id  char(4)[];

    -- Local Frame variables
    i                        integer;
    j                        integer;
    rs                       RECORD;
    aux                      RECORD;
    index                    integer;
BEGIN
    -- Should return between 0 and max_trades rows.

    i = 0;
    index :=                   1;
    num_found := 0;
    FOR rs IN SELECT T_CA_ID,
                     T_EXEC_NAME,
                     T_IS_CASH,
                     T_ID,
                     T_TRADE_PRICE,
                     T_QTY,
                     T_DTS,
                     T_TT_ID
              FROM TRADE
              WHERE T_S_SYMB = symbol
                AND T_DTS >= start_trade_dts
                AND T_DTS <= end_trade_dts
                AND T_CA_ID <= max_acct_id
              ORDER BY T_DTS asc
              LIMIT max_trades
        LOOP
        -- Get extra information for each trade in the trade list.
        -- Get settlement information
        -- Should return only one row for each trade

            SELECT SE_AMT,
                   SE_CASH_DUE_DATE,
                   SE_CASH_TYPE
            INTO settlement_amount,
                settlement_cash_due_date,
                settlement_cash_type
            FROM SETTLEMENT
            WHERE SE_T_ID = rs.T_ID;

            -- get cash information if this is a cash transaction
            -- Should return only one row for each trade that was a cash transaction

            IF rs.T_IS_CASH THEN
                SELECT CT_AMT,
                       CT_DTS,
                       CT_NAME
                INTO cash_transaction_amount,
                    cash_transaction_dts,
                    cash_transaction_name
                FROM CASH_TRANSACTION
                WHERE CT_T_ID = rs.T_ID;
            END IF;

            -- read trade_history for the trades
            -- Should return 2 to 3 rows per trade

            j = 1;
            FOR aux IN SELECT TH_DTS, TH_ST_ID
                       FROM TRADE_HISTORY
                       WHERE TH_T_ID = rs.T_ID
                       ORDER BY TH_DTS
                LOOP
                    trade_history_dts[j] = aux.TH_DTS;
                    trade_history_status_id[j] = aux.TH_ST_ID;
                    j = j + 1;
                END LOOP;

            FOR aux IN
                SELECT 0,
                       rs.T_CA_ID as t_ca_id,
                       cash_transaction_amount,
                       cash_transaction_dts,
                       cash_transaction_name,
                       rs.T_EXEC_NAME as t_exe_name,
                       rs.T_IS_CASH as t_is_cash,
                       rs.T_TRADE_PRICE as t_trade_price,
                       rs.T_QTY as t_qty,
                       settlement_amount,
                       settlement_cash_due_date,
                       settlement_cash_type,
                       rs.T_DTS as t_dts,
                       trade_history_dts[1] as trade_history_dts_1,
                       trade_history_status_id[1] as trade_history_status_id_1,
                       trade_history_dts[2] as trade_history_dts_2,
                       trade_history_status_id[2] as trade_history_status_id_2,
                       trade_history_dts[3] as trade_history_dts_3,
                       trade_history_status_id[3] as trade_history_status_id_3,
                       rs.T_ID as t_id,
                       rs.T_TT_ID as t_tt_id
                LOOP
                    t_ca_id_list[index] =  aux.t_ca_id;
                    t_id_list[index] = aux.t_id;
                    t_tt_id_list[index] = aux.t_tt_id;
                    cash_transaction_amount_list[index] = aux.cash_transaction_amount;
                    cash_transaction_dts_list[index] = aux.cash_transaction_dts;
                    cash_transaction_name_list[index] = aux.cash_transaction_name;
                    exec_name_list[index] = aux.t_exe_name;
                    is_cash_list[index] = aux.t_is_cash;
                    trade_price_list[index] = aux.t_trade_price;
                    t_qty_list[index] = aux.t_qty;
                    settlement_amount_list[index] = aux.settlement_amount;
                    settlement_cash_due_date_list[index] = aux.settlement_cash_due_date;
                    t_dts_list[index] = aux.t_dts;
                    settlement_cash_type_list[index] = aux.settlement_cash_type;
                    trade_history_dts_1_list[index] = aux.trade_history_dts_1;
                    trade_history_dts_2_list[index] = aux.trade_history_dts_2;
                    trade_history_dts_3_list[index] = aux.trade_history_dts_3;
                    trade_history_status_id_1_list[index] = aux.trade_history_status_id_1;
                    trade_history_status_id_2_list[index] = aux.trade_history_status_id_2;
                    trade_history_status_id_3_list[index] = aux.trade_history_status_id_3;
                    index := index + 1;
                END LOOP;

        i = i + 1;
        num_found = num_found + 1;
        END LOOP;
    RETURN NEXT;
END;
$$ LANGUAGE 'plpgsql';


/*
 * Frame 4
 * identifies the first trade for the specified customer account on or after
 * the specified time.
 */

CREATE OR REPLACE FUNCTION TradeLookupFrame4(
    IN acct_id BIGINT,
    IN trade_dts timestamp,
    OUT num_found INTEGER,
    OUT num_trades_found INTEGER,
    OUT holding_history_id_list BIGINT[],
    OUT holding_history_trade_id_list BIGINT[],
    OUT quantity_after_list INTEGER[],
    OUT quantity_before_list INTEGER[]) RETURNS SETOF record AS
$$
DECLARE
    -- Local Frame variables
    rs       RECORD;
    -- output parameters
    trade_id BIGINT;
    i INTEGER;
BEGIN
    SELECT T_ID
    INTO trade_id
    FROM TRADE
    WHERE T_CA_ID = acct_id
      AND T_DTS >= trade_dts
    ORDER BY T_DTS asc
    LIMIT 1;

    -- The trade_id is used in the subquery to find the original trade_id
    -- (HH_H_T_ID), which then is used to list all the entries.
    -- Should return 0 to 20 rows.
    num_found = 0;
    num_trades_found = 1;
    i = 1;
    FOR rs IN SELECT HH_H_T_ID,
                     HH_T_ID,
                     HH_BEFORE_QTY,
                     HH_AFTER_QTY,
                     trade_id
              FROM HOLDING_HISTORY
              WHERE HH_H_T_ID in
                    (SELECT HH_H_T_ID
                     FROM HOLDING_HISTORY
                     WHERE HH_T_ID = trade_id)
              LIMIT 20
        LOOP
            holding_history_id_list[i] = rs.HH_T_ID;
            holding_history_trade_id_list[i] = rs.HH_H_T_ID;
            quantity_after_list[i] = rs.HH_AFTER_QTY;
            quantity_before_list[i] = rs.HH_BEFORE_QTY;
            num_found = num_found + 1;
            i = i + 1;
        END LOOP;
    RETURN NEXT;
END;
$$ LANGUAGE 'plpgsql';
