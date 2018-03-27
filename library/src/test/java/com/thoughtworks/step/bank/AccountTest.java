package com.thoughtworks.step.bank;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws InvalidAccountNumber, MinimumBalanceException {
        account = Account.create(new AccountNumber("1234-1234"), 2000.00);
    }

    @Test
    public void checkBalance() {
        assertThat(account.getBalance(), is(2000.00));
    }

    @Test(expected = InvalidAccountNumber.class)
    public void validateAccountNumber() throws InvalidAccountNumber, MinimumBalanceException {
        Account.create(new AccountNumber("1234-123a"), 2000.00);
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalance() throws InvalidAccountNumber, MinimumBalanceException {
        Account.create( new AccountNumber("1234-1234"), 200.00);
    }

    @Test
    public void withdrawValidAmount() throws MinimumBalanceException, InvalidAccountNumber, InvalidAmountException {
        Account account = Account.create(new AccountNumber("1234-1234"), 2000.00);
        account.debit(800);
        assertThat(account.getBalance(),is(1200.00));
    }
    @Test
    public void credit() throws InvalidAccountNumber, MinimumBalanceException, InvalidAmountException {
        Account account = Account.create(new AccountNumber("1234-1234"), 2000.00);
        account.credit(200.490);
        assertThat(account.getBalance(),is(2200.490));
    }
}