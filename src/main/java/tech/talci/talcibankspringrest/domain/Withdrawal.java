package tech.talci.talcibankspringrest.domain;

import java.time.Instant;

public class Withdrawal {

    private Long id;
    private Double amount;

    private Instant date;

    private Account account;
}
