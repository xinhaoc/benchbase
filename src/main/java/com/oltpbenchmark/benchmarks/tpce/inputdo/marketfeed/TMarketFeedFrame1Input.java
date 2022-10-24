package com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed;

import com.oltpbenchmark.benchmarks.tpce.inputdo.TStatusAndTradeType;
import com.oltpbenchmark.benchmarks.tpce.inputdo.TTickerEntry;
import com.oltpbenchmark.benchmarks.tpce.pojo.TxnHarnessStructs;

public class TMarketFeedFrame1Input {
    public TStatusAndTradeType   StatusAndTradeType;
    public char[]                zz_padding;
    public TTickerEntry[]        Entries;

    public TMarketFeedFrame1Input(){
        StatusAndTradeType = new TStatusAndTradeType();
        zz_padding = new char[4];
        Entries = new TTickerEntry[TxnHarnessStructs.max_feed_len];
    }

    public TStatusAndTradeType getStatusAndTradeType() {
        return StatusAndTradeType;
    }

    public void setStatusAndTradeType(TStatusAndTradeType statusAndTradeType) {
        StatusAndTradeType = statusAndTradeType;
    }

    public char[] getZz_padding() {
        return zz_padding;
    }

    public void setZz_padding(char[] zz_padding) {
        this.zz_padding = zz_padding;
    }

    public TTickerEntry[] getEntries() {
        return Entries;
    }

    public void setEntries(TTickerEntry[] entries) {
        Entries = entries;
    }
}
