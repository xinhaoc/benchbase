package com.oltpbenchmark.benchmarks.tpce.transactionsinterface;

import com.oltpbenchmark.benchmarks.tpce.TPCEConstants;
import com.oltpbenchmark.benchmarks.tpce.TPCEGenerator;
import com.oltpbenchmark.benchmarks.tpce.dbinterface.DBConnectionImpl;
import com.oltpbenchmark.benchmarks.tpce.emulator.MEE;
import com.oltpbenchmark.benchmarks.tpce.emulator.TTradeRequest;
import com.oltpbenchmark.benchmarks.tpce.inputdo.TTradeStatusTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.brokervolume.TBrokerVolumeFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.brokervolume.TBrokerVolumeTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.cleanup.TTradeCleanupTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition.TCustomerPositionFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition.TCustomerPositionFrame2Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.customerposition.TCustomerPositionTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.datamaintenance.TDataMaintenanceTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed.TMarketFeedFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketfeed.TMarketFeedTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.marketwatch.TMarketWatchTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.securitydetail.TSecurityDetailTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradelookup.*;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeorder.*;
import com.oltpbenchmark.benchmarks.tpce.inputdo.traderesult.*;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateFrame1Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateFrame2Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateFrame3Input;
import com.oltpbenchmark.benchmarks.tpce.inputdo.tradeupdate.TTradeUpdateTxnInput;
import com.oltpbenchmark.benchmarks.tpce.log.BaseLogger;
import com.oltpbenchmark.benchmarks.tpce.log.EGenLogFormatterTab;
import com.oltpbenchmark.benchmarks.tpce.log.EGenLogger;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TDataMaintenanceTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TMarketWatchFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.TTradeCleanupTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.brokervolume.TBrokerVolumeFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.brokervolume.TBrokerVolumeTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition.TCustomerPositionFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition.TCustomerPositionFrame2Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition.TCustomerPositionTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketfeed.TMarketFeedFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketfeed.TMarketFeedTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.marketwatch.TMarketWatchTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.securitydetail.TSecurityDetailFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.securitydetail.TSecurityDetailTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeloopup.*;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeorder.*;
import com.oltpbenchmark.benchmarks.tpce.outputdo.traderesult.*;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradestatus.TTradeStatusFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradestatus.TTradeStatusTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateFrame1Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateFrame2Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateFrame3Output;
import com.oltpbenchmark.benchmarks.tpce.outputdo.tradeupdate.TTradeUpdateTxnOutput;
import com.oltpbenchmark.benchmarks.tpce.pojo.ErrorCode;
import com.oltpbenchmark.benchmarks.tpce.sut.MEESUTInterface;
import com.oltpbenchmark.benchmarks.tpce.tablegenerator.SecurityHandler;
import com.oltpbenchmark.benchmarks.tpce.utils.TimestampType;
import org.apache.commons.lang3.time.DateUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.*;
import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.eMEETradeRequestAction.eMEEProcessOrder;
import static com.oltpbenchmark.benchmarks.tpce.TPCEConstants.eMEETradeRequestAction.eMEESetLimitOrderTrigger;
import static com.oltpbenchmark.benchmarks.tpce.outputdo.customerposition.TCustomerPositionTxnOutput.*;
import static com.oltpbenchmark.benchmarks.tpce.pojo.TxnHarnessStructs.*;
import static java.sql.Connection.TRANSACTION_READ_COMMITTED;

public class SUTInterfacesImpl implements SUTInterfaces {
    private final DBConnectionImpl dbConnection;

    private final ErrorCode errorCode;
    private final static long iTIdentShift = 4300000000L;
    private final static long iDefaultLoadUnitSize = 1000L;
    private BaseLogger logger;

    private static MEE marketExchangeGenerator;

    private static SUTInterfacesImpl sutInterfaces = new SUTInterfacesImpl();



