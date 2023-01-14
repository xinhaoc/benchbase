package com.oltpbenchmark.benchmarks.tpce.emulator;

import com.oltpbenchmark.benchmarks.tpce.TPCEConstants;
import com.oltpbenchmark.benchmarks.tpce.TPCEGenerator;
import com.oltpbenchmark.benchmarks.tpce.procedures.Datamaintaince;
import com.oltpbenchmark.benchmarks.tpce.procedures.TPCEProcedure;
import com.oltpbenchmark.benchmarks.tpce.procedures.TradeCleanup;
import com.oltpbenchmark.benchmarks.tpce.sut.DMSUTInterface;
import com.oltpbenchmark.benchmarks.tpce.fileparser.InputFileHandler;
import com.oltpbenchmark.benchmarks.tpce.inputdo.datamaintenance.TDataMaintenanceTxnInput;
import com.oltpbenchmark.benchmarks.tpce.inputdo.cleanup.TTradeCleanupTxnInput;
import com.oltpbenchmark.benchmarks.tpce.log.BaseLogger;
import com.oltpbenchmark.benchmarks.tpce.pojo.TableConsts;
import com.oltpbenchmark.benchmarks.tpce.settings.DriverDMSettings;
import com.oltpbenchmark.benchmarks.tpce.settings.DriverGlobalSettings;
import com.oltpbenchmark.benchmarks.tpce.tablegenerator.CompanyGenerator;
import com.oltpbenchmark.benchmarks.tpce.tablegenerator.CustomerAccountsGenerator;
import com.oltpbenchmark.benchmarks.tpce.tablegenerator.CustomerSelection;
import com.oltpbenchmark.benchmarks.tpce.tablegenerator.SecurityHandler;
import com.oltpbenchmark.benchmarks.tpce.utils.EGenDate;
import com.oltpbenchmark.benchmarks.tpce.utils.EGenRandom;
import com.oltpbenchmark.benchmarks.tpce.tablegenerator.CustomerSelection.TierId;
import com.oltpbenchmark.benchmarks.tpce.TPCEGenerator.InputFile;
import com.oltpbenchmark.benchmarks.tpce.TPCEConstants.eStatusTypeID;

import java.util.List;

public class DM {

    private DriverGlobalSettings driverGlobalSettings;
    private DriverDMSettings driverDMSettings;

    private EGenRandom rnd;
    private CustomerSelection customerSelection;
    private CustomerAccountsGenerator account;
    private SecurityHandler securities;
    private CompanyGenerator companies;
    private InputFileHandler taxRatesDivision;
    private InputFileHandler statusType;
    private long securityCount;
    private long companyCount;
    private long startFromCompany;
    private int divisionTaxCount;
    private long startFromCustomer;
    private long myCustomerCount;
    private int dataMaintenanceTableNum;

    private TDataMaintenanceTxnInput txnInput;
    private TTradeCleanupTxnInput cleanupTxnInput;
    private DMSUTInterface sut;
    private BaseLogger logger;

    public static final int iDataMaintenanceTableCount = 12;

    public static String DataMaintenanceTableName[] = {
        "ACCOUNT_PERMISSION",
        "ADDRESS",
        "COMPANY",
        "CUSTOMER",
        "CUSTOMER_TAXRATE",
        "DAILY_MARKET",
        "EXCHANGE",
        "FINANCIAL",
        "NEWS_ITEM",
        "SECURITY",
        "TAXRATE",
        "WATCH_ITEM"
    };

    private void autoSetRNGSeeds(long uniqueID) {
        int baseYear, baseMonth, baseDay, millisec;

        baseYear = EGenDate.getYear();
        baseMonth = EGenDate.getMonth();
        baseDay = EGenDate.getDay();
        millisec = (EGenDate.getHour() * EGenDate.MinutesPerHour + EGenDate.getMinute()) * EGenDate.SecondsPerMinute + EGenDate.getSecond();
        baseYear -= (baseYear % 5);

        long Seed;
        Seed = millisec / 100;
        Seed <<= 11;
        Seed += EGenDate.getDayNo(baseYear, baseMonth, baseDay) - EGenDate.getDayNo(baseYear, 1, 1);
        Seed <<= 33;
        rnd.setSeed(Seed);
        driverDMSettings.cur_RNGSeed = Seed;
    }

