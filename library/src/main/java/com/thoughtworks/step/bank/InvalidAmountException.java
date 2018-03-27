package com.thoughtworks.step.bank;

public class InvalidAmountException extends Throwable {
    public InvalidAmountException() {
        super("Invalid amount");
    }
}

