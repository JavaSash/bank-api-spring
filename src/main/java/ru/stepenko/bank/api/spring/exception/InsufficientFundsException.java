package ru.stepenko.bank.api.spring.exception;

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
    }

    public InsufficientFundsException(String msg) {
        super(msg);
    }
}
