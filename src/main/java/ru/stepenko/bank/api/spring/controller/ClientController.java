package ru.stepenko.bank.api.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepenko.bank.api.spring.model.Client;
import ru.stepenko.bank.api.spring.service.ClientService;
//TODO методы должны возвращать однотипные ответы. Либо DTO rs либо ResponseEntity
@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService service;

    @GetMapping("/get/all")
    public Iterable<Client> getClients() {
        return service.getClients();
    }

    @GetMapping("/get/{id}")
    public Client getClientById(@PathVariable long id) {
        return service.getClient(id);
    }

    @PostMapping("/create")
    public Client createClient(@RequestBody Client client) {
        return service.save(client);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable long id, @RequestBody Client client) {
        return service.existClientWithId(id)
                ? new ResponseEntity<>(service.save(client), HttpStatus.OK)
                : new ResponseEntity<>(service.save(client), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable long id) {
        service.delete(id);
    }
}
