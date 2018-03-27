package com.thoughtworks.step.bank;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CreditTransactionTest {
    @Test
    public void mustCreateCreditTransactions() {
        Date date = new Date();
        CreditTransaction creditTransaction = new CreditTransaction(date,"from",102.4,102.4);
        assertThat(creditTransaction.getDate(),is(date));
    }
}
