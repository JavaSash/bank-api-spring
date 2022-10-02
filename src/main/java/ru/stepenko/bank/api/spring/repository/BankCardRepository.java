package ru.stepenko.bank.api.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.stepenko.bank.api.spring.model.BankAccount;

public interface BankCardRepository extends CrudRepository<BankAccount.BankCard, Long> {
}
