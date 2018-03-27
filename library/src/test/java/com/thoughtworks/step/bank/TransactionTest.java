package com.thoughtworks.step.bank;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionTest {
    @Test
    public void createTransaction() {
        Date date = new Date();
        Transaction transaction = new Transaction(date, "to", 200.0,100.0);
        assertThat(transaction.getDate(),is(date));
    }
}
