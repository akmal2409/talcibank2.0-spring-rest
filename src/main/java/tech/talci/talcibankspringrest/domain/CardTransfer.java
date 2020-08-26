package tech.talci.talcibankspringrest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Instant date;

    @ManyToOne
    @Column(name = "from_card")
    private Card fromCard;

    private String description;

    private BigDecimal amount;

    @Column(name = "recipient_card_number")
    private Long recipientCardNumber;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;
}
