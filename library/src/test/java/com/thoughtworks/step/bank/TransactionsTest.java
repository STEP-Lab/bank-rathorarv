package com.thoughtworks.step.bank;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
        transactions.debit(1002.0,"to");
            Date date = transactions.list.get(0).getDate();
        assertThat(transactions.list,hasItem(new DebitTransaction(date,"to",1002.0)));
    }

    @Test
    public void mustAddCreditTransaction() {
        transactions.credit(102.0,"from");
        Date date = transactions.list.get(0).getDate();
        assertThat(transactions.list,hasItem(new CreditTransaction(date,"from",102.0)));
    }

    @Test
    public void mustPrintTransaction() throws FileNotFoundException, UnsupportedEncodingException {
        ArrayList<String> result = new ArrayList<>();
        transactions.debit(1002.0,"to");
        transactions.credit(1002.0,"from");
        DebitTransaction debitTransaction = new DebitTransaction(new Date(), "to", 1002.0);
        CreditTransaction creditTransaction = new CreditTransaction(new Date(), "from", 1002.0);
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
        transactions.debit(1002.0,"to");
        PrintWriter writer = new PrintWriter("transactions.csv", "utf8"){
            @Override
            public PrintWriter append(CharSequence csq) {
                result.add(csq);
                return this;
            }
        };
        writer.append("date,to,amount\n");
        transactions.printCsv(writer);
        assertThat(result,hasItems("date,to,amount\n",transactions.list.get(0).toCsv()));
    }

    @Test
    public void getAllDebitTransaction() {
        transactions.debit(1000.0,"to");
        transactions.credit(1000.0,"from");
        DebitTransaction debitTransaction = new DebitTransaction(new Date(), "to", 1000.0);
        ArrayList<Transaction> debitTransactions = transactions.getDebitTransactions();
        assertThat(debitTransactions,hasItem(debitTransaction));
    }
    @Test
    public void getAllCreditTransaction() {
        transactions.debit(1000.0,"to");
        transactions.credit(1000.0,"from");
        CreditTransaction creditTransaction = new CreditTransaction(new Date(), "from", 1000.0);
        ArrayList<Transaction> creditTransactions = transactions.getCreditTransactions();
        assertThat(creditTransactions,hasItem(creditTransaction));
    }
}