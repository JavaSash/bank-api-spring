package ru.stepenko.bank.api.spring.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.stepenko.bank.api.spring.exception.InsufficientFundsException;
import ru.stepenko.bank.api.spring.exception.NegativeAmountException;
import ru.stepenko.bank.api.spring.utils.NumberGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BankAccount implements AccountOperations {

    @Getter
    private static long accInstances = 0L;
    @Getter
    private static long cardInstances = 0L;
    @Getter
    private long accountId;
    @Getter
    private String accountNumber;
    @Getter
    @Setter
    private String ownersFullName;
    @Getter
    private String ownersUuid;
    @Getter
    private final LocalDateTime issueDate;
    private List<BankCard> cards = new ArrayList<>();
    private BigDecimal balance = BigDecimal.ZERO;

    public BankAccount(Client client) {
        accInstances++;
        accountId = accInstances;
        accountNumber = NumberGenerator.generateAccountNumber();
        ownersFullName = client.getFullName();
        ownersUuid = client.getUuid();
        issueDate = LocalDateTime.now();
    }

    @Override
    public BankCard issueCard() {
        cardInstances++;
        BankCard card = new BankCard(this);
        cards.add(card);
        return card;
    }

    @Override
    public List<BankCard> getAllCards() {
        return cards;
    }

    @Override
    public BigDecimal deposit(BigDecimal amount) throws NegativeAmountException {
        checkAmount(amount);
        balance.add(amount);
        return getBalance();
    }

    @Override
    public BigDecimal withdrawal(BigDecimal amount) throws NegativeAmountException, InsufficientFundsException {
        checkAmount(amount);
        if(balance.compareTo(amount) == -1) {
            throw new InsufficientFundsException();
        }
        balance.subtract(amount);
        return getBalance();
    }

    @Override
    public BigDecimal getBalance() {
        return balance.setScale(2, RoundingMode.HALF_UP);
    }

    private void checkAmount(BigDecimal amount) throws NegativeAmountException {
        if(amount.signum() == -1) {
            throw new NegativeAmountException("amount must be greater than 0");
        }
    }
}
