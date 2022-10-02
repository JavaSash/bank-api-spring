package ru.stepenko.bank.api.spring.service;

import org.springframework.stereotype.Service;
import ru.stepenko.bank.api.spring.exception.InsufficientFundsException;
import ru.stepenko.bank.api.spring.exception.NegativeAmountException;
import ru.stepenko.bank.api.spring.model.BankAccount;
import ru.stepenko.bank.api.spring.model.Client;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
//TODO lock operations with balance
@Service
public class BankAccountService implements AccountOperations {

    public BankAccount createAccount(Client client) {
        return new BankAccount(client);
    }

    @Override
    public BankAccount.BankCard issueCard(BankAccount account) {
        return account.new BankCard();
    }

    @Override
    public List<BankAccount.BankCard> getAllCards(BankAccount account) {
        return account.getCards();
    }

    @Override
    public BigDecimal deposit(BankAccount account, BigDecimal amount) throws NegativeAmountException {
        checkAmount(amount);
        return account.deposit(amount);
    }

    @Override
    public BigDecimal withdrawal(BankAccount account, BigDecimal amount) throws NegativeAmountException, InsufficientFundsException {
        checkAmount(amount);
        if (account.getBalance().compareTo(amount) == -1) {
            throw new InsufficientFundsException();
        }
        return account.withdraw(amount);
    }

    @Override
    public BigDecimal getBalance(BankAccount account) {
        return account.getBalance().setScale(2, RoundingMode.HALF_UP);
    }


}
