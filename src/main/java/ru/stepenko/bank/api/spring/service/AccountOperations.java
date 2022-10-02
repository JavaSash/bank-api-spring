package ru.stepenko.bank.api.spring.service;

import ru.stepenko.bank.api.spring.exception.InsufficientFundsException;
import ru.stepenko.bank.api.spring.exception.NegativeAmountException;
import ru.stepenko.bank.api.spring.model.BankAccount;
import ru.stepenko.bank.api.spring.model.Client;

import java.math.BigDecimal;
import java.util.List;

public interface AccountOperations extends Operations {

    /**
     * Создание банковского аккаунта
     *
     * @param client клиент, которому открывается аккаунт
     * @return активный аккаунт
     */
    BankAccount createAccount(Client client);

    /**
     * Выпуск карты возможен только на активном аккаунте
     * Карта может быть привязана только к 1 аккаунту
     *
     * @return выпущенную карту
     */
    BankAccount.BankCard issueCard(BankAccount account);

    /**
     * @return список карт, принадлежащих данному аккаунту
     */
    List<BankAccount.BankCard> getAllCards(BankAccount account);

    /**
     * Вносит средства на баланс аккаунта
     *
     * @param amount сумма вносимых средств, > 0
     * @return баланс после пополнения
     * @throws NegativeAmountException если пытаться пополнить на отрицательную сумму
     */
    BigDecimal deposit(BankAccount account, BigDecimal amount) throws NegativeAmountException;

    /**
     * Вывод средств с баланса аккаунта
     *
     * @param amount сумма выводимых средств, > 0
     * @return баланс после снятия
     * @throws NegativeAmountException если пытаться снять отрицательную сумму
     */
    BigDecimal withdrawal(BankAccount account, BigDecimal amount) throws NegativeAmountException, InsufficientFundsException;

    /**
     * @return текущий баланс
     */
    BigDecimal getBalance(BankAccount account);


}
