package ru.stepenko.bank.api.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Client {

    private String uuid;
    private String fullName;
    private String login;
    private String password;
    private BankAccount account;
}
