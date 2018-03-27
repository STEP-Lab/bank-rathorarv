package com.thoughtworks.step.bank;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MoneyTest {

    private Money money;

    @Before
    public void setUp() throws Exception {
        money = new Money(1000.00, "INR");
    }

    @Test
    public void deductAmount() throws InvalidAmountException {
        money.deductAmount(500.0);
        assertThat(money.getBalance(),is(500.0));
    }

    @Test
    public void creditAmount() throws InvalidAmountException {
        money.creditAmount(500.0);
        assertThat(money.getBalance(),is(1500.0));
    }

    @Test
    public void getCurrenyDisplayName() {
        assertThat(money.getCurrencyDisplayName(),is("Indian Rupee"));
    }

    @Test(expected = InvalidAmountException.class)
    public void mustThrowEzxceptionWhenAmountIsNegative() throws InvalidAmountException {
        money.deductAmount(-12);
    }
}
