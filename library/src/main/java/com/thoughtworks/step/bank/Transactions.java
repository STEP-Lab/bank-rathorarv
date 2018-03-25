package com.thoughtworks.step.bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Transactions {

    protected ArrayList<Transaction> list;

    public Transactions() {
        this.list = new ArrayList<>();
    }
    public void debit(double amount,String name){
        list.add(new DebitTransaction(new Date(),name,amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transactions that = (Transactions) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {

        return Objects.hash(list);
    }
}
