package tech.talci.talcibankspringrest.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @JoinColumn(name = "card_id")
    private Card card;

    @NotEmpty(message = "Transfer description is required")
    private String description;

    private BigDecimal amount;

    @Column(name = "recipient_card_number")
    private Long recipientCardNumber;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;
}
