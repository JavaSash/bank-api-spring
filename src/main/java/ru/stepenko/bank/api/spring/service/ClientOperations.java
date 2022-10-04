package ru.stepenko.bank.api.spring.service;

import ru.stepenko.bank.api.spring.exception.NoSuchElementException;
import ru.stepenko.bank.api.spring.model.Client;

public interface ClientOperations {

    Iterable<Client> getClients();

    Client getClient(long id) throws NoSuchElementException;

    Client save(Client client);

    void delete(long id);

    boolean existClientWithId(long id);
}
