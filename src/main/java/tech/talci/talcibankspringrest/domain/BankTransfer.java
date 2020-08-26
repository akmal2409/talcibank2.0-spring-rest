package tech.talci.talcibankspringrest.domain;

import java.math.BigDecimal;
import java.time.Instant;

public class BankTransfer {

    private Long id;

    private Instant date;

    private Account sender;

    private String description;

    private BigDecimal amount;

    private Long recipientNumber;

    private Currency currency;
}
