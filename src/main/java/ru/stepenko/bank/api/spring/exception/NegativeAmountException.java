package ru.stepenko.bank.api.spring.exception;

public class NegativeAmountException extends Exception {

    public NegativeAmountException(String msg) {
        super(msg);
    }

    public NegativeAmountException() {
    }
}
