package tech.talci.talcibankspringrest.domain;

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
public class BankTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Instant date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account sender;

    @NotEmpty(message = "Transfer description is required")
    private String description;

    private BigDecimal amount;

    @Column(name = "recipient_number")
    private Long recipientNumber;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;
}
