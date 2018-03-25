package com.thoughtworks.step.bank;

import java.util.Date;

public class Transaction {
    protected Date date;
    protected final String to;
    protected final double amount;

    public Transaction(Date date, String to, double amount) {
        this.date = date;
        this.to = to;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
