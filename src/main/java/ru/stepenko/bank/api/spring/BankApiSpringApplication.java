package ru.stepenko.bank.api.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BankApiSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApiSpringApplication.class, args);
	}

}
