package com.oltpbenchmark.benchmarks.tpce.outputdo.securitydetail;

import com.oltpbenchmark.benchmarks.tpce.outputdo.TDailyHistory;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TFinInfo;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TNews;
import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.GregorianCalendar;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.cCEO_NAME_len;
import static com.oltpbenchmark.benchmarks.tpce.pojo.TableConsts.*;
import static com.oltpbenchmark.benchmarks.tpce.pojo.TxnHarnessStructs.*;

//TODO need char[] length check for String length?
public class TSecurityDetailFrame1Output {
    double divid;
    double last_open;
    double last_price;
    double pe_ratio;
    public TSecurityDetailFrame1Output() {
        ex_date = new TimestampType(new GregorianCalendar(0, 0, 0, 0, 0, 0).getTime());
        open_date = new TimestampType(new GregorianCalendar(0, 0, 0, 0, 0, 0).getTime());
        s52_wk_high_date = new TimestampType(new GregorianCalendar(0, 0, 0, 0, 0, 0).getTime());
        s52_wk_low_date = new TimestampType(new GregorianCalendar(0, 0, 0, 0, 0, 0).getTime());
        start_date = new TimestampType(new GregorianCalendar(0, 0, 0, 0, 0, 0).getTime());

        day = new TDailyHistory[max_day_len];
        fin = new TFinInfo[max_fin_len];
        news = new TNews[max_news_len];
        cp_co_name = new String[max_comp_len];
        for (int i = 0; i < max_comp_len; i++) cp_co_name[i] = new String();

        cp_in_name = new String[max_comp_len];
        for (int i = 0; i < max_comp_len; i++) cp_in_name[i] = new String();

        ceo_name = new String();
        co_ad_div = new String();
        co_ad_cty = new String();
        co_ad_line1 = new String();
        co_ad_line2 = new String();
        co_ad_town = new String();
        co_ad_zip =new String();
        co_desc = new String();
        co_name = new String();
        co_st_id = new String();
        ex_ad_cty = new String();
        ex_ad_div = new String();
        ex_ad_line1 = new String();
        ex_ad_line2 = new String();
        ex_ad_zip = new String();
        ex_desc = new String();
        ex_name = new String();
        ex_ad_town = new String();
        s_name = new String();
        sp_rate = new String();


    }



    double s52_wk_high;
    double s52_wk_low;
    double yield;
    long last_vol;
    long num_out;
    int day_len;
    int ex_close;
    int ex_num_symb;
    int ex_open;
    int fin_len;
    int news_len;
    TimestampType ex_date;
    TimestampType open_date;
    TimestampType s52_wk_high_date;
    TimestampType s52_wk_low_date;
    TimestampType start_date;
    TDailyHistory[] day;
    TFinInfo[] fin;
    TNews[] news;
    String[] cp_co_name;
    String[] cp_in_name;
    String ceo_name;
    String co_ad_cty;
    String co_ad_div;
    String co_ad_line1;
    String co_ad_line2;
    String co_ad_town;

    String co_ad_zip;
    String co_desc;
    String co_name;
    String co_st_id;
    String ex_ad_cty;
    String ex_ad_div;
    String ex_ad_line1;
    String ex_ad_line2;
    String ex_ad_town;
    String ex_ad_zip;
    String ex_desc;
    String ex_name;
    String s_name;
    String sp_rate;


    public double getDivid() {
        return divid;
    }

    public void setDivid(double divid) {
        this.divid = divid;
    }

    public double getLast_open() {
        return last_open;
    }

    public void setLast_open(double last_open) {
        this.last_open = last_open;
    }

    public double getLast_price() {
        return last_price;
    }

    public void setLast_price(double last_price) {
        this.last_price = last_price;
    }

    public double getPe_ratio() {
        return pe_ratio;
    }

    public void setPe_ratio(double pe_ratio) {
        this.pe_ratio = pe_ratio;
    }

    public double getS52_wk_high() {
        return s52_wk_high;
    }

    public void setS52_wk_high(double s52_wk_high) {
        this.s52_wk_high = s52_wk_high;
    }

    public double getS52_wk_low() {
        return s52_wk_low;
    }

