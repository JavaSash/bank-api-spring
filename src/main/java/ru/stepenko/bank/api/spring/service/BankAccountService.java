package ru.stepenko.bank.api.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepenko.bank.api.spring.exception.InsufficientFundsException;
import ru.stepenko.bank.api.spring.exception.NegativeAmountException;
import ru.stepenko.bank.api.spring.exception.NoSuchElementException;
import ru.stepenko.bank.api.spring.model.BankAccount;
import ru.stepenko.bank.api.spring.model.Client;
import ru.stepenko.bank.api.spring.repository.BankAccountRepository;
import ru.stepenko.bank.api.spring.repository.BankCardRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


//TODO lock operations with balance
@RequiredArgsConstructor
@Service
public class BankAccountService implements AccountOperations {

    private final BankAccountRepository accountRepository;
    private final BankCardRepository cardRepository;

    public BankAccount createAccount(Client client) {
        return accountRepository.save(new BankAccount(client));
    }

    @Override
    public BankAccount getAccount(Client client) throws NoSuchElementException {
        return accountRepository.findById(client.getAccount().getId()).orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public BankAccount.BankCard issueCard(BankAccount account) {
        BankAccount.BankCard card = account.new BankCard();
        cardRepository.save(card);
        return cardRepository.findById(card.getId()).get();
    }

    @Override
    public List<BankAccount.BankCard> getAllCards(BankAccount account) {
        return accountRepository.findById(account.getId()).get().getCards();
    }

    @Override
    public BigDecimal deposit(BankAccount account, BigDecimal amount) throws NegativeAmountException {
        checkAmount(amount);

        BankAccount dbAccount = accountRepository.findById(account.getId()).get();
        dbAccount.deposit(amount);
        accountRepository.save(dbAccount);

        return dbAccount.getBalance();
    }

    @Override
    public BigDecimal withdrawal(BankAccount account, BigDecimal amount) throws NegativeAmountException, InsufficientFundsException {
        checkAmount(amount);
        if (account.getBalance().compareTo(amount) == -1) {
            throw new InsufficientFundsException();
        }

        BankAccount dbAccount = accountRepository.findById(account.getId()).get();
        dbAccount.withdraw(amount);
        accountRepository.save(dbAccount);

        return dbAccount.getBalance();
    }

    @Override
    public BigDecimal getBalance(BankAccount account) {
        return accountRepository.findById(account.getId()).get()
                .getBalance().setScale(2, RoundingMode.HALF_UP);
    }
}
