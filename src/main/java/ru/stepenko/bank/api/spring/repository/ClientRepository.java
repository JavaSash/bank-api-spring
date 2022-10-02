package ru.stepenko.bank.api.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.stepenko.bank.api.spring.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
