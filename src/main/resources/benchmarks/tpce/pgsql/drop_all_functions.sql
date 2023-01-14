/*
 * This file is released under the terms of the Artistic License.  Please see
 * the file LICENSE, included in this package, for details.
 */

DROP FUNCTION BrokerVolumeFrame1 (
		IN broker_list	varchar[],
		IN sector_name	varchar);
DROP FUNCTION CustomerPositionFrame1 (
		IN cust_id	BIGINT,
		IN tax_id	char(20),
		IN max_acct_len integer);
DROP FUNCTION CustomerPositionFrame2(IN acct_id BIGINT);
DROP FUNCTION DataMaintenanceFrame1 (
		IN add_flag	smallint,
		IN cust_id	BIGINT,
		IN comp_id 	BIGINT,
		IN day_of_month	smallint,
		IN symbol	char(15),
		IN table_name	char(18),
		IN tax_id 	char(4));
DROP FUNCTION MarketFeedFrame1 (
		IN MaxSize		smallint,
		IN price_quote		numeric(8,2)[],
		IN status_submitted	char(4),
		IN symbol		char(15)[],
		IN trade_qty		integer[],
		IN type_limit_buy	char(3),
		IN type_limit_sell	char(3),
		IN type_stop_loss	char(3));
DROP FUNCTION MarketWatchFrame1 (
		IN acct_id		BIGINT,
		IN cust_id		BIGINT,
		IN ending_co_id 	BIGINT,
		IN industry_name	varchar,
		IN starting_co_id 	BIGINT);
DROP FUNCTION SecurityDetailFrame1 (
		IN access_lob_flag	smallint,
		IN max_rows_to_return	integer,
		IN start_day		timestamp,
		IN symbol		char(15));
DROP FUNCTION TradeCleanupFrame1 (
		IN st_canceled_id	char(4),
		IN st_pending_id	char(4),
		IN st_submitted_id	char(4),
		IN start_trade_id	BIGINT);
DROP FUNCTION TradeLookupFrame1 (
		IN max_trades integer,
		IN trade_id bigint[]);
DROP FUNCTION TradeLookupFrame2(
		IN acct_id	BIGINT,
		IN max_trades	integer,
		IN trade_dts	timestamp);
DROP FUNCTION TradeLookupFrame3(
		IN max_acct_id	BIGINT,
		IN max_trades	integer,
		IN trade_dts	timestamp,
		IN symbol	char(15));
DROP FUNCTION TradeLookupFrame4(
		IN acct_id	BIGINT,
		IN trade_dts	timestamp);
DROP FUNCTION TradeOrderFrame1 (IN acct_id BIGINT);
DROP FUNCTION TradeOrderFrame2(
		IN acct_id 	BIGINT,
		IN exec_f_name	varchar,
		IN exec_l_name	varchar,
		IN exec_tax_id	varchar);
DROP FUNCTION TradeOrderFrame3(
		IN acct_id		BIGINT,
		IN cust_id		BIGINT,
		IN cust_tier		smallint,
		IN is_lifo		smallint,
		IN issue		char(6),
		IN st_pending_id	char(4),
		IN st_submitted_id	char(4),
		IN tax_status		smallint,
		IN trade_qty		INTEGER,
		IN trade_type_id	char(3),
		IN type_is_margin	smallint,
		IN company_name		varchar,
		IN requested_price	FLOAT,
		IN symbol		varchar);
DROP FUNCTION TradeOrderFrame4(
		IN acct_id            BIGINT,
		IN charge_amount      FLOAT,
		IN comm_amount        FLOAT,
		IN exec_name          char(64),
		IN is_cash            smallint,
		IN is_lifo            smallint,
		IN requested_price    FLOAT,
		IN status_id          char(4),
		IN symbol             varchar(15),
		IN trade_qty          INTEGER,
		IN trade_type_id      char(3),
		IN type_is_market     smallint);
DROP FUNCTION TradeResultFrame1 (IN trade_id BIGINT);
DROP FUNCTION TradeResultFrame2(
		IN acct_id	BIGINT,
		IN holdsum_qty	INTEGER,
		IN is_lifo	smallint,
		IN symbol	char(15),
		IN trade_id	BIGINT,
		IN trade_price	FLOAT,
		IN trade_qty	INTEGER,
		IN type_is_sell	smallint);
DROP FUNCTION TradeResultFrame3(
		IN buy_value	numeric(12,2),
		IN cust_id	BIGINT,
		IN sell_value	numeric(12,2),
		IN trade_id	BIGINT,
		IN tax_amnt	FLOAT);
DROP FUNCTION TradeResultFrame4(
		IN cust_id	BIGINT,
		IN symbol	char(15),
		IN trade_qty	INTEGER,
		IN type_id	char(3));
DROP FUNCTION TradeResultFrame5(
		IN broker_id		BIGINT,
		IN comm_amount		numeric(5,2),
		IN st_completed_id	char(4),
		IN trade_dts		timestamp,
		IN trade_id		BIGINT,
		IN trade_price		FLOAT);
DROP FUNCTION TradeResultFrame6(
		IN acct_id		BIGINT,
		IN due_date		timestamp,
		IN s_name		varchar,
		IN se_amount		FLOAT,
		IN trade_dts		timestamp,
		IN trade_id		BIGINT,
		IN trade_is_cash	smallint,
		IN trade_qty		INTEGER,
		IN type_name		char(12));
DROP FUNCTION TradeStatusFrame1 (IN acct_id BIGINT);
DROP FUNCTION TradeUpdateFrame1 (
		IN max_trades	integer,
		IN max_updates	integer,
		IN trade_id	bigint[]);
DROP FUNCTION TradeUpdateFrame2(
		IN acct_id	BIGINT,
		IN max_trades	integer,
		IN max_updates	integer,
		IN trade_dts	timestamp);
DROP FUNCTION TradeUpdateFrame3(
		IN max_acct_id	BIGINT,
		IN max_trades	integer,
		IN max_updates	integer,
		IN trade_dts	timestamp,
		IN symbol	char(15));
