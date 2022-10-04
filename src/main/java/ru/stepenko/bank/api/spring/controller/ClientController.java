package ru.stepenko.bank.api.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepenko.bank.api.spring.exception.NoSuchElementException;
import ru.stepenko.bank.api.spring.model.Client;
import ru.stepenko.bank.api.spring.service.ClientService;

import static ru.stepenko.bank.api.spring.utils.Errors.CLIENT_NOT_FOUND;

/**
 * Контроллер всегда возвращает параметризованный объект ResponseEntity с телом и кодом ответа
 * 200 код ответа - запрос успешно выполнен
 * 400 код - ошибка бизнес логики
 * 500 код - ошибка транспорта
 * При бизнес ошибке в теле ответа будет описание этой ошибки
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService service;

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return new ResponseEntity<>(service.save(client), HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Iterable<Client>> getClients() {
        return new ResponseEntity<>(service.getClients(), HttpStatus.OK) ;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getClientById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(service.getClient(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(CLIENT_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        return service.existClientWithId(client.getId())
                ? new ResponseEntity<>(service.save(client), HttpStatus.OK)
                : new ResponseEntity<>(service.save(client), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClient(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
