package com.thoughtworks.step.bank;

import java.util.Currency;
 
public class Money {
    private double balance;
    private Currency currency;

    protected Money(double balance, String locale) {
        this.balance = balance;
        this.currency = Currency.getInstance(locale);
    }
    protected void deductAmount(double amount) throws InvalidAmountException {
        if(amount < 0){
            throw new InvalidAmountException();
        }
        if(balance - amount > 0){
            balance -= amount;
        }
    }
    protected void creditAmount(double amount) throws InvalidAmountException {
        if(amount < 0){
            throw new InvalidAmountException();
        }
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrencyDisplayName() {
        return currency.getDisplayName();
    }
}
