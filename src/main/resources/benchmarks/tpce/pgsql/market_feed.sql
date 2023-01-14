CREATE OR REPLACE FUNCTION MarketFeedFrame1(
    IN MaxSize INTEGER,
    IN price_quote numeric(8, 2)[],
    IN status_submitted char(4),
    IN symbol char(15)[],
    IN trade_qty integer[],
    IN type_limit_buy char(3),
    IN type_limit_sell char(3),
    IN type_stop_loss char(3),
    OUT num_updated INTEGER,
    OUT send_len INTEGER,
    OUT symbol_list VARCHAR(15)[],
    OUT trade_id_list BIGINT[],
    OUT price_quote_list NUMERIC(8, 2)[],
    OUT trade_qty_list INTEGER[],
    OUT trade_type_list VARCHAR(3)[]) RETURNS SETOF record AS
$$
DECLARE
    -- output parameters
    TradeRequestBuffer record;

    -- variables
    i                  integer;
    now_dts            timestamp;
    request_list       refcursor;
    trade_id           BIGINT;
    price              numeric(8, 2);
    trade_type         char(3);
    trade_quant        integer;
BEGIN
    num_updated = 0;
    now_dts = now();

    FOR i IN 1..MaxSize
        LOOP
            -- start transaction
            UPDATE LAST_TRADE
            SET LT_PRICE = price_quote[i],
                LT_VOL   = LT_VOL + trade_qty[i],
                LT_DTS   = now_dts
            WHERE LT_S_SYMB = symbol[i];

            OPEN request_list FOR
                SELECT TR_T_ID,
                       TR_BID_PRICE,
                       TR_TT_ID,
                       TR_QTY
                FROM TRADE_REQUEST
                WHERE TR_S_SYMB = symbol[i]
                  and ((TR_TT_ID = type_stop_loss and TR_BID_PRICE >= price_quote[i]) or
                       (TR_TT_ID = type_limit_sell and TR_BID_PRICE <= price_quote[i]) or
                       (TR_TT_ID = type_limit_buy and TR_BID_PRICE >= price_quote[i]));

            FETCH request_list
                INTO trade_id,
                price,
                trade_type,
                trade_quant;

            WHILE FOUND
                LOOP
                    num_updated = num_updated + 1;
                    UPDATE TRADE
                    SET T_DTS   = now_dts,
                        T_ST_ID = status_submitted
                    WHERE T_ID = trade_id;

                    DELETE
                    FROM TRADE_REQUEST
                    WHERE TR_T_ID = trade_id;

                    INSERT INTO TRADE_HISTORY
                    VALUES (trade_id, now_dts, status_submitted);

                    FOR TradeRequestBuffer IN
                        SELECT symbol[i] as symbol,
                               trade_id,
                               price,
                               trade_quant,
                               trade_type
                        LOOP
--                             RETURN NEXT TradeRequestBuffer;
                        END LOOP;

                    FETCH request_list
                        INTO trade_id,
                        price,
                        trade_type,
                        trade_quant;
                symbol_list[num_updated] = TradeRequestBuffer.symbol;
                trade_id_list[num_updated] = TradeRequestBuffer.trade_id;
                price_quote_list[num_updated] = TradeRequestBuffer.price;
                trade_qty_list[num_updated] = TradeRequestBuffer.trade_quant;
                trade_type_list[num_updated] = TradeRequestBuffer.trade_type;
                END LOOP;

            CLOSE request_list;
-- commit transaction
        END LOOP;
    RETURN NEXT;
END;
$$ LANGUAGE 'plpgsql';
