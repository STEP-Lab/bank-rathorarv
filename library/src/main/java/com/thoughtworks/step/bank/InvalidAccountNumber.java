package com.thoughtworks.step.bank;

public class InvalidAccountNumber extends Throwable {
    public InvalidAccountNumber() {
        super("Invalid account number");
    }
}
