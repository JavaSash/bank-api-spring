package ru.stepenko.bank.api.spring.service;

import org.springframework.stereotype.Service;
import ru.stepenko.bank.api.spring.exception.InsufficientFundsException;
import ru.stepenko.bank.api.spring.exception.NegativeAmountException;
import ru.stepenko.bank.api.spring.model.BankAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class BankCardService implements CardOperations {

    @Override
    public BigDecimal deposit(BankAccount.BankCard card, BigDecimal amount) throws NegativeAmountException {
        checkAmount(amount);
        return card.deposit(amount);
    }

    @Override
    public BigDecimal withdrawal(BankAccount.BankCard card, BigDecimal amount) throws NegativeAmountException, InsufficientFundsException {
        checkAmount(amount);
        if (card.getBalance().compareTo(amount) == -1) {
            throw new InsufficientFundsException();
        }
        return card.withdraw(amount);
    }

    @Override
    public BigDecimal getBalance(BankAccount.BankCard card) {
        return card.getBalance().setScale(2, RoundingMode.HALF_UP);
    }
}
