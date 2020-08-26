package tech.talci.talcibankspringrest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long number;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "holders_name")
    private String holdersName;

    @Column(name = "issue_date")
    private Instant issueDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "card_type")
    @Enumerated(value = EnumType.STRING)
    private CardType cardType;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    private BigDecimal balance;

    private int cvv;
}
