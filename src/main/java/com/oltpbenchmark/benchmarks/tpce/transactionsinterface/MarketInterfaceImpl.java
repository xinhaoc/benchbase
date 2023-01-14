package com.oltpbenchmark.benchmarks.tpce.transactionsinterface;

import com.oltpbenchmark.benchmarks.tpce.dbinterface.DBConnectionImpl;
import com.oltpbenchmark.benchmarks.tpce.emulator.MEE;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed.TMarketFeedFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed.TMarketFeedTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketwatch.TMarketWatchTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult.*;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketfeed.TMarketFeedFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketfeed.TMarketFeedTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketwatch.TMarketWatchTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.traderesult.*;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import static com.oltpbenchmark.benchmarks.tpce.pojo.TxnHarnessStructs.max_feed_len;

public class MarketInterfaceImpl implements MarketInterface{

    private final DBConnectionImpl dbConnection;

    private final ErrorCode errorCode;
    private final static long iTIdentShift = 4300000000L;
    private final static long iDefaultLoadUnitSize = 1000L;

    public MarketInterfaceImpl() {
        this.dbConnection = new DBConnectionImpl();
        this.errorCode = new ErrorCode();
    }


    @Override
    public void CMarketFeed(Connection connection, TMarketFeedTxnInput input, TMarketFeedTxnOutput output) {
        TMarketFeedFrame1Input frame1Input = new TMarketFeedFrame1Input();
        TMarketFeedFrame1Output frame1Output = new TMarketFeedFrame1Output();
        output.setStatus(ErrorCode.SUCCESS);
        frame1Input.setStatusAndTradeType(input.getStatusAndTradeType());
        for (int i = 0; i < max_feed_len; i++) {
            frame1Input.getEntries()[i] = input.getEntries()[i];
            frame1Input.getEntries()[i].setSymbol(input.getEntries()[i].symbol);
            frame1Input.getEntries()[i].setPrice_quote(input.getEntries()[i].price_quote);
            frame1Input.getEntries()[i].setTrade_qty(input.getEntries()[i].trade_qty);
        }

//        TODO send to market
        dbConnection.execute(connection, frame1Input, frame1Output);
        if (frame1Output.getNum_updated() < input.getUnique_symbols()) {
            output.setStatus(ErrorCode.MFF1_ERROR1);
        }
        output.setSend_len(frame1Output.getSend_len());

    }

