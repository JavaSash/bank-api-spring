package ru.stepenko.bank.api.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = 6433234526707421413L;
    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("full_name")
    private String fullName;
    private String login;
    private String password;
    @OneToOne
    @JoinColumn(name = "account_id")
    private BankAccount account;
}
