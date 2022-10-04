package ru.stepenko.bank.api.spring.model;

import java.math.BigDecimal;

public abstract class CashEntity {

    public abstract BigDecimal deposit(BigDecimal amount);
    public abstract BigDecimal withdraw(BigDecimal amount);
    public abstract BigDecimal getBalance();
}
