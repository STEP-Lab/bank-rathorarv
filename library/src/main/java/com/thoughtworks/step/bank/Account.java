package com.thoughtworks.step.bank;

public class Account {
    private final AccountNumber accountNumber;
    private Money money;

    private Account(AccountNumber accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.money = new Money(balance,"INR");
    }

    public static  Account create(AccountNumber accountNumber, double balance) throws MinimumBalanceException {
        if(balance < 1000){
            throw new MinimumBalanceException();
        }
        return new Account(accountNumber,balance);
    }

    public double getBalance() {
        return money.getBalance();
    }


    public double debit(double amount) throws InvalidAmountException {
        money.deductAmount(amount);
        return money.getBalance();
    }
    public double credit(double amount) throws InvalidAmountException {
        money.creditAmount(amount);
        return money.getBalance();
    }
}