    private SUTInterfacesImpl() {
//        String input_path = new File("src/main/resources/data/tpce" + File.separator).getAbsolutePath();
//
//        File inputDir = new File(input_path);
//        TPCEGenerator inputFiles = new TPCEGenerator(inputDir, TPCEConstants.DEFAULT_NUM_CUSTOMERS, TPCEConstants.DEFAULT_SCALE_FACTOR, TPCEConstants.DEFAULT_INITIAL_DAYS);
//
//        inputFiles.parseInputFiles();
//        SecurityHandler securityHandler = new SecurityHandler(inputFiles);
//
        this.dbConnection = new DBConnectionImpl();
        this.errorCode = new ErrorCode();
//        EGenLogFormatterTab logFormat = new com.oltpbenchmark.benchmarks.tpce.log.EGenLogFormatterTab();
//        this.logger = new EGenLogger(TPCEConstants.DriverType.eDriverEGenLoader, 0, logFormat);
//        this.marketExchangeGenerator = new MEE(0, new MEESUTInterface(), logger, securityHandler, 1,TPCEConstants.DEFAULT_NUM_CUSTOMERS);
//        this.marketExchangeGenerator.enableTickerTape();
         this.marketExchangeGenerator = MEE.getMee();
    }

    public static SUTInterfacesImpl getSutInterfaces(){
        return sutInterfaces;
    }



    @Override
    public void CBrokerVolume(Connection connection, TBrokerVolumeTxnInput input, TBrokerVolumeTxnOutput output) throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
        TBrokerVolumeFrame1Output frame1Output = new TBrokerVolumeFrame1Output();
        TBrokerVolumeFrame1Input frame1Input = new TBrokerVolumeFrame1Input();
        output.setStatus(ErrorCode.SUCCESS);

