package com.oltpbenchmark.benchmarks.tpce.outputdo.tradestatus;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.GregorianCalendar;

import static com.oltpbenchmark.benchmarks.tpce.pojo.TxnHarnessStructs.max_trade_status_len;

public class TTradeStatusFrame1Output {

    double[] charge;
    long[] trade_id;
    int[] trade_qty;
    int num_found;
    TimestampType[] trade_dts;
    String[] ex_name;
    String[] exec_name;
    String[] s_name;
    String[] status_name;
    String[] symbol;
    String[] type_name;
    String broker_name;
    String cust_f_name;
    String cust_l_name;

    public TTradeStatusFrame1Output() {
        charge = new double[max_trade_status_len];
        trade_id = new long[max_trade_status_len];
        trade_qty = new int[max_trade_status_len];
        trade_dts = new TimestampType[max_trade_status_len];
        for (int i = 0; i < max_trade_status_len; i++) {
            trade_dts[i] =
                new TimestampType(new GregorianCalendar(0, 0, 1, 0, 0, 0).getTime());
        }
        ex_name = new String[max_trade_status_len];
        for (int i = 0; i < max_trade_status_len; i++) ex_name[i] = new String();
        exec_name = new String[max_trade_status_len];
        for (int i = 0; i < max_trade_status_len; i++) exec_name[i] = new String();
        s_name = new String[max_trade_status_len];
        for (int i = 0; i < max_trade_status_len; i++) s_name[i] = new String();
        status_name = new String[max_trade_status_len];
        for (int i = 0; i < max_trade_status_len; i++) status_name[i] = new String();
        symbol = new String[max_trade_status_len];
        for (int i = 0; i < max_trade_status_len; i++) symbol[i] = new String();
        type_name = new String[max_trade_status_len];
        for (int i = 0; i < max_trade_status_len; i++) type_name[i] = new String();

        broker_name = new String();
        cust_f_name = new String();
        cust_l_name = new String();

    }

    public double[] getCharge() {
        return charge;
    }

    public void setCharge(double[] charge) {
        this.charge = charge;
    }

    public long[] getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(long[] trade_id) {
        this.trade_id = trade_id;
    }

    public int[] getTrade_qty() {
        return trade_qty;
    }

    public void setTrade_qty(int[] trade_qty) {
        this.trade_qty = trade_qty;
    }

    public int getNum_found() {
        return num_found;
    }

    public void setNum_found(int num_found) {
        this.num_found = num_found;
    }

    public TimestampType[] getTrade_dts() {
        return trade_dts;
    }

    public void setTrade_dts(TimestampType[] trade_dts) {
        this.trade_dts = trade_dts;
    }

    public String[] getEx_name() {
        return ex_name;
    }

    public void setEx_name(String[] ex_name) {
        this.ex_name = ex_name;
    }

    public String[] getExec_name() {
        return exec_name;
    }

    public void setExec_name(String[] exec_name) {
        this.exec_name = exec_name;
    }

    public String[] getS_name() {
        return s_name;
    }

    public void setS_name(String[] s_name) {
        this.s_name = s_name;
    }

    public String[] getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String[] status_name) {
        this.status_name = status_name;
    }

    public String[] getSymbol() {
        return symbol;
    }

    public void setSymbol(String[] symbol) {
        this.symbol = symbol;
    }

    public String[] getType_name() {
        return type_name;
    }

    public void setType_name(String[] type_name) {
        this.type_name = type_name;
    }

    public String getBroker_name() {
        return broker_name;
    }

    public void setBroker_name(String broker_name) {
        this.broker_name = broker_name;
    }

    public String getCust_f_name() {
        return cust_f_name;
    }

    public void setCust_f_name(String cust_f_name) {
        this.cust_f_name = cust_f_name;
    }

    public String getCust_l_name() {
        return cust_l_name;
    }

    public void setCust_l_name(String cust_l_name) {
        this.cust_l_name = cust_l_name;
    }
}