    private long generateRandomCustomerId() {
        return rnd.int64Range(startFromCustomer,
            startFromCustomer + driverGlobalSettings.cur_iActiveCustomerCount - 1);
    }

    private long generateRandomCustomerAccountId() {
        long iCustomerId;
        TierId iCustomerTier;
        Object[] customerId;
        customerId = customerSelection.genRandomCustomer();

        iCustomerId = Long.parseLong(customerId[0].toString());
        iCustomerTier = (TierId) customerId[1];
        return (account.genRandomAccId(rnd, iCustomerId, iCustomerTier)[0]);

    }

    private long generateRandomCompanyId() {
        return rnd.int64Range(startFromCompany, startFromCompany + companyCount - 1);
    }

    private long generateRandomSecurityId() {
        return rnd.int64Range(0, securityCount - 1);
    }

    private void initialize() {
        logger.sendToLogger(driverGlobalSettings);

        securityCount = SecurityHandler.getSecurityNum(myCustomerCount);
        companyCount = companies.getCompanyCount();
        startFromCompany = companies.generateCompId();
        divisionTaxCount = taxRatesDivision.getRecordsNum();
        startFromCustomer = TPCEConstants.DEFAULT_START_CUSTOMER_ID + TPCEConstants.IDENT_SHIFT;
    }

    public DM(DMSUTInterface pSUT, BaseLogger pLogger, TPCEGenerator inputFiles, long iConfiguredCustomerCount, long iActiveCustomerCount, int iScaleFactor, int iDaysOfInitialTrades, long uniqueID) {
        driverGlobalSettings = new DriverGlobalSettings(iConfiguredCustomerCount, iActiveCustomerCount, iScaleFactor, iDaysOfInitialTrades);
        driverDMSettings = new DriverDMSettings(uniqueID, 0);
        rnd = new EGenRandom();
        customerSelection = new CustomerSelection(rnd, TPCEConstants.DEFAULT_START_CUSTOMER_ID, TPCEConstants.ACTIVECUSTOMERCOUNT);
        account = (CustomerAccountsGenerator) inputFiles.getTableGen(TPCEConstants.TABLENAME_CUSTOMER_ACCOUNT);

        securities = new SecurityHandler(inputFiles);
        companies = (CompanyGenerator) inputFiles.getTableGen(TPCEConstants.TABLENAME_COMPANY);
        taxRatesDivision = inputFiles.getInputFile(TPCEGenerator.InputFile.TAXDIV);
        statusType = inputFiles.getInputFile(TPCEGenerator.InputFile.STATUS);
        divisionTaxCount = 0;
        dataMaintenanceTableNum = 0;
        sut = pSUT;
        logger = pLogger;
        myCustomerCount = iActiveCustomerCount;
        logger.sendToLogger("DM object constructed using constructor 1 (valid for publication: YES).");
        initialize();
        autoSetRNGSeeds(uniqueID);
        logger.sendToLogger(driverDMSettings);

    }

    public DM(DMSUTInterface pSUT, BaseLogger pLogger, TPCEGenerator inputFiles, long iConfiguredCustomerCount, long iActiveCustomerCount, int iScaleFactor,
              int iDaysOfInitialTrades, long uniqueID, long RNGSeed) {
        driverGlobalSettings = new DriverGlobalSettings(iConfiguredCustomerCount, iActiveCustomerCount, iScaleFactor, iDaysOfInitialTrades);
        driverDMSettings = new DriverDMSettings(uniqueID, RNGSeed);
        customerSelection = new CustomerSelection(rnd, TPCEConstants.DEFAULT_START_CUSTOMER_ID, TPCEConstants.ACTIVECUSTOMERCOUNT);
        account = (CustomerAccountsGenerator) inputFiles.getTableGen(TPCEConstants.TABLENAME_CUSTOMER_ACCOUNT);
        rnd = new EGenRandom(RNGSeed);
        securities = new SecurityHandler(inputFiles);
        companies = (CompanyGenerator) inputFiles.getTableGen(TPCEConstants.TABLENAME_COMPANY);
        taxRatesDivision = inputFiles.getInputFile(InputFile.TAXDIV);
        statusType = inputFiles.getInputFile(InputFile.STATUS);
        divisionTaxCount = 0;
        dataMaintenanceTableNum = 0;
        sut = pSUT;
        logger = pLogger;
        logger.sendToLogger("DM object constructed using constructor 1 (valid for publication: YES).");
        initialize();
        logger.sendToLogger(driverDMSettings);
    }


