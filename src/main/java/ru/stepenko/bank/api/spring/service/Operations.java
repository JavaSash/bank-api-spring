package ru.stepenko.bank.api.spring.service;

import ru.stepenko.bank.api.spring.exception.NegativeAmountException;

import java.math.BigDecimal;

public interface Operations {

    default void checkAmount(BigDecimal amount) throws NegativeAmountException {
        if (amount.signum() == -1) {
            throw new NegativeAmountException("amount must be greater than 0");
        }
    }
}
