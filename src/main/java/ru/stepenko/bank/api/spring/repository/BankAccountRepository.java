package ru.stepenko.bank.api.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.stepenko.bank.api.spring.model.BankAccount;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
}
