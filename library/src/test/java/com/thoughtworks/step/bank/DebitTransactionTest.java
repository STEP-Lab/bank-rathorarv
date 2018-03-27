package com.thoughtworks.step.bank;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DebitTransactionTest {
    @Test
    public void mustCreateDebitTransaction() {
        Date date = new Date();
        DebitTransaction debitTransaction = new DebitTransaction(date, "to", 200.00,200.0);
        assertThat(debitTransaction.getDate(),is(date));
    }
}
