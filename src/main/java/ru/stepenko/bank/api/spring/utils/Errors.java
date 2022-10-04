package ru.stepenko.bank.api.spring.utils;

import lombok.Getter;

@Getter
public enum Errors {

    CLIENT_NOT_FOUND("Client not found"),
    ACCOUNT_NOT_FOUND("Account not found"),
    NEGATIVE_AMOUNT("Amount is negative"),
    INSUFFICIENT_FUNDS("Insufficient funds");
    String description;

    Errors(String desc) {
        description = desc;
    }
}
