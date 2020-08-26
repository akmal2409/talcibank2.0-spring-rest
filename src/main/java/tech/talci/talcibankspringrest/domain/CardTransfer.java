package tech.talci.talcibankspringrest.domain;

import java.math.BigDecimal;
import java.time.Instant;

public class CardTransfer {

    private Long id;

    private Instant date;

    private Card fromCard;

    private String description;

    private BigDecimal amount;

    private Long recipientCardNumber;

    private Currency currency;
}
