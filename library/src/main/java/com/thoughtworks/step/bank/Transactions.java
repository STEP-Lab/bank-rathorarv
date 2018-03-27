package com.thoughtworks.step.bank;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Transactions {

    protected ArrayList<Transaction> list;

    public Transactions() {
        this.list = new ArrayList<>();
    }
    public void debit(double amount,String name){
        list.add(new DebitTransaction(new Date(),name,amount));
    }
    public void credit(double amount,String name){
        list.add(new CreditTransaction(new Date(),name,amount));
    }

    public void print(PrintWriter printer) {
        for(Transaction transaction : list){
            printer.println(transaction.toString());
        }
        printer.close();
    }

    public void printCsv(PrintWriter writer) {
        for(Transaction transaction : list){
            writer.append(transaction.toCsv());
        }
        writer.close();
    }

    public ArrayList<Transaction> getDebitTransactions() {
        ArrayList<Transaction> debits = new ArrayList<>();
        for (Transaction transaction : list){
            if(transaction instanceof DebitTransaction){
                debits.add(transaction);
            }
        }
        return debits;
    }
    public ArrayList<Transaction> getCreditTransactions() {
        ArrayList<Transaction> credits = new ArrayList<>();
        for (Transaction transaction : list){
            if(transaction instanceof CreditTransaction){
                credits.add(transaction);
            }
        }
        return credits;
    }

    public ArrayList<Transaction> getTransactionsAfterGivenDate(Date date) {
        ArrayList<Transaction> aboveDateTransaction = new ArrayList<>();
        for (Transaction transaction : list){
            if(transaction.getDate().after(date)){
                aboveDateTransaction.add(transaction);
            }
        }
        return aboveDateTransaction;
    }
}
