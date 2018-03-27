package com.thoughtworks.step.bank;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class TransactionsTest{

    private Transactions transactions;

    @Before
    public void setUp() {
        transactions = new Transactions();
    }

    @Test
    public void mustAddDebitTransactions() {
        Transactions transactions = new Transactions();
        transactions.debit(1002.0,"to",1002.0);
            Date date = transactions.list.get(0).getDate();
        assertThat(transactions.list,hasItem(new DebitTransaction(date,"to",1002.0,1002.0)));
    }

    @Test
    public void mustAddCreditTransaction() {
        transactions.credit(102.0,"from", 102.0);
        Date date = transactions.list.get(0).getDate();
        assertThat(transactions.list,hasItem(new CreditTransaction(date,"from",102.0,102.0)));
    }

    @Test
    public void mustPrintTransaction() throws FileNotFoundException, UnsupportedEncodingException {
        ArrayList<String> result = new ArrayList<>();
        transactions.debit(1002.0,"to",1002.0);
        transactions.credit(1002.0,"from", 1002.0);
        DebitTransaction debitTransaction = new DebitTransaction(new Date(), "to", 1002.0,1002.0);
        CreditTransaction creditTransaction = new CreditTransaction(new Date(), "from", 1002.0,1002.0);
        PrintWriter printer = new PrintWriter("./transactions.txt","utf8"){
            @Override
            public void println(String x) {
                result.add(x);
            }
        };
        transactions.print(printer);
        assertThat(result,hasItems(debitTransaction.toString(),creditTransaction.toString()));
        printer.close();
    }

    @Test
    public void mustPrintInCsvFile() throws FileNotFoundException, UnsupportedEncodingException {
        ArrayList<CharSequence> result = new ArrayList<>();
        transactions.debit(1002.0,"to", 1002.0);
        PrintWriter writer = new PrintWriter("transactions.csv", "utf8"){
            @Override
            public void println(String string) {
                result.add(string);
            }
        };
        transactions.printCsv(writer);
        assertThat(result,hasItems("date,to,amount",transactions.list.get(0).toCsv()));
    }

    @Test
    public void getAllDebitTransaction() {
        transactions.debit(1000.0,"to", 1000.0);
        transactions.credit(1000.0,"from", 1000.0);
        DebitTransaction debitTransaction = new DebitTransaction(new Date(), "to", 1000.0,1000.0);
        ArrayList<Transaction> debitTransactions = transactions.getDebitTransactions();
        assertThat(debitTransactions,hasItem(debitTransaction));
    }
    @Test
    public void getAllCreditTransaction() {
        transactions.debit(1000.0,"to", 1000.0);
        transactions.credit(1000.0,"from", 1000.0);
        CreditTransaction creditTransaction = new CreditTransaction(new Date(), "from", 1000.0,1000.0);
        ArrayList<Transaction> creditTransactions = transactions.getCreditTransactions();
        assertThat(creditTransactions,hasItem(creditTransaction));
    }

    @Test
    public void getTransactionsafterDate() {
        transactions.debit(1000.0,"to", 1000);
        transactions.credit(2000.0,"from", 2000);
        Date debitDate = transactions.list.get(0).getDate();
        Date creditDate = transactions.list.get(1).getDate();
        CreditTransaction creditTransaction = new CreditTransaction(creditDate, "from", 2000.0,2000);
        DebitTransaction debitTransaction = new DebitTransaction(debitDate, "to", 1000.0,1000);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2018);
        cal.set(Calendar.MONTH, 2);
        cal.set(Calendar.DAY_OF_MONTH, 2);
        Date date = cal.getTime();
        ArrayList<Transaction> transactionsAfterGivenDate = transactions.getTransactionsAfterGivenDate(date);
        assertThat(transactionsAfterGivenDate,hasItems(creditTransaction, debitTransaction));
    }
    @Test
    public void getTransactionsBeforeDate() {
        transactions.debit(3000.0,"to", 3000.0);
        transactions.credit(2000.0,"from", 2000.0);
        Date debitDate = transactions.list.get(0).getDate();
        Date creditDate = transactions.list.get(1).getDate();
        CreditTransaction creditTransaction = new CreditTransaction(creditDate, "from", 2000.0,2000.0);
        DebitTransaction debitTransaction = new DebitTransaction(debitDate, "to", 3000.0,3000.0);
        Date date = new Date();
        date.setMinutes(date.getMinutes() + 12);
        ArrayList<Transaction> transactionsAfterGivenDate = transactions.getTransactionsBeforeGivenDate(date);
        assertThat(transactionsAfterGivenDate,hasItems(creditTransaction, debitTransaction));
    }
}