package com.thoughtworks.step.bank;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Transactions {

    protected ArrayList<Transaction> list;

    public Transactions() {
        this.list = new ArrayList<>();
    }
    public void debit(double amount, String name, double balance){
        list.add(new DebitTransaction(new Date(),name,amount,balance));
    }
    public void credit(double amount, String name, double balance){
        list.add(new CreditTransaction(new Date(),name,amount,balance));
    }

    public void print(PrintWriter printer) {
        for(Transaction transaction : list){
            printer.println(transaction.toString());
        }
    }

    public void printCsv(PrintWriter writer) {
        String[] headers = {"date","to","amount"};
        for(Transaction transaction : list){
            CsvPrinter csvPrinter = new CsvPrinter(writer, headers);
            csvPrinter.writer(transaction);
            csvPrinter.close();
        }
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
    public ArrayList<Transaction> getTransactionsBeforeGivenDate(Date date) {
        ArrayList<Transaction> aboveDateTransaction = new ArrayList<>();
        for (Transaction transaction : list){
            if(transaction.getDate().before(date)){
                aboveDateTransaction.add(transaction);
            }
        }
        return aboveDateTransaction;
    }

    public ArrayList<Transaction> getTransactionsAboveAmount(double amount) {
        ArrayList<Transaction> filteredTranactions = new ArrayList<>();
        for (Transaction transaction : list){
            if(transaction.getAmount()>amount){
                filteredTranactions.add(transaction);
            }
        }
        return filteredTranactions;
    }

    public ArrayList<Transaction> getTransactionsBelowAmount(double amount) {
        ArrayList<Transaction> filteredTranactions = new ArrayList<>();
        for (Transaction transaction : list){
            if(transaction.getAmount() < amount){
                filteredTranactions.add(transaction);
            }
        }
        return filteredTranactions;
    }
}
