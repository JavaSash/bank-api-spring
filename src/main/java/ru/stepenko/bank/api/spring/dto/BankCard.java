package ru.stepenko.bank.api.spring.dto;

import lombok.Getter;
import ru.stepenko.bank.api.spring.utils.NumberGenerator;

import java.time.LocalDateTime;

@Getter
public class BankCard {

    private long cardId;
    private String cardNumber;
    private long accountId;
    private final LocalDateTime issueDate;

    BankCard(BankAccount account) {
        cardNumber = NumberGenerator.generateCardNumber();
        cardId = account.getCardInstances();
        accountId = account.getAccountId();
        issueDate = LocalDateTime.now();
    }
}
