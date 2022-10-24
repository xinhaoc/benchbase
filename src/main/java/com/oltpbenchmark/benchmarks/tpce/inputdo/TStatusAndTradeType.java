package com.oltpbenchmark.benchmarks.tpce.inputdo;

public class TStatusAndTradeType {
	public String    status_submitted;
	public String    type_limit_buy;
	public String    type_limit_sell;
	public String    type_stop_loss;

    public TStatusAndTradeType(){
    	status_submitted = new String();
    	type_limit_buy = new String();
    	type_limit_sell = new String();
    	type_stop_loss = new String();
    }

    public String getStatus_submitted() {
        return status_submitted;
    }

    public void setStatus_submitted(String status_submitted) {
        this.status_submitted = status_submitted;
    }

    public String getType_limit_buy() {
        return type_limit_buy;
    }

    public void setType_limit_buy(String type_limit_buy) {
        this.type_limit_buy = type_limit_buy;
    }

    public String getType_limit_sell() {
        return type_limit_sell;
    }

    public void setType_limit_sell(String type_limit_sell) {
        this.type_limit_sell = type_limit_sell;
    }

    public String getType_stop_loss() {
        return type_stop_loss;
    }

    public void setType_stop_loss(String type_stop_loss) {
        this.type_stop_loss = type_stop_loss;
    }
}
