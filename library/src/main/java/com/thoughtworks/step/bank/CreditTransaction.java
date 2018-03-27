package com.thoughtworks.step.bank;

import java.util.Date;

class CreditTransaction extends Transaction {

    protected CreditTransaction(Date date, String to, double amount,double balance) {
        super(date, to, amount,balance );
    }
}
