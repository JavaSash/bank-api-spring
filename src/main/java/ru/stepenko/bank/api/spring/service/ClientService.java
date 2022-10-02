package ru.stepenko.bank.api.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepenko.bank.api.spring.exception.InsufficientFundsException;
import ru.stepenko.bank.api.spring.exception.NegativeAmountException;
import ru.stepenko.bank.api.spring.model.BankAccount;
import ru.stepenko.bank.api.spring.model.Client;
import ru.stepenko.bank.api.spring.repository.ClientRepository;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService implements ClientOperations {

    private final ClientRepository repository;
    private final BankAccountService accountService;
    private final BankCardService cardService;

    @Override
    public Iterable<Client> getClients() {
        return repository.findAll();
    }

//   TODO что возвращать если нет такого клиента?
    @Override
    public Client getClient(long id) {
        return repository.findById(id).orElse(new Client());
    }

    @Override
    public Client save(Client client) {
        return repository.save(client);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existClientWithId(long id) {
        return repository.existsById(id);
    }

    @Override
    public BankAccount createAccount(Client client) {
        return new BankAccount(client);
    }

    @Override
    public BankAccount getAccount(Client client) {
        return client.getAccount();
    }

    @Override
    public BankAccount.BankCard issueCard(BankAccount account) {
        return accountService.issueCard(account);
    }

    @Override
    public List<BankAccount.BankCard> getAllCards(BankAccount account) {
        return accountService.getAllCards(account);
    }

    @Override
    public BigDecimal depositOnAccount(BankAccount account, BigDecimal amount) throws NegativeAmountException {
        return accountService.deposit(account, amount);
    }

    @Override
    public BigDecimal withdrawalFromAccount(BankAccount account, BigDecimal amount) throws NegativeAmountException, InsufficientFundsException {
        return accountService.withdrawal(account, amount);
    }

    @Override
    public BigDecimal getAccountBalance(BankAccount account) {
        return accountService.getBalance(account);
    }

    @Override
    public BigDecimal depositOnCard(BankAccount.BankCard card, BigDecimal amount) throws NegativeAmountException {
        return cardService.deposit(card, amount);
    }

    @Override
    public BigDecimal withdrawalFromCard(BankAccount.BankCard card, BigDecimal amount) throws NegativeAmountException, InsufficientFundsException {
        return cardService.withdrawal(card, amount);
    }

    @Override
    public BigDecimal getCardBalance(BankAccount.BankCard card) {
        return cardService.getBalance(card);
    }
}
