package ru.stepenko.bank.api.spring.dto;

import ru.stepenko.bank.api.spring.exception.InsufficientFundsException;
import ru.stepenko.bank.api.spring.exception.NegativeAmountException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface AccountOperations extends Serializable {

    /**
     * Выпуск карты возможен только на активном аккаунте
     * Карта может быть привязана только к 1 аккаунту
     * @return выпущенную карту
     */
    BankCard issueCard();

    /**
     * @return список карт, принадлежащих данному аккаунту
     */
    List<BankCard> getAllCards();

    /**
     * Вносит средства на баланс аккаунта
     * @param amount сумма вносимых средств, > 0
     * @return баланс после пополнения
     * @throws NegativeAmountException если пытаться пополнить на отрицательную сумму
     */
    BigDecimal deposit(BigDecimal amount) throws NegativeAmountException;
    /**
     * Вывод средств с баланса аккаунта
     * @param amount сумма выводимых средств, > 0
     * @return баланс после снятия
     * @throws NegativeAmountException если пытаться снять отрицательную сумму
     */
    BigDecimal withdrawal(BigDecimal amount) throws NegativeAmountException, InsufficientFundsException;

    /**
     * @return текущий баланс
     */
    BigDecimal getBalance();
}
