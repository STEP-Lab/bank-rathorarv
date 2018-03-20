package com.thoughtworks.step.bank;

public class LowAccountBalanceException extends Throwable {
    public LowAccountBalanceException() {
        super("Insufficient account balance for deduct");
    }
}
