package com.powernode.bank.exception;

public class NotSufficientFundsException extends Exception{
    public NotSufficientFundsException() {

    }

    public NotSufficientFundsException(String message) {
        super(message);
    }
}
