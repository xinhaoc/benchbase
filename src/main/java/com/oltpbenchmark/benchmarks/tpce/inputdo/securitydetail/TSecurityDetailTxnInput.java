package com.oltpbenchmark.benchmarks.tpce.inputdo.securitydetail;

import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;

import java.util.ArrayList;

public class TSecurityDetailTxnInput {

    public TSecurityDetailTxnInput(){
    	symbol = new String();
    	start_day = new TimestampType();
    }

    public ArrayList<Object>InputParameters(){
    	ArrayList<Object> para = new ArrayList<Object>();
    	para.add(max_rows_to_return);
    	para.add(access_lob_flag);
    	para.add(start_day);
    	para.add(symbol);
    	return para;
    }

    public int getMaxRowsToReturn(){
    	return max_rows_to_return;
    }
    public boolean getAccessLobFlag(){
    	return access_lob_flag;
    }
    public TimestampType getStartDay(){
    	return start_day;
    }
    public String getSymbol(){
    	return symbol;
    }

    public void setMaxRowsToReturn(int max_rows_to_return){
    	this.max_rows_to_return = max_rows_to_return;
    }
    public void setAccessLobFlag(boolean access_lob_flag){
    	this.access_lob_flag = access_lob_flag;
    }
    public void setStartDay(TimestampType start_day){
    	this.start_day = start_day;
    }
    public void setSymbol(String symbol){
    	this.symbol = symbol;
    }

    private int                max_rows_to_return;
	private boolean access_lob_flag;
	private TimestampType      start_day;
	private String             symbol;
}
