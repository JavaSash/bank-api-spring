package ru.stepenko.bank.api.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepenko.bank.api.spring.dto.CashMovementRq;
import ru.stepenko.bank.api.spring.exception.InsufficientFundsException;
import ru.stepenko.bank.api.spring.exception.NegativeAmountException;
import ru.stepenko.bank.api.spring.exception.NoSuchElementException;
import ru.stepenko.bank.api.spring.model.BankAccount;
import ru.stepenko.bank.api.spring.model.Client;
import ru.stepenko.bank.api.spring.service.BankAccountService;

import java.math.BigDecimal;
import java.util.List;

import static ru.stepenko.bank.api.spring.utils.Errors.*;

/**
 * Контроллер всегда возвращает параметризованный объект ResponseEntity с телом и кодом ответа
 * 200 код ответа - запрос успешно выполнен
 * 400 код - ошибка бизнес логики
 * 500 код - ошибка транспорта
 * При бизнес ошибке в теле ответа будет описание этой ошибки
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final BankAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<BankAccount> createAccount(@RequestBody Client client) {
        return new ResponseEntity<>(accountService.createAccount(client), HttpStatus.CREATED) ;
    }

    @PostMapping("/get")
    public ResponseEntity getClientAccount(@RequestBody Client client) {
        try {
            return new ResponseEntity<>(accountService.getAccount(client), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(ACCOUNT_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/issue-card")
    public ResponseEntity<BankAccount.BankCard> issueCard(@RequestBody BankAccount account) {
        return new ResponseEntity<>(accountService.issueCard(account), HttpStatus.CREATED);
    }

    @PostMapping("/get-cards")
    public ResponseEntity<List<BankAccount.BankCard>> getAllCards(@RequestBody BankAccount account) {
        return new ResponseEntity<>(accountService.getAllCards(account), HttpStatus.OK);
    }

    @PutMapping("/deposit")
    public ResponseEntity depositOnAccount(@RequestBody CashMovementRq rq) {
        try {
            return new ResponseEntity<>(
                    accountService.deposit((BankAccount) rq.getCashEntity(), rq.getAmount()),
                    HttpStatus.OK);
        } catch (NegativeAmountException e) {
            return new ResponseEntity<>(NEGATIVE_AMOUNT, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/withdraw")
    public ResponseEntity withdrawalFromAccount(@RequestBody CashMovementRq rq) {
        try {
            return new ResponseEntity<>(
                    accountService.withdrawal((BankAccount) rq.getCashEntity(), rq.getAmount()),
                    HttpStatus.OK);
        } catch (NegativeAmountException e) {
            return new ResponseEntity<>(NEGATIVE_AMOUNT, HttpStatus.BAD_REQUEST);
        } catch (InsufficientFundsException e) {
            return new ResponseEntity<>(INSUFFICIENT_FUNDS, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get-balance")
    public ResponseEntity<BigDecimal> getAccountBalance(@RequestBody BankAccount account) {
        return new ResponseEntity<>(accountService.getBalance(account), HttpStatus.OK);
    }

}
