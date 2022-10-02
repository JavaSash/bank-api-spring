package ru.stepenko.bank.api.spring.service;

import ru.stepenko.bank.api.spring.exception.InsufficientFundsException;
import ru.stepenko.bank.api.spring.exception.NegativeAmountException;
import ru.stepenko.bank.api.spring.model.BankAccount;
import ru.stepenko.bank.api.spring.model.Client;

import java.math.BigDecimal;
import java.util.List;

public interface ClientOperations {

    Iterable<Client> getClients();

    Client getClient(long id);

    Client save(Client client);

    void delete(long id);

    boolean existClientWithId(long id);

    BankAccount createAccount(Client client);

    BankAccount getAccount(Client client);

    BankAccount.BankCard issueCard(BankAccount account);

    List<BankAccount.BankCard> getAllCards(BankAccount account);

    BigDecimal depositOnAccount(BankAccount account, BigDecimal amount) throws NegativeAmountException;

    BigDecimal withdrawalFromAccount(BankAccount account, BigDecimal amount) throws NegativeAmountException, InsufficientFundsException;

    BigDecimal getAccountBalance(BankAccount account);

    BigDecimal withdrawalFromCard(BankAccount.BankCard card, BigDecimal amount) throws NegativeAmountException, InsufficientFundsException;

    BigDecimal depositOnCard(BankAccount.BankCard card, BigDecimal amount) throws NegativeAmountException;

    BigDecimal getCardBalance(BankAccount.BankCard card);


}
