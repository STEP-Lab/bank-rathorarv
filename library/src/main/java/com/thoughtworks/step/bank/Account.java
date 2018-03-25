package com.thoughtworks.step.bank;

public class Account {
    private final AccountNumber accountNumber;
    private double balance;

    private Account(AccountNumber accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public static  Account create(AccountNumber accountNumber, double balance) throws MinimumBalanceException {
        if(balance < 1000){
            throw new MinimumBalanceException();
        }
        return new Account(accountNumber,balance);
    }

    public double getBalance() {
        return balance;
    }


    public double debit(double amount) throws MinimumBalanceException {
        if (balance - amount > 1000) {
        balance -= amount;
        return balance;
        }else {
            throw new MinimumBalanceException();
        }
    }
    public double credit(double amount){
        balance += amount;
        return balance;
    }
}