    @Override
    public void CTradeResult(Connection connection, TTradeResultTxnInput input, TTradeResultTxnOutput output) {
        TTradeResultFrame1Input frame1Input = new TTradeResultFrame1Input();
        TTradeResultFrame1Output frame1Output = new TTradeResultFrame1Output();
        TTradeResultFrame2Input frame2Input = new TTradeResultFrame2Input();
        TTradeResultFrame2Output frame2Output = new TTradeResultFrame2Output();
        TTradeResultFrame3Input frame3Input = new TTradeResultFrame3Input();
        TTradeResultFrame3Output frame3Output = new TTradeResultFrame3Output();
        TTradeResultFrame4Input frame4Input = new TTradeResultFrame4Input();
        TTradeResultFrame4Output frame4Output = new TTradeResultFrame4Output();
        TTradeResultFrame5Input frame5Input = new TTradeResultFrame5Input();
        TTradeResultFrame6Input frame6Input = new TTradeResultFrame6Input();
        TTradeResultFrame6Output frame6Output = new TTradeResultFrame6Output();

        output.setStatus(ErrorCode.SUCCESS);
        //
        // FRAME 1
        //
        // Copy Frame 1 Input
        frame1Input.setTrade_id(input.getTrade_id());
        // Execute Frame 1
        dbConnection.execute(connection, frame1Input, frame1Output);
        // Validate Frame 1 Output
        if (frame1Output.getNum_found() != 1) {
            output.setStatus(ErrorCode.TRF1_ERROR1);
        }
        //
        // FRAME 2
        //

        // Copy Frame 2 Input
        frame2Input.setAcct_id(frame1Output.getAcct_id());
        frame2Input.setHs_qty(frame1Output.getHs_qty());
        frame2Input.setIs_lifo(frame1Output.getIs_lifo());
        frame2Input.setSymbol(frame1Output.getSymbol());
        frame2Input.setTrade_id(input.getTrade_id());
        frame2Input.setTrade_price(input.getTrade_price());
        frame2Input.setTrade_qty(frame1Output.getTrade_qty());
        frame2Input.setType_is_sell(frame1Output.getType_is_sell());
        // Execute Frame 2
        dbConnection.execute(connection, frame2Input, frame2Output);
        //
        // FRAME 3
        //

        frame3Output.setTax_amount(0.0);
        if ((frame2Output.getTax_status() == 1 || frame2Output.getTax_status() == 2)
            && (frame2Output.getSell_value() > frame2Output.getBuy_value())) {
            frame3Input.setBuy_value(frame2Output.getBuy_value());
            frame3Input.setCust_id(frame2Output.getCust_id());
            frame3Input.setSell_value(frame2Output.getSell_value());
            frame3Input.setTrade_id(input.getTrade_id());
            dbConnection.execute(connection, frame3Input, frame3Output);
            if (frame3Output.getTax_amount() < 0.00) {
                output.setStatus(ErrorCode.TRF3_ERROR1);
            }
        }
        //
        // FRAME 4
        //

        // Copy Frame 4 Input
        frame4Input.setCust_id(frame2Output.getCust_id());
        frame4Input.setSymbol(frame1Output.getSymbol());
        frame4Input.setTrade_qty(frame1Output.getTrade_qty());
        frame4Input.setType_id(frame1Output.getType_id());

        // Execute Frame 4
        dbConnection.execute(connection, frame4Input, frame4Output);
        // Validate Frame 4 Output
        if (frame4Output.getComm_rate() <= 0.0000) {
            output.setStatus(ErrorCode.TRF4_ERROR1);
        }

        //
        // FRAME 5
        //

        // Copy Frame 5 Input
        frame5Input.setBroker_id(frame2Output.getBroker_id());
        frame5Input.setComm_amount((frame4Output.getComm_rate() / 100.00) * (frame1Output.getTrade_qty() * input.getTrade_price()));
        // round up for correct precision (cents only)
        frame5Input.setComm_amount((double) ((int) (100.00 * frame5Input.getComm_amount() + 0.5)) / 100.00);
        // ToDo: Need to get completed ID from constant struct!!
        frame5Input.setSt_completed_id("CMPT");
        frame5Input.setTrade_dts(frame2Output.getTrade_dts());
        frame5Input.setTrade_id(input.getTrade_id());
        frame5Input.setTrade_price(input.getTrade_price());
        // Execute Frame 5
        dbConnection.execute(connection, frame5Input);
        //
        // FRAME 6
        //

        // Compute Frame 6 Input
        TimestampType due_date_time = frame2Output.getTrade_dts();
        Date t = due_date_time.getM_date();
        // add 2 days
        // zero out time portion
        DateUtils.addDays(t, 2);
        DateUtils.setHours(t, 0);
        DateUtils.setMinutes(t, 0);
        DateUtils.setSeconds(t, 0);
        DateUtils.setMilliseconds(t, 0);
        due_date_time = new TimestampType(t);


        if (frame1Output.getType_is_sell() == 1) {
            frame6Input.setSe_amount(frame1Output.getTrade_qty()
                * input.getTrade_price() - frame1Output.getCharge() - frame5Input.getComm_amount());
        } else {
            frame6Input.setSe_amount(-1 * (frame1Output.getTrade_qty()
                * input.getTrade_price() + frame1Output.getCharge() + frame5Input.getComm_amount()));
        }

        // withhold tax only for certain account tax status
        if (frame2Output.getTax_status() == 1) {
            frame6Input.setSe_amount(frame6Input.getSe_amount() - frame3Output.getTax_amount());
        }

        // Copy Frame 6 Input
        frame6Input.setAcct_id(frame1Output.getAcct_id());
        frame6Input.setDue_date(due_date_time);
        frame6Input.setS_name(frame4Output.getS_name());
        frame6Input.setTrade_dts(frame2Output.getTrade_dts());
        frame6Input.setTrade_id(input.getTrade_id());
        frame6Input.setTrade_is_cash(frame1Output.getTrade_is_cash());
        frame6Input.setTrade_qty(frame1Output.getTrade_qty());
        frame6Input.setType_name(frame1Output.getType_name());

        // Execute Frame 6
        dbConnection.execute(connection, frame6Input, frame6Output);
        // Copy Frame 6 Output
        output.setAcct_id(frame1Output.getAcct_id());
        output.setAcct_bal(frame6Output.getAcct_bal());
        output.setLoad_unit((int) ((frame2Output.getCust_id() - iTIdentShift - 1) / iDefaultLoadUnitSize + 1));

    }
}