        dbConnection.execute(connection, input, frame1Output);
        if (frame1Output.getList_len() < 0 || frame1Output.getList_len() > max_broker_list_len) {
            output.setStatus(ErrorCode.BVF1_ERROR1);
        }
        output.setList_len(frame1Output.getList_len());
        for (int i = 0; i < frame1Output.getList_len(); i++) {
            output.getVolume()[i] = frame1Output.getVolume()[i];
        }
        connection.commit();
    }

    @Override
    public void CCustomerPosition(Connection connection, TCustomerPositionTxnInput input, TCustomerPositionTxnOutput output) throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
        TCustomerPositionFrame1Input frame1Input = new TCustomerPositionFrame1Input();
        TCustomerPositionFrame1Output frame1Output = new TCustomerPositionFrame1Output();
        TCustomerPositionFrame2Input frame2Input = new TCustomerPositionFrame2Input();
        TCustomerPositionFrame2Output frame2Output = new TCustomerPositionFrame2Output();

        output.setStatus(ErrorCode.SUCCESS);

        //set frame1 input params
        frame1Input.setCust_id(input.getCustId());
        frame1Input.setTax_id(input.getTaxId());
        //execute frame1
        dbConnection.execute(connection, frame1Input, frame1Output);
        if (frame1Output.getAcct_len() < 1 || frame1Output.getAcct_len() > max_acct_len) {
            output.setStatus(ErrorCode.CPF1_ERROR1);
        }

        output.setAcct_len(frame1Output.getAcct_len());
        output.setC_ad_id(frame1Output.getC_ad_id());
        output.setC_area_1(frame1Output.getC_area_1());
        output.setC_area_2(frame1Output.getC_area_2());
        output.setC_area_3(frame1Output.getC_area_3());
        output.setC_ctry_1(frame1Output.getC_ctry_1());
        output.setC_ctry_2(frame1Output.getC_ctry_2());
        output.setC_ctry_3(frame1Output.getC_ctry_3());
        output.setC_dob(frame1Output.getC_dob());
        output.setC_email_1(frame1Output.getC_email_1());
        output.setC_email_2(frame1Output.getC_email_2());
        output.setC_ext_1(frame1Output.getC_ext_1());
        output.setC_ext_2(frame1Output.getC_ext_2());
        output.setC_ext_3(frame1Output.getC_ext_3());
        output.setC_f_name(frame1Output.getC_f_name());
        output.setC_gndr(frame1Output.getC_gndr());
        output.setC_l_name(frame1Output.getC_l_name());
        output.setC_local_1(frame1Output.getC_local_1());
        output.setC_local_2(frame1Output.getC_local_2());
        output.setC_local_3(frame1Output.getC_local_3());
        output.setC_m_name(frame1Output.getC_m_name());
        output.setC_st_id(frame1Output.getC_st_id());
        output.setC_tier(frame1Output.getC_tier());


        for (int i = 0; i < frame1Output.getAcct_len() && i < max_acct_len; i++) {
            output.getAcct_id()[i] = frame1Output.getAcct_id()[i];
            output.getAsset_total()[i] = frame1Output.getAsset_total()[i];
            output.getCash_bal()[i] = frame1Output.getCash_bal()[i];
        }
        if (input.getHistory()) {

            if (frame1Output.getAcct_id()[(int) input.getAcctIdIndex()] == 0) {
                return;
            }
            frame2Input.setAcct_id(frame1Output.getAcct_id()[(int) input.getAcctIdIndex()]);
            dbConnection.execute(connection, frame2Input, frame2Output);
            if (frame2Output.getHist_len() < min_hist_len || frame2Output.getHist_len() > max_hist_len) {
                output.setStatus(ErrorCode.CPF2_ERROR1);
            }
            output.setHist_len(frame2Output.getHist_len());
            for (int i = 0; i < frame2Output.getHist_len() && i < max_hist_len; i++) {
                output.getHist_dts()[i] = frame2Output.getHist_dts()[i];
                output.getQty()[i] = frame2Output.getQty()[i];
                output.getSymbol()[i] = frame2Output.getSymbol()[i];
                output.getTrade_id()[i] = frame2Output.getTrade_id()[i];
                output.getTrade_status()[i] = frame2Output.getTrade_status()[i];
            }
        } else {
            //just commit transaction, frame3
            output.setHist_len(0);
        }
        connection.commit();
    }

    @Override
    public void CDataMaintenance(Connection connection, TDataMaintenanceTxnInput input, TDataMaintenanceTxnOutput output) {
        output.setStatus(ErrorCode.SUCCESS);
        dbConnection.execute(connection, input, output);
    }

    @Override
    public void CMarketFeed(Connection connection, TMarketFeedTxnInput input, TMarketFeedTxnOutput output) {
        TMarketFeedFrame1Input frame1Input = new TMarketFeedFrame1Input();
        TMarketFeedFrame1Output frame1Output = new TMarketFeedFrame1Output();
        output.setStatus(ErrorCode.SUCCESS);
        frame1Input.setStatusAndTradeType(input.getStatusAndTradeType());
        for (int i = 0; i < max_feed_len; i++) {
            frame1Input.getEntries()[i] = input.getEntries()[i];
        }

        //TODO send to market
        //dbConnection.execute(connection, frame1Input, frame1Output);
        if (frame1Output.getNum_updated() < input.getUnique_symbols()) {
            output.setStatus(ErrorCode.MFF1_ERROR1);
        }
        output.setSend_len(frame1Output.getSend_len());
    }

    @Override
    public void CMarketWatch(Connection connection, TMarketWatchTxnInput input, TMarketWatchTxnOutput output) throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
