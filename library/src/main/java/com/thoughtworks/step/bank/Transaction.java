package com.thoughtworks.step.bank;

import java.util.Date;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(date, that.date) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, to, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                '}';
    }
    public String toCsv(){
        return date + "," + to + "," + amount;
    }
}
