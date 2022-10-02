package ru.stepenko.bank.api.spring.service;

import ru.stepenko.bank.api.spring.exception.InsufficientFundsException;
import ru.stepenko.bank.api.spring.exception.NegativeAmountException;
import ru.stepenko.bank.api.spring.model.BankAccount;

import java.math.BigDecimal;

public interface CardOperations extends Operations {


    BigDecimal deposit(BankAccount.BankCard card, BigDecimal amount) throws NegativeAmountException;

    BigDecimal withdrawal(BankAccount.BankCard card, BigDecimal amount) throws NegativeAmountException, InsufficientFundsException;

    BigDecimal getBalance(BankAccount.BankCard card);
}
