package ru.stepenko.bank.api.spring.dto;

import lombok.Value;
import ru.stepenko.bank.api.spring.model.CashEntity;

import java.math.BigDecimal;

@Value
public class CashMovementRq {

    CashEntity cashEntity;
    BigDecimal amount;
}