//        output.setStatus(ErrorCode.SUCCESS);
        if (input.getAcctId() != 0 || input.getCId() != 0 || input.getIndustryName().length() != 0) {
            TMarketWatchFrame1Output frame1Output = new TMarketWatchFrame1Output();
            dbConnection.execute(connection, input, frame1Output);
            output.setPct_change(frame1Output.getPct_change());
        } else {
            output.setStatus(ErrorCode.MWF1_ERROR1);
        }
        connection.commit();
    }

    @Override
    public void CSecurityDetail(Connection connection, TSecurityDetailTxnInput input, TSecurityDetailTxnOutput output) throws SQLException {

        TSecurityDetailFrame1Output frame1Output = new TSecurityDetailFrame1Output();
        output.setLast_vol(0);
        output.setNews_len(0);
        output.setStatus(ErrorCode.SUCCESS);
        dbConnection.execute(connection, input, frame1Output);

        if (frame1Output.getDay_len() < min_day_len || frame1Output.getDay_len() > max_day_len) {
            output.setStatus(ErrorCode.SDF1_ERROR1);
        }
        if (frame1Output.getFin_len() != max_fin_len) {
            output.setStatus(ErrorCode.SDF1_ERROR2);
        }
        if (frame1Output.getNews_len() != max_news_len) {
            output.setStatus(ErrorCode.SDF1_ERROR3);
        }
        output.setLast_vol(frame1Output.getLast_vol());
        output.setNews_len(frame1Output.getNews_len());


    }


    @Override
    public void CTradeCleanup(Connection connection, TTradeCleanupTxnInput input, TTradeCleanupTxnOutput output) throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
        output.setStatus(ErrorCode.SUCCESS);
        dbConnection.execute(connection, input, output);
        connection.commit();
    }

    @Override
    public void CTradeLookup(Connection connection, TTradeLookupTxnInput input, TTradeLookupTxnOutput output) {
        output.setStatus(ErrorCode.SUCCESS);

        switch (input.getFrameToExecute()) {
            case 1:
                TTradeLookupFrame1Input frame1Input = new TTradeLookupFrame1Input();
                TTradeLookupFrame1Output frame1Output = new TTradeLookupFrame1Output();

                frame1Input.setMax_trades(input.getMaxTrades());
                frame1Input.setTrade_id(input.getTradeId());
                dbConnection.execute(connection, frame1Input, frame1Output);

                if (frame1Output.getNum_found() != input.getMaxTrades()) {
                    output.setStatus(ErrorCode.TLF1_ERROR1);
                }
                output.setFrame_executed(1);
                for (int i = 0; i < frame1Output.getNum_found() && i < TradeLookupFrame1MaxRows; i++) {
                    output.getIs_cash()[i] = frame1Output.getTrade_info()[i].isIs_cash();
                    output.getIs_market()[i] = frame1Output.getTrade_info()[i].isIs_market();
                }
                output.setNum_found(frame1Output.getNum_found());
                break;
            case 2:
                TTradeLookupFrame2Input frame2Input = new TTradeLookupFrame2Input();
                TTradeLookupFrame2Output frame2Output = new TTradeLookupFrame2Output();
                frame2Input.setAcct_id(input.getAcctId());
                frame2Input.setMax_trades(input.getMaxTrades());
                frame2Input.setStart_trade_dts(input.getStartTradeDts());
                frame2Input.setEnd_trade_dts(input.getEndTradeDts());

                dbConnection.execute(connection, frame2Input, frame2Output);
                // Validate Frame 2 Output
                if (frame2Output.getNum_found() < 0 || frame2Output.getNum_found() > frame2Input.getMax_trades()) {
                    output.setStatus(ErrorCode.TLF2_ERROR1);
                }
                if (frame2Output.getNum_found() == 0) {
                    output.setStatus(ErrorCode.TLF2_WARN1);
                }

                // Copy Frame 2 Output
                output.setFrame_executed(2);
                for (int i = 0; i < frame2Output.getNum_found() && i < TradeLookupFrame2MaxRows; i++) {
                    output.getIs_cash()[i] = frame2Output.getTrade_info()[i].isIs_cash();
                    output.getTrade_list()[i] = frame2Output.getTrade_info()[i].getTrade_id();
                }
                output.setNum_found(frame2Output.getNum_found());
                break;
            case 3:
                TTradeLookupFrame3Input frame3Input = new TTradeLookupFrame3Input();
                TTradeLookupFrame3Output frame3Output = new TTradeLookupFrame3Output();
                frame3Input.setMax_trades(input.getMaxTrades());
                frame3Input.setSymbol(input.getSymbol());
                frame3Input.setStart_trade_dts(input.getStartTradeDts());
                frame3Input.setEnd_trade_dts(input.getEndTradeDts());
                frame3Input.setMax_acct_id(input.getMaxAcctId());
                // Execute Frame 3
                dbConnection.execute(connection, frame3Input, frame3Output);
                // Validate Frame 3 Output
                if (frame3Output.getNum_found() < 0 || frame3Output.getNum_found() > frame3Input.getMax_trades()) {
                    output.setStatus(ErrorCode.TLF3_ERROR1);
                }
                if (frame3Output.getNum_found() == 0) {
                    output.setStatus(ErrorCode.TLF3_WARN1);
                }
                // Copy Frame 3 Output
                output.setFrame_executed(3);
                output.setNum_found(frame3Output.getNum_found());
                for (int i = 0; i < frame3Output.getNum_found() && i < TradeLookupFrame3MaxRows; i++) {
                    output.getIs_cash()[i] = frame3Output.getTrade_info()[i].isIs_cash();
                    output.getTrade_list()[i] = frame3Output.getTrade_info()[i].getTrade_id();
                }
                break;
            case 4:
                TTradeLookupFrame4Input frame4Input = new TTradeLookupFrame4Input();
                TTradeLookupFrame4Output frame4Output = new TTradeLookupFrame4Output();
                // Copy Frame 4 Input
                frame4Input.setAcct_id(input.getAcctId());
                frame4Input.setTrade_dts(input.getStartTradeDts());

                // Execute Frame 4
                dbConnection.execute(connection, frame4Input, frame4Output);
                // Validate Frame 4 Output
                // NOTE: The TLF4_ERROR2 check must be in an else clause, or else it could
                //       potentially overwrite the TLF1_ERROR1 status, if set.
                if (frame4Output.getNum_trades_found() < 0 || frame4Output.getNum_trades_found() > 1) {
                    output.setStatus(ErrorCode.TLF4_ERROR1);
                }
                if (frame4Output.getNum_trades_found() == 0) {
                    output.setStatus(ErrorCode.TLF4_WARN1);
                } else if (frame4Output.getNum_found() < 1 || frame4Output.getNum_found() > TradeLookupFrame4MaxRows) {
                    output.setStatus(ErrorCode.TLF4_ERROR2);
                }
                // Copy Frame 4 Output
                output.setFrame_executed(4);
                output.setNum_found(frame4Output.getNum_found());
                output.getTrade_list()[0] = frame4Output.getTrade_id();
                break;
            default:
                assert (false);
                break;
        }
    }

    @Override
    public void CTradeOrder(Connection connection, TTradeOrderTxnInput input, TTradeOrderTxnOutput output) throws SQLException {
        // Initialization
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
        TTradeOrderFrame1Input frame1Input = new TTradeOrderFrame1Input();
        TTradeOrderFrame1Output frame1Output = new TTradeOrderFrame1Output();
        TTradeOrderFrame2Input frame2Input = new TTradeOrderFrame2Input();
        TTradeOrderFrame2Output frame2Output = new TTradeOrderFrame2Output();
        TTradeOrderFrame3Input frame3Input = new TTradeOrderFrame3Input();
        TTradeOrderFrame3Output frame3Output = new TTradeOrderFrame3Output();
        TTradeOrderFrame4Input frame4Input = new TTradeOrderFrame4Input();
        TTradeOrderFrame4Output frame4Output = new TTradeOrderFrame4Output();
        TTradeRequest TradeRequestForMEE = new TTradeRequest();

        output.setStatus(ErrorCode.SUCCESS);
        //
        // FRAME 1
        //
        // Copy Frame 1 Input
        frame1Input.setAcct_id(input.getAcctId());

        // Execute Frame 1
        dbConnection.execute(connection, frame1Input, frame1Output);
        // Validate Frame 1 Output
        if (frame1Output.getNum_found() != 1) {
            output.setStatus(ErrorCode.TOF1_ERROR1);
        }
        // FRAME 2
        if (!frame1Output.getCust_l_name().equals(input.getExecLastName())
            || !frame1Output.getCust_f_name().equals(input.getExecFirstName()) || !frame1Output.getTax_id().equals(input.getExecTaxId())) {
            frame2Output.setAp_acl(new String());
            frame2Input.setAcct_id(input.getAcctId());
            frame2Input.setExec_f_name(input.getExecFirstName());
            frame2Input.setExec_l_name(input.getExecLastName());
            frame2Input.setExec_tax_id(input.getExecTaxId());
            dbConnection.execute(connection, frame2Input, frame2Output);

            if (frame2Output.getAp_acl().length() == 0) {
                output.setStatus(ErrorCode.TOF2_ERROR1);
                connection.rollback();
                return;
            }
        }
        // FRAME 3

        // Copy Frame 3 Input
        frame3Input.setAcct_id(input.getAcctId());
        frame3Input.setCust_id(frame1Output.getCust_id());
        frame3Input.setCust_tier(frame1Output.getCust_tier());
        frame3Input.setIs_lifo(input.getIsLifo());
        frame3Input.setIssue(input.getIssue());
        frame3Input.setSt_pending_id(input.getStPendingId());
        frame3Input.setSt_submitted_id(input.getStSubmittedId());
        frame3Input.setTax_status(frame1Output.getTax_status());
        frame3Input.setTrade_qty(input.getTradeQty());
        frame3Input.setTrade_type_id(input.getTradeTypeId());
        frame3Input.setType_is_margin(input.getTypeIsMargin());
        frame3Input.setCo_name(input.getCoNmae());
        frame3Input.setRequested_price(input.getRequestedPrice());
        frame3Input.setSymbol(input.getSymbol());

        // Execute Frame 3
        dbConnection.execute(connection, frame3Input, frame3Output);

        // Validate Frame 3 Output
        if (frame3Output.getSell_value() > frame3Output.getBuy_value() &&
            ((frame3Input.getTax_status() == 1) || (frame3Input.getTax_status() == 2)) && frame3Output.getTax_amount() == 0.00) {
            output.setStatus(ErrorCode.TOF3_ERROR1);
        }
        if (frame3Output.getComm_rate() == 0.0000) {
            output.setStatus(ErrorCode.TOF3_ERROR2);
        }
        if (frame3Output.getCharge_amount() == 0.00) {
            output.setStatus(ErrorCode.TOF3_ERROR3);
        }

        // Copy Frame 3 Output
        output.setBuy_value(frame3Output.getBuy_value());
        output.setSell_value(frame3Output.getSell_value());
        output.setTax_amount(frame3Output.getTax_amount());

        //
        // FRAME 4
        //

        // Copy Frame 4 Input

        frame4Input.setAcct_id(input.getAcctId());
        frame4Input.setBroker_id(frame1Output.getBroker_id());
        frame4Input.setCharge_amount(frame3Output.getCharge_amount());
        frame4Input.setComm_amount((frame3Output.getCharge_amount() / 100) * input.getTradeQty() * frame3Output.getRequested_price());
        // round up for correct precision (cents only)

        frame4Input.setComm_amount((double) ((int) (100.00 * frame4Input.getComm_amount() + 0.5)) / 100.00);
        frame4Input.setExec_name(input.getExecFirstName() + " " + input.getExecLastName());
        frame4Input.setIs_cash(frame3Input.getType_is_margin() == 1 ? 0 : 1);
        frame4Input.setIs_lifo(input.getIsLifo());
        frame4Input.setRequested_price(frame3Output.getRequested_price());
        frame4Input.setStatus_id(frame3Output.getStatus_id());
        frame4Input.setSymbol(frame3Output.getSymbol());
        frame4Input.setTrade_qty(input.getTradeQty());
        frame4Input.setTrade_type_id(input.getTradeTypeId());
        frame4Input.setType_is_market(frame3Output.getType_is_market());

        // Execute Frame 4
        dbConnection.execute(connection, frame4Input, frame4Output);

        // Copy Frame 4 Output
        output.setTrade_id(frame4Output.getTrade_id());
        //
        // FRAME 5/6
        //

        if (input.getRollItBack() == 1) {
            // Execute Frame 5
            connection.rollback();
            output.setStatus(ErrorCode.EXPECTED_ROLLBACK);
        } else {
            // Execute Frame 6
            // commit the transaction we are inside
            connection.commit();
            output.setStatus(ErrorCode.SUCCESS);

            //
            // Send to Market Exchange Emulator
            TradeRequestForMEE.setPrice_quote(frame4Input.getRequested_price());
            TradeRequestForMEE.setSymbol(frame4Input.getSymbol());
            TradeRequestForMEE.setTrade_id(frame4Output.getTrade_id());
            TradeRequestForMEE.setTrade_qty(frame4Input.getTrade_qty());
            TradeRequestForMEE.setTrade_type_id(input.getTradeTypeId());

            if (frame4Input.getType_is_market() == 1) {
                TradeRequestForMEE.eAction = eMEEProcessOrder;
            } else {
                TradeRequestForMEE.eAction = eMEESetLimitOrderTrigger;
            }

            marketExchangeGenerator.submitTradeRequest(connection, TradeRequestForMEE); // maybe should check the return code here
        }


    }


