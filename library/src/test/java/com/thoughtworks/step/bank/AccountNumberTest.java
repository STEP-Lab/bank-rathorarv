package com.thoughtworks.step.bank;

import org.junit.Test;


public class AccountNumberTest {
    @Test
    public void CheckAccountNumberWithValidAccountNumber() throws InvalidAccountNumber {
        new AccountNumber("1234-1332");
    }

    @Test(expected = InvalidAccountNumber.class)
    public void checkAccountNumberWithNumberAndAlphabets() throws InvalidAccountNumber {
        new AccountNumber("1231-21sa");
    }

    @Test(expected = InvalidAccountNumber.class)
    public void checkAccountNUmberWithOnlyAlphabets() throws InvalidAccountNumber {
        new AccountNumber("aecsd-aa");
    }
}