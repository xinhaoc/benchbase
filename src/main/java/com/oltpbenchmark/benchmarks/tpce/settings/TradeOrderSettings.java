package com.oltpbenchmark.benchmarks.tpce.settings;

import com.oltpbenchmark.benchmarks.tpce.exceptions.CheckException;

public class TradeOrderSettings extends ParametersWithDefaults {

    public int dft_market;
    public int dft_limit;
    public int dft_stop_loss;
    public int dft_security_by_name;
    public int dft_security_by_symbol;
    public int dft_buy_orders;
    public int dft_sell_orders;
    public int dft_lifo;
    public int dft_exec_is_owner;
    public int dft_rollback;
    public int dft_type_is_margin;

    public int cur_market;
    public int cur_limit;
    public int cur_stop_loss;
    public int cur_security_by_name;
    public int cur_security_by_symbol;
    public int cur_buy_orders;
    public int cur_sell_orders;
    public int cur_lifo;
    public int cur_exec_is_owner;
    public int cur_rollback;
    public int cur_type_is_margin;

    public boolean state_market;
    public boolean state_limit;
    public boolean state_stop_loss;
    public boolean state_security_by_name;
    public boolean state_security_by_symbol;
    public boolean state_buy_orders;
    public boolean state_sell_orders;
    public boolean state_lifo;
    public boolean state_exec_is_owner;
    public boolean state_rollback;
    public boolean state_type_is_margin;

    public TradeOrderSettings() {
        initialize();
    }

    public void initializeDefaults() {
        dft_market = 60;
        dft_limit = 40;
        dft_stop_loss = 50;
        dft_security_by_name = 40;
        dft_security_by_symbol = 60;
        dft_buy_orders = 50;
        dft_sell_orders = 50;
        dft_lifo = 35;
        dft_exec_is_owner = 90;
        dft_rollback = 1;
        dft_type_is_margin = 8;
    }

    public void setToDefaults() {
        cur_market = dft_market;
        cur_limit = dft_limit;
        cur_stop_loss = dft_stop_loss;
        cur_security_by_name = dft_security_by_name;
        cur_security_by_symbol = dft_security_by_symbol;
        cur_buy_orders = dft_buy_orders;
        cur_sell_orders = dft_sell_orders;
        cur_lifo = dft_lifo;
        cur_exec_is_owner = dft_exec_is_owner;
        cur_rollback = dft_rollback;
        cur_type_is_margin = dft_type_is_margin;
        checkDefaults();
    }

    public void checkDefaults() {
        state_market = (cur_market == dft_market);
        state_limit = (cur_limit == dft_limit);
        state_stop_loss = (cur_stop_loss == dft_stop_loss);
        state_security_by_name = (cur_security_by_name == dft_security_by_name);
        state_security_by_symbol = (cur_security_by_symbol == dft_security_by_symbol);
        state_buy_orders = (cur_buy_orders == dft_buy_orders);
        state_sell_orders = (cur_sell_orders == dft_sell_orders);
        state_lifo = (cur_lifo == dft_lifo);
        state_exec_is_owner = (cur_exec_is_owner == dft_exec_is_owner);
        state_rollback = (cur_rollback == dft_rollback);
        state_type_is_margin = (cur_type_is_margin == dft_type_is_margin);
    }

    public boolean checkValid() {
        try {
            driverParamCheckBetween("market", cur_market, 0, 100);
            driverParamCheckBetween("limit", cur_limit, 0, 100);
            driverParamCheckEqual("market or limit total", cur_market + cur_limit, 100);
            driverParamCheckBetween("stop_loss", cur_stop_loss, 0, 100);
            driverParamCheckBetween("security_by_name", cur_security_by_name, 0, 100);
            driverParamCheckBetween("security_by_symbol", cur_security_by_symbol, 0, 100);
            driverParamCheckEqual("security_by_* total", cur_security_by_name + cur_security_by_symbol, 100);
            driverParamCheckBetween("buy_orders", cur_buy_orders, 0, 100);
            driverParamCheckBetween("sell_orders", cur_sell_orders, 0, 100);
            driverParamCheckEqual("*_orders total", cur_buy_orders + cur_sell_orders, 100);
            driverParamCheckBetween("lifo", cur_lifo, 0, 100);
            driverParamCheckBetween("exec_is_owner", cur_exec_is_owner, 0, 100);
            driverParamCheckBetween("rollback", cur_rollback, 0, 100);
            driverParamCheckBetween("type_is_margin", cur_type_is_margin, 0, 100);
            return true;
        } catch (CheckException e) {
            return false;
        }
    }

    public boolean checkCompliant() {
        checkValid();
        try {
            driverParamCheckBetween("exec_is_owner", cur_exec_is_owner, 60, 100);
            driverParamCheckDefault(dft_market, cur_market, "market");
            driverParamCheckDefault(dft_limit, cur_limit, "limit");
            driverParamCheckDefault(dft_stop_loss, cur_stop_loss, "stop_loss");
            driverParamCheckDefault(dft_security_by_name, cur_security_by_name, "security_by_name");
            driverParamCheckDefault(dft_security_by_symbol, cur_security_by_symbol, "security_by_symbol");
            driverParamCheckDefault(dft_buy_orders, cur_buy_orders, "buy_orders");
            driverParamCheckDefault(dft_sell_orders, cur_sell_orders, "sell_orders");
            driverParamCheckDefault(dft_lifo, cur_lifo, "lifo");
            driverParamCheckDefault(dft_exec_is_owner, cur_exec_is_owner, "exec_is_owner");
            driverParamCheckDefault(dft_rollback, cur_rollback, "rollback");
            driverParamCheckDefault(dft_type_is_margin, cur_type_is_margin, "type_is_margin");
            return true;
        } catch (CheckException e) {
            return false;
        }
    }
}
