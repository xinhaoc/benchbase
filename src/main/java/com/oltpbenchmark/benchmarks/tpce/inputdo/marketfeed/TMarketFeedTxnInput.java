package com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed;

import com.oltpbenchmark.benchmarks.tpce.inputdo.TStatusAndTradeType;
import com.oltpbenchmark.benchmarks.tpce.inputdo.TTickerEntry;
import com.oltpbenchmark.benchmarks.tpce.pojo.TxnHarnessStructs;

public class TMarketFeedTxnInput {
    int unique_symbols;
    public TStatusAndTradeType   StatusAndTradeType;
    public char[]                zz_padding;
    public char[]                zz_paddin2;
    public TTickerEntry[]        Entries;

    public TMarketFeedTxnInput(){
        StatusAndTradeType = new TStatusAndTradeType();
        zz_padding = new char[4];
        zz_paddin2 = new char[4];
        Entries = new TTickerEntry[TxnHarnessStructs.max_feed_len];
    }

    public int getUnique_symbols() {
        return unique_symbols;
    }

    public void setUnique_symbols(int unique_symbols) {
        this.unique_symbols = unique_symbols;
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

    public char[] getZz_paddin2() {
        return zz_paddin2;
    }

    public void setZz_paddin2(char[] zz_paddin2) {
        this.zz_paddin2 = zz_paddin2;
    }

    public TTickerEntry[] getEntries() {
        return Entries;
    }

    public void setEntries(TTickerEntry[] entries) {
        Entries = entries;
    }
}
