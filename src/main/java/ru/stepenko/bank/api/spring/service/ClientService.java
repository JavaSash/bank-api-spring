package ru.stepenko.bank.api.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepenko.bank.api.spring.exception.NoSuchElementException;
import ru.stepenko.bank.api.spring.model.Client;
import ru.stepenko.bank.api.spring.repository.ClientRepository;

@RequiredArgsConstructor
@Service
public class ClientService implements ClientOperations {

    private final ClientRepository repository;

    @Override
    public Iterable<Client> getClients() {
        return repository.findAll();
    }

    @Override
    public Client getClient(long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException());
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
}
