package tech.talci.talcibankspringrest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Double amount;
    private Instant date;

    @ManyToOne
    private Account account;

    @Enumerated(value = EnumType.STRING)
    private DepositType depositType;
}