//    }



    @Override
    public void CTradeStatus(Connection connection, TTradeStatusTxnInput input, TTradeStatusTxnOutput output) throws SQLException {
        TTradeStatusFrame1Output frame1Output = new TTradeStatusFrame1Output();
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
        output.setStatus(ErrorCode.SUCCESS);
        dbConnection.execute(connection, input, frame1Output);
        // Validate Frame 1 Output todo
//        if (frame1Output.getNum_found() != max_trade_status_len) {
//            output.setStatus(ErrorCode.TSF1_ERROR1);
//        }

        // Copy Frame 1 Output
        for (int i = 0; i < frame1Output.getNum_found() && i < max_trade_status_len; i++) {
            output.getStatus_name()[i] = frame1Output.getStatus_name()[i];
            output.getTrade_id()[i] = frame1Output.getTrade_id()[i];
        }
        connection.commit();
    }

    @Override
    public void CTradeUpdate(Connection connection, TTradeUpdateTxnInput input, TTradeUpdateTxnOutput output) {
        output.setStatus(ErrorCode.SUCCESS);
        switch (input.getFrameToExecute()) {
            case 1:

                TTradeUpdateFrame1Input frame1Input = new TTradeUpdateFrame1Input();
                TTradeUpdateFrame1Output frame1Output = new TTradeUpdateFrame1Output();
                // Copy Frame 1 Input
                frame1Input.setMax_trades(input.getMaxTrades());
                frame1Input.setMax_updates(input.getMaxUpdates());
                frame1Input.setTrade_id(input.getTradeId());

                // Execute Frame 1
                dbConnection.execute(connection, frame1Input, frame1Output);
                // Validate Frame 1 Output
                if (frame1Output.getNum_found() != input.getMaxTrades()
                    || frame1Output.getNum_updated() != input.getMaxUpdates()) {
                    output.setStatus(ErrorCode.TUF1_ERROR1);
                }

                // Copy Frame 1 Output
                output.setFrame_executed(1);
                for (int i = 0; i < frame1Output.getNum_found() && i < TradeUpdateFrame1MaxRows; i++) {
                    output.getIs_cash()[i] = frame1Output.getTrade_info()[i].isIs_cash();
                    output.getIs_market()[i] = frame1Output.getTrade_info()[i].isIs_market();
                }
                output.setNum_found(frame1Output.getNum_found());
                output.setNum_updated(frame1Output.getNum_updated());
                break;
            case 2:
                // Initialize
                TTradeUpdateFrame2Input frame2Input = new TTradeUpdateFrame2Input();
                TTradeUpdateFrame2Output frame2Output = new TTradeUpdateFrame2Output();
                // Copy Frame 2 Input
                frame2Input.setAcct_id(input.getAcctId());
                frame2Input.setMax_trades(input.getMaxTrades());
                frame2Input.setMax_updates(input.getMaxUpdates());
                frame2Input.setStart_trade_dts(input.getStartTradeDts());
                frame2Input.setEnd_trade_dts(input.getEndTradeDts());

                // Execute Frame 2
                dbConnection.execute(connection, frame2Input, frame2Output);

                /* valid relationships           */
                /* 1) num_found   <= max_trades  */
                /* 2) num_updated <= max_updates */
                /* 3) max_updates <= max_trades  */
                /* expected limits               */
                /* 4) max_trades   = 20          */
                /* 5  max_updates  = 20          */
                /* 6) num_found   >=  0          */
                /* 7) num_updated  = num_found   */

                // Validate Frame 2 Output
                if (frame2Output.getNum_updated() != frame2Output.getNum_found()
                    || frame2Output.getNum_found() < 0 || frame2Output.getNum_found() > frame2Input.getMax_trades()) {
                    output.setStatus(ErrorCode.TUF2_ERROR1);
                }
                if (frame2Output.getNum_updated() == 0) {
                    output.setStatus(ErrorCode.TUF2_WARN1);
                }

                // Copy Frame 2 Output
                output.setFrame_executed(2);
                for (int i = 0; i < frame2Output.getNum_found() && i < TradeUpdateFrame2MaxRows; i++) {
                    output.getIs_cash()[i] = frame2Output.getTrade_info()[i].isIs_cash();
                    output.getTrade_list()[i] = frame2Output.getTrade_info()[i].getTrade_id();
                }
                output.setNum_found(frame2Output.getNum_found());
                output.setNum_updated(frame2Output.getNum_updated());
                break;
            case 3:
                TTradeUpdateFrame3Input frame3Input = new TTradeUpdateFrame3Input();
                TTradeUpdateFrame3Output frame3Output = new TTradeUpdateFrame3Output();


                // Copy Frame 3 Input
                frame3Input.setMax_trades(input.getMaxTrades());
                frame3Input.setMax_updates(input.getMaxUpdates());
                frame3Input.setSymbol(input.getSymbol());
                frame3Input.setStart_trade_dts(input.getStartTradeDts());
                frame3Input.setEnd_trade_dts(input.getEndTradeDts());
                frame3Input.setMax_acct_id(input.getMaxAcctId());
                // Execute Frame 3
                dbConnection.execute(connection, frame3Input, frame3Output);
                /* valid relationships           */
                /* 1) num_found   <= max_trades  */
                /* 2) num_updated <= max_updates */
                /* 3) max_updates <= max_trades  */
                /* expected limits               */
                /* 4) max_trades   = 20          */
                /* 5  max_updates  = 20          */
                /* 6) num_found   >=  1          */
                /* 7) num_updated <= num_found   */
                /* 8) num_updated >=  0          */

                // Validate Frame 3 Output

                if (frame3Output.getNum_found() < 0
                    || frame3Output.getNum_found() > frame3Input.getMax_trades()
                    || frame3Output.getNum_updated() > frame3Output.getNum_found()) {
                    output.setStatus(ErrorCode.TUF3_ERROR1);
                }
                if (frame3Output.getNum_updated() == 0) {
                    output.setStatus(ErrorCode.TUF3_WARN1);
                }
                // Copy Frame 3 Output
                output.setFrame_executed(3);
                output.setNum_found(frame3Output.getNum_found());
                output.setNum_updated(frame3Output.getNum_updated());
                for (int i = 0; i < frame3Output.getNum_found() && i < 1000; i++) {
                    output.getIs_cash()[i] = frame3Output.getTrade_info()[i].isIs_cash();
                    output.getTrade_list()[i] = frame3Output.getTrade_info()[i].getTrade_id();
                }
                break;
            default:
                assert (false);
                break;

        }
    }
}
