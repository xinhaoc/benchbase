CREATE OR REPLACE FUNCTION TradeUpdateFrame1(
    IN max_trades integer,
    IN max_updates integer,
    IN trade_id bigint[],
    OUT bid_price_list NUMERIC(8, 2)[],
    OUT cash_transaction_amount_list NUMERIC(10, 2)[],
    OUT cash_transaction_dts_list TIMESTAMP[],
    OUT cash_transaction_name_list VARCHAR(100)[],
    OUT exec_name_list VARCHAR(64)[],
    OUT is_cash_list SMALLINT[],
    OUT is_market_list SMALLINT[],
    OUT num_found INTEGER,
    OUT num_updated INTEGER,
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
-- 	num_updated			integer;
-- 	num_found			integer;
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
    index                    integer;

    -- variables
    exch_name                char(64);
    i                        integer;
    j                        integer;
    irow_count               integer;
    rs                       RECORD;
BEGIN
    num_found = max_trades;
    num_updated = 0;

    i = 1;
    WHILE i <= max_trades
        LOOP

            -- Get trade information

            IF num_updated < max_updates THEN
                -- Modify the TRADE row for this trade

                SELECT T_EXEC_NAME
                INTO exch_name
                FROM TRADE
                WHERE T_ID = trade_id[i];

--                 IF exch_name like '% X %' THEN
--                     SELECT overlay(exch_name placing ' ' from position('X' in exch_name) for 1)
--                     INTO exch_name;
--                 ELSIF exch_name like '% %' THEN
--                     SELECT overlay(exch_name placing ' X ' from position(' ' in exch_name) for 3)
--                     INTO exch_name;
--                 END IF;

                UPDATE TRADE
                SET T_EXEC_NAME = exch_name
                WHERE T_ID = trade_id[i];

                GET DIAGNOSTICS irow_count = ROW_COUNT;
                num_updated = num_updated + irow_count;
            END IF;

            -- will only return one row for each trade

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
            -- Will only return one row for each trade

            SELECT SE_AMT,
                   SE_CASH_DUE_DATE,
                   SE_CASH_TYPE
            INTO settlement_amount,
                settlement_cash_due_date,
                settlement_cash_type
            FROM SETTLEMENT
            WHERE SE_T_ID = trade_id[i];

            -- get cash information if this is a cash transaction
            -- Will only return one row for each trade that was a cash transaction

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
            -- Will return 2 to 3 rows per trade

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
            index := 0;
            FOR rs IN
                select num_found,
                       num_updated,
                       bid_price,
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
                    bid_price_list[i] = rs.bid_price;
                    cash_transaction_amount_list[i] = rs.cash_transaction_amount;
                    cash_transaction_dts_list[i] = rs.cash_transaction_dts;
                    cash_transaction_name_list[i] = rs.cash_transaction_name;
                    exec_name_list[i] = rs.exec_name;
                    is_cash_list[i] = rs.is_cash;
                    is_market_list[i] = rs.is_market;
                    settlement_amount_list[i] = rs.settlement_amount;
                    settlement_cash_due_date_list[i] = rs.settlement_cash_due_date;
                    settlement_cash_type_list[i] = rs.settlement_cash_type;
                    trade_history_dts_1_list[i] = rs.trade_history_dts_1;
                    trade_history_dts_2_list[i] = rs.trade_history_dts_2;
                    trade_history_dts_3_list[i] = rs.trade_history_dts_3;
                    trade_history_status_id_1_list[i] = rs.trade_history_status_id_1;
                    trade_history_status_id_2_list[i] = rs.trade_history_status_id_2;
                    trade_history_status_id_3_list[i] = rs.trade_history_status_id_3;
                    trade_price_list[i] = rs.trade_price;
                END LOOP;
            i = i + 1;
        END LOOP;
    RETURN NEXT;
END;
$$ LANGUAGE 'plpgsql';


/*
 * Frame 2
 *
 *
 */

CREATE OR REPLACE FUNCTION TradeUpdateFrame2(
    IN acct_id BIGINT,
    IN max_trades integer,
    IN max_updates integer,
    IN start_trade_dts timestamp,
    IN end_trade_dts timestamp,
    OUT bid_price_list NUMERIC(8, 2)[],
    OUT cash_transaction_amount_list NUMERIC(10, 2)[],
    OUT cash_transaction_dts_list TIMESTAMP[],
    OUT cash_transaction_name_list VARCHAR(100)[],
    OUT exec_name_list VARCHAR(64)[],
    OUT is_cash_list SMALLINT[],
    OUT t_id_list BIGINT[],
    OUT num_updated INTEGER,
    OUT settlement_amount_list NUMERIC(10, 2)[],
    OUT settlement_cash_due_date_list TIMESTAMP[],
    OUT settlement_cash_type_list VARCHAR(40)[],
    OUT trade_history_dts_1_list TIMESTAMP[],
    OUT trade_history_dts_2_list TIMESTAMP[],
    OUT trade_history_dts_3_list TIMESTAMP[],
    OUT trade_history_status_id_1_list VARCHAR(4)[],
    OUT trade_history_status_id_2_list VARCHAR(4)[],
    OUT trade_history_status_id_3_list VARCHAR(4)[],
    OUT trade_price_list NUMERIC(8, 2)[])
    RETURNS SETOF record AS
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
--     num_updated              integer;
    i                        integer;
    j                        integer;
    num_found                integer;
    rs                       RECORD;
    aux                      RECORD;
    cash_type                char(40);
    irow_count               integer;
BEGIN
    -- Get trade information
    -- Should return between 0 and max_trades rows

    i = 0;
    num_updated = 0;
    FOR rs IN SELECT T_BID_PRICE,
                     T_EXEC_NAME,
                     T_IS_CASH,
                     T_ID,
                     T_TRADE_PRICE
              FROM TRADE
              WHERE T_CA_ID = acct_id
                AND T_DTS >= start_trade_dts AND T_DTS <= end_trade_dts
              ORDER BY T_DTS asc
              LIMIT max_trades
        LOOP

            IF num_updated < max_updates THEN

                -- Select the SETTLEMENT row for this trade

                SELECT SE_CASH_TYPE
                INTO cash_type
                FROM SETTLEMENT
                WHERE SE_T_ID = rs.T_ID;

                IF rs.T_IS_CASH THEN
                    IF cash_type = 'Cash Account' THEN
                        cash_type = 'Cash';
                    ELSE
                        cash_type = 'Cash Account';
                    END IF;
                ELSE
                    IF cash_type = 'Margin Account' THEN
                        cash_type = 'Margin';
                    ELSE
                        cash_type = 'Margin Account';
                    END IF;
                END IF;

                UPDATE SETTLEMENT
                SET SE_CASH_TYPE = cash_type
                WHERE SE_T_ID = rs.T_ID;

                GET DIAGNOSTICS irow_count = ROW_COUNT;
                num_updated = num_updated + irow_count;
            END IF;

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
                SELECT num_updated,
                       rs.T_BID_PRICE::FLOAT as bid_price,
                       rs.T_EXEC_NAME as exec_name,
                       rs.T_IS_CASH as is_cash,
                       rs.T_TRADE_PRICE::FLOAT as trade_price,
                       rs.T_ID::BIGINT as t_id,
                       settlement_amount,
                       settlement_cash_due_date,
                       settlement_cash_type,
                       cash_transaction_amount,
                       cash_transaction_dts,
                       cash_transaction_name,
                       trade_history_dts[1] as trade_history_dts_1,
                       trade_history_status_id[1] as trade_history_status_id_1,
                       trade_history_dts[2] as trade_history_dts_2,
                       trade_history_status_id[2] as trade_history_status_id_2,
                       trade_history_dts[3] as trade_history_dts_3,
                       trade_history_status_id[3] as trade_history_status_id_3
                LOOP
                    bid_price_list[i] = aux.bid_price;
                    cash_transaction_amount_list[i] = aux.cash_transaction_amount;
                    cash_transaction_dts_list[i] = aux.cash_transaction_dts;
                    cash_transaction_name_list[i] = aux.cash_transaction_name;
                    exec_name_list[i] = aux.exec_name;
                    is_cash_list[i] = aux.is_cash;
                    t_id_list[i] = aux.t_id;
                    settlement_amount_list[i] = aux.settlement_amount;
                    settlement_cash_due_date_list[i] = aux.settlement_cash_due_date;
                    settlement_cash_type_list[i] = aux.settlement_cash_type;
                    trade_history_dts_1_list[i] = aux.trade_history_dts_1;
                    trade_history_dts_2_list[i] = aux.trade_history_dts_2;
                    trade_history_dts_3_list[i] = aux.trade_history_dts_3;
                    trade_history_status_id_1_list[i] = aux.trade_history_status_id_1;
                    trade_history_status_id_2_list[i] = aux.trade_history_status_id_2;
                    trade_history_status_id_3_list[i] = aux.trade_history_status_id_3;
                    trade_price_list[i] = aux.trade_price;
                END LOOP;

            i = i + 1;
        END LOOP;
    RETURN next;
END;
$$ LANGUAGE 'plpgsql';


/*
 * Frame 3
 * returns up to N (max_trades) trades for a given security on or after a
 * specified point in time
 * and modifies some data from the CASH_TRANSACTION table.
 */

CREATE OR REPLACE FUNCTION TradeUpdateFrame3(
    IN max_acct_id BIGINT,
    IN max_trades integer,
    IN max_updates integer,
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
    OUT num_updated INTEGER,
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
    OUT trade_price_list NUMERIC(8, 2)[],
    OUT s_name_list  VARCHAR(70)[],
    OUT tt_name_list CHAR(12)[]) RETURNS SETOF record AS
$$
DECLARE
    -- output parameters
--     num_updated              integer;
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
    cash_name                char(100);
    irow_count               integer;
    index                    integer;
BEGIN
    -- Should return between 0 and max_trades rows.

    i = 0;
    index := 1;
    num_updated = 0;
    FOR rs IN SELECT T_CA_ID,
                     T_EXEC_NAME,
                     T_IS_CASH,
                     T_ID,
                     T_TRADE_PRICE,
                     T_QTY,
                     T_DTS,
                     T_TT_ID,
                     TT_NAME,
                     S_NAME
              FROM TRADE,
                   TRADE_TYPE,
                   SECURITY
              WHERE T_S_SYMB = symbol
                AND T_DTS >= start_trade_dts AND T_DTS <= end_trade_dts
                AND TT_ID = T_TT_ID
                AND S_SYMB = T_S_SYMB
                AND T_CA_ID <= max_acct_id
              ORDER BY T_DTS asc
              LIMIT max_trades
        LOOP
        -- Get extra information for each trade in the trade list.
        -- Get settlement information
        -- Will return only one row for each trade

            SELECT SE_AMT,
                   SE_CASH_DUE_DATE,
                   SE_CASH_TYPE
            INTO settlement_amount,
                settlement_cash_due_date,
                settlement_cash_type
            FROM SETTLEMENT
            WHERE SE_T_ID = rs.T_ID;

            -- get cash information if this is a cash transaction
            -- Will return only one row for each trade that was a cash transaction

            IF rs.T_IS_CASH THEN

                IF num_updated < max_updates THEN
                    -- Modify the CASH_TRANSACTION row for this trade
                    SELECT CT_NAME
                    INTO cash_name
                    FROM CASH_TRANSACTION
                    WHERE CT_T_ID = rs.T_ID;

                    IF cash_name like '% shares of %' THEN
                        cash_name = rs.TT_NAME || ' ' || rs.T_QTY || ' Shares of ' || rs.S_NAME;
                    ELSE
                        cash_name = rs.TT_NAME || ' ' || rs.T_QTY || ' shares of ' || rs.S_NAME;
                    END IF;

                    UPDATE CASH_TRANSACTION
                    SET CT_NAME = cash_name
                    WHERE CT_T_ID = rs.T_ID;

                    GET DIAGNOSTICS irow_count = ROW_COUNT;

                    num_updated = num_updated + irow_count;

                END IF;

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
                       rs.T_TT_ID as t_tt_id,
                       rs.S_NAME as s_name,
                       rs.TT_NAME as tt_name
                LOOP
                 t_ca_id_list[index] =  aux.t_ca_id;
                 t_id_list[index] = aux.t_id;
                 tt_name_list[index] = aux.tt_name;
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
                 s_name_list[index] = aux.s_name;
                 index := index + 1;
                 END LOOP;
            i = i + 1;
        END LOOP;

    -- send num_updated
    FOR aux IN
        SELECT num_updated,
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
        END LOOP;
    RETURN NEXT;
END;
$$ LANGUAGE 'plpgsql';
