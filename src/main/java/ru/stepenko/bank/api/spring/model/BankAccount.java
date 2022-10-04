package ru.stepenko.bank.api.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.stepenko.bank.api.spring.utils.NumberGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "accounts")
public class BankAccount extends CashEntity {

    private static long accInstances = 0L;
    @Getter
    private static long cardInstances = 0L;
    @Id
    @GeneratedValue
    private long id;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("owners_full_name")
    private String ownersFullName;
    @JsonProperty("owners_id")
    private long ownersId;
    @JsonProperty("owners_full_name")
    private LocalDateTime issueDate;
    @OneToMany
    @JoinColumn(name = "id")
    private List<BankAccount.BankCard> cards;
    private BigDecimal balance = BigDecimal.ZERO;

    public BankAccount(Client client) {
        accInstances++;
        id = accInstances;
        accountNumber = NumberGenerator.generateAccountNumber();
        ownersFullName = client.getFullName();
        ownersId = client.getId();
        issueDate = LocalDateTime.now();
    }

    @Override
    public BigDecimal deposit(BigDecimal amount) {
        return balance.add(amount);
    }

    @Override
    public BigDecimal withdraw(BigDecimal amount) {
        return balance.subtract(amount);
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    //    TODO add card status (active/blocked/closed etc)
//    TODO  подумать над отдельным балансом для карты, как часть баланса аккаунта. Плюс перевод между своими картами
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @Data
    @Entity
    @Table(name = "cards")
    public class BankCard extends CashEntity {

        @Id
        @GeneratedValue
        private long id;
        @JsonProperty("card_number")
        private String cardNumber;
        @JsonProperty("account_id")
        private long accountId;
        @JsonProperty("issue_date")
        private LocalDateTime issueDate;

        public BankCard() {
            cardNumber = NumberGenerator.generateCardNumber();
            id = getCardInstances();
            accountId = getId();
            issueDate = LocalDateTime.now();
            cardInstances++;
            cards.add(this);
        }

        @Override
        public BigDecimal getBalance() {
            return balance;
        }

        @Override
        public BigDecimal deposit(BigDecimal amount) {
            return balance.add(amount);
        }

        @Override
        public BigDecimal withdraw(BigDecimal amount) {
            return balance.subtract(amount);
        }
    }
}
