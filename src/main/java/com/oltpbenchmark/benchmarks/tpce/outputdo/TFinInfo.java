package com.oltpbenchmark.benchmarks.tpce.outputdo;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.GregorianCalendar;

public class TFinInfo {
    double assets;
    double basic_eps;
    double dilut_eps;
    double invent;
    double liab;
    double margin;
    double net_earn;
    double out_basic;
    double out_dilut;
    double rev;
    int qtr;
    int year;
    TimestampType start_date;
    int assets_ind;
    int basic_eps_ind;
    int dilut_eps_ind;
    int invent_ind;
    int liab_ind;
    int margin_ind;
    int net_earn_ind;
    int out_basic_ind;
    int out_dilut_ind;
    int qtr_ind;
    int rev_ind;
    int start_date_ind;
    int year_ind;

    public TFinInfo() {
        start_date = new TimestampType(new GregorianCalendar(0, 0, 0, 0, 0, 0).getTime());
    }

    public double getAssets() {
        return assets;
    }

    public void setAssets(double assets) {
        this.assets = assets;
    }

    public double getBasic_eps() {
        return basic_eps;
    }

    public void setBasic_eps(double basic_eps) {
        this.basic_eps = basic_eps;
    }

    public double getDilut_eps() {
        return dilut_eps;
    }

    public void setDilut_eps(double dilut_eps) {
        this.dilut_eps = dilut_eps;
    }

    public double getInvent() {
        return invent;
    }

    public void setInvent(double invent) {
        this.invent = invent;
    }

    public double getLiab() {
        return liab;
    }

    public void setLiab(double liab) {
        this.liab = liab;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public double getNet_earn() {
        return net_earn;
    }

    public void setNet_earn(double net_earn) {
        this.net_earn = net_earn;
    }

    public double getOut_basic() {
        return out_basic;
    }

    public void setOut_basic(double out_basic) {
        this.out_basic = out_basic;
    }

    public double getOut_dilut() {
        return out_dilut;
    }

    public void setOut_dilut(double out_dilut) {
        this.out_dilut = out_dilut;
    }

    public double getRev() {
        return rev;
    }

    public void setRev(double rev) {
        this.rev = rev;
    }

    public int getQtr() {
        return qtr;
    }

    public void setQtr(int qtr) {
        this.qtr = qtr;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public TimestampType getStart_date() {
        return start_date;
    }

    public void setStart_date(TimestampType start_date) {
        this.start_date = start_date;
    }

    public int getAssets_ind() {
        return assets_ind;
    }

    public void setAssets_ind(int assets_ind) {
        this.assets_ind = assets_ind;
    }

    public int getBasic_eps_ind() {
        return basic_eps_ind;
    }

    public void setBasic_eps_ind(int basic_eps_ind) {
        this.basic_eps_ind = basic_eps_ind;
    }

    public int getDilut_eps_ind() {
        return dilut_eps_ind;
    }

    public void setDilut_eps_ind(int dilut_eps_ind) {
        this.dilut_eps_ind = dilut_eps_ind;
    }

    public int getInvent_ind() {
        return invent_ind;
    }

    public void setInvent_ind(int invent_ind) {
        this.invent_ind = invent_ind;
    }

    public int getLiab_ind() {
        return liab_ind;
    }

    public void setLiab_ind(int liab_ind) {
        this.liab_ind = liab_ind;
    }

    public int getMargin_ind() {
        return margin_ind;
    }

    public void setMargin_ind(int margin_ind) {
        this.margin_ind = margin_ind;
    }

    public int getNet_earn_ind() {
        return net_earn_ind;
    }

    public void setNet_earn_ind(int net_earn_ind) {
        this.net_earn_ind = net_earn_ind;
    }

    public int getOut_basic_ind() {
        return out_basic_ind;
    }

    public void setOut_basic_ind(int out_basic_ind) {
        this.out_basic_ind = out_basic_ind;
    }

    public int getOut_dilut_ind() {
        return out_dilut_ind;
    }

    public void setOut_dilut_ind(int out_dilut_ind) {
        this.out_dilut_ind = out_dilut_ind;
    }

    public int getQtr_ind() {
        return qtr_ind;
    }

    public void setQtr_ind(int qtr_ind) {
        this.qtr_ind = qtr_ind;
    }

    public int getRev_ind() {
        return rev_ind;
    }

    public void setRev_ind(int rev_ind) {
        this.rev_ind = rev_ind;
    }

    public int getStart_date_ind() {
        return start_date_ind;
    }

    public void setStart_date_ind(int start_date_ind) {
        this.start_date_ind = start_date_ind;
    }

    public int getYear_ind() {
        return year_ind;
    }

    public void setYear_ind(int year_ind) {
        this.year_ind = year_ind;
    }
}
