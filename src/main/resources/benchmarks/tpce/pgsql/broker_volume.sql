CREATE OR REPLACE FUNCTION BrokerVolumeFrame1 (
		IN in_broker_list VARCHAR[],
		IN in_sector_name VARCHAR,
		OUT broker_name VARCHAR(100)[],
		OUT list_len INTEGER,
		OUT status INTEGER,
		OUT volume NUMERIC(14, 2)[])
RETURNS SETOF record AS $$
DECLARE
r RECORD;
BEGIN
	list_len := 0;
FOR r IN
SELECT b_name, SUM(tr_qty * tr_bid_price) AS vol
FROM trade_request, sector, industry, company, broker, security
WHERE tr_b_id = b_id
  AND tr_s_symb = s_symb
  AND s_co_id = co_id
  AND co_in_id = in_id
  AND sc_id = in_sc_id
  AND b_name = ANY (in_broker_list)
  AND sc_name = in_sector_name
GROUP BY b_name
ORDER BY 2 DESC
    LOOP
		list_len := list_len + 1;
broker_name[list_len] = r.b_name;
		volume[list_len] = r.vol;
END LOOP;

	status := 0;

	RETURN NEXT;
END;
$$ LANGUAGE 'plpgsql';