    public void setS52_wk_low(double s52_wk_low) {
        this.s52_wk_low = s52_wk_low;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    public long getLast_vol() {
        return last_vol;
    }

    public void setLast_vol(long last_vol) {
        this.last_vol = last_vol;
    }

    public long getNum_out() {
        return num_out;
    }

    public void setNum_out(long num_out) {
        this.num_out = num_out;
    }

    public int getDay_len() {
        return day_len;
    }

    public void setDay_len(int day_len) {
        this.day_len = day_len;
    }

    public int getEx_close() {
        return ex_close;
    }

    public void setEx_close(int ex_close) {
        this.ex_close = ex_close;
    }

    public int getEx_num_symb() {
        return ex_num_symb;
    }

    public void setEx_num_symb(int ex_num_symb) {
        this.ex_num_symb = ex_num_symb;
    }

    public int getEx_open() {
        return ex_open;
    }

    public void setEx_open(int ex_open) {
        this.ex_open = ex_open;
    }

    public int getFin_len() {
        return fin_len;
    }

    public void setFin_len(int fin_len) {
        this.fin_len = fin_len;
    }

    public int getNews_len() {
        return news_len;
    }

    public void setNews_len(int news_len) {
        this.news_len = news_len;
    }

    public TimestampType getEx_date() {
        return ex_date;
    }

    public void setEx_date(TimestampType ex_date) {
        this.ex_date = ex_date;
    }

    public TimestampType getOpen_date() {
        return open_date;
    }

    public void setOpen_date(TimestampType open_date) {
        this.open_date = open_date;
    }

    public TimestampType getS52_wk_high_date() {
        return s52_wk_high_date;
    }

    public void setS52_wk_high_date(TimestampType s52_wk_high_date) {
        this.s52_wk_high_date = s52_wk_high_date;
    }

    public TimestampType getS52_wk_low_date() {
        return s52_wk_low_date;
    }

    public void setS52_wk_low_date(TimestampType s52_wk_low_date) {
        this.s52_wk_low_date = s52_wk_low_date;
    }

    public TimestampType getStart_date() {
        return start_date;
    }

    public void setStart_date(TimestampType start_date) {
        this.start_date = start_date;
    }

    public TDailyHistory[] getDay() {
        return day;
    }

    public void setDay(TDailyHistory[] day) {
        this.day = day;
    }

    public TFinInfo[] getFin() {
        return fin;
    }

    public void setFin(TFinInfo[] fin) {
        this.fin = fin;
    }

    public TNews[] getNews() {
        return news;
    }

    public void setNews(TNews[] news) {
        this.news = news;
    }

    public String[] getCp_co_name() {
        return cp_co_name;
    }

    public void setCp_co_name(String[] cp_co_name) {
        this.cp_co_name = cp_co_name;
    }

    public String[] getCp_in_name() {
        return cp_in_name;
    }

    public void setCp_in_name(String[] cp_in_name) {
        this.cp_in_name = cp_in_name;
    }

    public String getCeo_name() {
        return ceo_name;
    }

    public void setCeo_name(String ceo_name) {
        this.ceo_name = ceo_name;
    }

    public String getCo_ad_cty() {
        return co_ad_cty;
    }

    public void setCo_ad_cty(String co_ad_cty) {
        this.co_ad_cty = co_ad_cty;
    }

    public String getCo_ad_div() {
        return co_ad_div;
    }

    public void setCo_ad_div(String co_ad_div) {
        this.co_ad_div = co_ad_div;
    }

    public String getCo_ad_line1() {
        return co_ad_line1;
    }

    public void setCo_ad_line1(String co_ad_line1) {
        this.co_ad_line1 = co_ad_line1;
    }

    public String getCo_ad_line2() {
        return co_ad_line2;
    }

    public void setCo_ad_line2(String co_ad_line2) {
        this.co_ad_line2 = co_ad_line2;
    }

    public String getCo_ad_town() {
        return co_ad_town;
    }

    public void setCo_ad_town(String co_ad_town) {
        this.co_ad_town = co_ad_town;
    }

    public String getCo_ad_zip() {
        return co_ad_zip;
    }

    public void setCo_ad_zip(String co_ad_zip) {
        this.co_ad_zip = co_ad_zip;
    }

    public String getCo_desc() {
        return co_desc;
    }

    public void setCo_desc(String co_desc) {
        this.co_desc = co_desc;
    }

    public String getCo_name() {
        return co_name;
    }

    public void setCo_name(String co_name) {
        this.co_name = co_name;
    }

    public String getCo_st_id() {
        return co_st_id;
    }

    public void setCo_st_id(String co_st_id) {
        this.co_st_id = co_st_id;
    }

    public String getEx_ad_cty() {
        return ex_ad_cty;
    }

    public void setEx_ad_cty(String ex_ad_cty) {
        this.ex_ad_cty = ex_ad_cty;
    }

    public String getEx_ad_div() {
        return ex_ad_div;
    }

    public void setEx_ad_div(String ex_ad_div) {
        this.ex_ad_div = ex_ad_div;
    }

    public String getEx_ad_line1() {
        return ex_ad_line1;
    }

    public void setEx_ad_line1(String ex_ad_line1) {
        this.ex_ad_line1 = ex_ad_line1;
    }

    public String getEx_ad_line2() {
        return ex_ad_line2;
    }

    public void setEx_ad_line2(String ex_ad_line2) {
        this.ex_ad_line2 = ex_ad_line2;
    }

    public String getEx_ad_town() {
        return ex_ad_town;
    }

    public void setEx_ad_town(String ex_ad_town) {
        this.ex_ad_town = ex_ad_town;
    }

    public String getEx_ad_zip() {
        return ex_ad_zip;
    }

    public void setEx_ad_zip(String ex_ad_zip) {
        this.ex_ad_zip = ex_ad_zip;
    }

    public String getEx_desc() {
        return ex_desc;
    }

    public void setEx_desc(String ex_desc) {
        this.ex_desc = ex_desc;
    }

    public String getEx_name() {
        return ex_name;
    }

    public void setEx_name(String ex_name) {
        this.ex_name = ex_name;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getSp_rate() {
        return sp_rate;
    }

    public void setSp_rate(String sp_rate) {
        this.sp_rate = sp_rate;
    }
}
