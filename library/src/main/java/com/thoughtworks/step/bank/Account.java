package com.thoughtworks.step.bank;

public class Account {
    private final String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) throws MinimumBalanceException, InvalidAccountNumber {
        if(!accountNumber.matches("^\\d{4}-\\d{4}$")) {
            throw new InvalidAccountNumber();
        }
        this.accountNumber = accountNumber;
        if(balance < 1000){
            throw new MinimumBalanceException();
        }
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double debit(double amount) throws MinimumBalanceException {
        if (balance - amount > 1000) {
        balance -= amount;
        return balance;
        }else {
            throw new MinimumBalanceException();
        }
    }
}
