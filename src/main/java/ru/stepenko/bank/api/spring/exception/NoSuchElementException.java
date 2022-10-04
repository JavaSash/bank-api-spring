package ru.stepenko.bank.api.spring.exception;

public class NoSuchElementException extends Exception {

    public NoSuchElementException() {
    }

    public NoSuchElementException(String element) {
        super(element);
    }
}
