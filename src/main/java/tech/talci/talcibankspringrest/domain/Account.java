package tech.talci.talcibankspringrest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private Long number;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private BigDecimal balance;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    private Instant createdOn;

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    private List<CardTransfer> cardTransfers = new ArrayList<>();

    private List<Withdrawal> withdrawals = new ArrayList<>();

    private List<Deposit> deposits = new ArrayList<>();

    private List<BankTransfer>  bankTransfers = new ArrayList<>();

}
