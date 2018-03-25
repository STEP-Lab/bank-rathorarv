package com.thoughtworks.step.bank;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest{
    @Test
    public void mustAddDebitTransactions() {
        Transactions transactions = new Transactions();
        transactions.debit(1002.0,"to");
        Date date = transactions.list.get(0).getDate();
        assertThat(transactions.list,hasItem(new DebitTransaction(date,"to",1002.0)));
    }

    @Test
    public void mustAddCreditTransaction() {
        Transactions transactions = new Transactions();
        transactions.credit(102.0,"from");
        Date date = transactions.list.get(0).getDate();
        assertThat(transactions.list,hasItem(new CreditTransaction(date,"from",102.0)));
    }
}