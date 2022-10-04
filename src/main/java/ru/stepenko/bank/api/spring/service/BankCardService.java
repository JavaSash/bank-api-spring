package ru.stepenko.bank.api.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepenko.bank.api.spring.exception.InsufficientFundsException;
import ru.stepenko.bank.api.spring.exception.NegativeAmountException;
import ru.stepenko.bank.api.spring.model.BankAccount;
import ru.stepenko.bank.api.spring.repository.BankCardRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
@Service
public class BankCardService implements CardOperations {

    private final BankCardRepository repository;

    @Override
    public BigDecimal deposit(BankAccount.BankCard card, BigDecimal amount) throws NegativeAmountException {
        checkAmount(amount);

        BankAccount.BankCard dbCard = repository.findById(card.getId()).get();
        dbCard.deposit(amount);
        repository.save(dbCard);

        return dbCard.getBalance();
    }

    @Override
    public BigDecimal withdrawal(BankAccount.BankCard card, BigDecimal amount) throws NegativeAmountException, InsufficientFundsException {
        checkAmount(amount);
        if (card.getBalance().compareTo(amount) == -1) {
            throw new InsufficientFundsException();
        }

        BankAccount.BankCard dbCard = repository.findById(card.getId()).get();
        dbCard.withdraw(amount);
        repository.save(dbCard);

        return dbCard.getBalance();
    }

    @Override
    public BigDecimal getBalance(BankAccount.BankCard card) {
        return repository.findById(card.getId()).get()
                .getBalance().setScale(2, RoundingMode.HALF_UP);
    }
}
