package com.thoughtworks.step.bank;

public class Account {
    private final AccountNumber accountNumber;
    private Money money;
    private Transactions transactions;

    private Account(AccountNumber accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.money = new Money(balance,"INR");
        this.transactions = new Transactions();
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
        transactions.debit(amount,"arvind",money.getBalance());
        return money.getBalance();
    }
    public double credit(double amount) throws InvalidAmountException {
        money.creditAmount(amount);
        transactions.credit(amount,"ishu",money.getBalance());
        return money.getBalance();
    }
}
