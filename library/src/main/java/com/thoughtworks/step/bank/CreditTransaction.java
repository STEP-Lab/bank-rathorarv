package com.thoughtworks.step.bank;

import java.util.Date;

class CreditTransaction extends Transaction {

    protected CreditTransaction(Date date, String to, double amount) {
        super(date, to, amount);
    }
}