    public long getRNGSeed() {
        return (rnd.getSeed());
    }

    public TPCEProcedure generateProcedure() {
        txnInput = new TDataMaintenanceTxnInput();
        txnInput.setTableName(DataMaintenanceTableName[dataMaintenanceTableNum]);

        switch (dataMaintenanceTableNum) {
            case 0: // ACCOUNT_PERMISSION
                txnInput.setAcctId(generateRandomCustomerAccountId());
                break;
            case 1: // ADDRESS
                if (rnd.rndPercent(67)) {
                    txnInput.setCId(generateRandomCustomerId());
                } else {
                    txnInput.setCoId(generateRandomCompanyId());
                }
                break;
            case 2: // COMPANY
                txnInput.setCoId(generateRandomCompanyId());
                break;
            case 3: // CUSTOMER
                txnInput.setCId(generateRandomCustomerId());
                break;
            case 4: // CUSTOMER_TAXRATE
                txnInput.setCId(generateRandomCustomerId());
                break;
            case 5: // DAILY_MARKET
                txnInput.setSymbol(securities.createSymbol(generateRandomSecurityId(), txnInput.getSymbol().length()));
                txnInput.setDayOfMonth(rnd.intRange(1, 31));
                txnInput.setVolIncr(rnd.intRange(-2, 3));
                if (txnInput.getVolIncr() == 0) {
                    txnInput.setVolIncr(-3);
                }
                break;
            case 6: // EXCHANGE
                break;
            case 7: // FINANCIAL
                txnInput.setCoId(generateRandomCompanyId());
                break;
            case 8: // NEWS_ITEM
                txnInput.setCoId(generateRandomCompanyId());
                break;
            case 9: // SECURITY
                txnInput.setSymbol(securities.createSymbol(generateRandomSecurityId(), txnInput.getSymbol().length()));
                break;

            case 10: // TAXRATE
                List<String[]> pRates;
                int threshold;

                pRates = taxRatesDivision.getTuplesByIndex(rnd.intRange(0, divisionTaxCount - 1));
                threshold = rnd.intRange(0, pRates.size() - 1);
                txnInput.setTxId(pRates.get(threshold)[0]);
                break;
            case 11: // WATCH_ITEM
                txnInput.setCId(generateRandomCustomerId());
                break;
            default:
                assert (false);
        }
        dataMaintenanceTableNum = (dataMaintenanceTableNum + 1) % iDataMaintenanceTableCount;
        return new Datamaintaince(txnInput);
    }

    public TPCEProcedure generateCleanUpProcedure(){
        cleanupTxnInput = new TTradeCleanupTxnInput();
        cleanupTxnInput.setStart_trade_id((long) (((driverGlobalSettings.cur_iDaysOfInitialTrades * EGenDate.HoursPerWorkDay * EGenDate.SecondsPerHour * (driverGlobalSettings.cur_iActiveCustomerCount / driverGlobalSettings.cur_iScaleFactor)) * TPCEConstants.AbortTrade / 100) + 1));
        cleanupTxnInput.setSt_pending_id(statusType.getTupleByKey(eStatusTypeID.ePending.getVal())[0]);
        cleanupTxnInput.setSt_submitted_id(statusType.getTupleByKey(eStatusTypeID.eSubmitted.getVal())[0]);
        cleanupTxnInput.setSt_canceled_id(statusType.getTupleByKey(eStatusTypeID.eCanceled.getVal())[0]);
        return new TradeCleanup(cleanupTxnInput);
    }

}
