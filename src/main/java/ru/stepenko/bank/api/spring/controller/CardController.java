package ru.stepenko.bank.api.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepenko.bank.api.spring.dto.CashMovementRq;
import ru.stepenko.bank.api.spring.exception.InsufficientFundsException;
import ru.stepenko.bank.api.spring.exception.NegativeAmountException;
import ru.stepenko.bank.api.spring.model.BankAccount;
import ru.stepenko.bank.api.spring.service.BankCardService;

import java.math.BigDecimal;

import static ru.stepenko.bank.api.spring.utils.Errors.INSUFFICIENT_FUNDS;
import static ru.stepenko.bank.api.spring.utils.Errors.NEGATIVE_AMOUNT;

/**
 * Контроллер всегда возвращает параметризованный объект ResponseEntity с телом и кодом ответа
 * 200 код ответа - запрос успешно выполнен
 * 400 код - ошибка бизнес логики
 * 500 код - ошибка транспорта
 * При бизнес ошибке в теле ответа будет описание этой ошибки
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/card")
public class CardController {

    private final BankCardService cardService;

    @PutMapping("/deposit")
    public ResponseEntity depositOnCard(@RequestBody CashMovementRq rq) {
        try {
            return new ResponseEntity(
                    cardService.deposit((BankAccount.BankCard) rq.getCashEntity(), rq.getAmount()),
                    HttpStatus.OK);
        } catch (NegativeAmountException e) {
            return new ResponseEntity(NEGATIVE_AMOUNT, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/withdraw")
    public ResponseEntity withdrawalFromCard(@RequestBody CashMovementRq rq) {
        try {
            return new ResponseEntity(
                    cardService.withdrawal((BankAccount.BankCard) rq.getCashEntity(), rq.getAmount()),
                    HttpStatus.OK);
        } catch (NegativeAmountException e) {
            return new ResponseEntity<>(NEGATIVE_AMOUNT, HttpStatus.BAD_REQUEST);
        } catch (InsufficientFundsException e) {
            return new ResponseEntity<>(INSUFFICIENT_FUNDS, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get-balance")
    public ResponseEntity<BigDecimal> getCardBalance(@RequestBody BankAccount.BankCard card) {
        return new ResponseEntity<>(cardService.getBalance(card), HttpStatus.OK);
    }
}
