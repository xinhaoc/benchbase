package com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition;

public class TCustomerPositionFrame1Input {
    private long cust_id;
    private String tax_id;

    public TCustomerPositionFrame1Input() {
        this.tax_id = new String();
    }

    public long getCust_id() {
        return cust_id;
    }

    public void setCust_id(long cust_id) {
        this.cust_id = cust_id;
    }

    public String getTax_id() {
        return tax_id;
    }

    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }
}
