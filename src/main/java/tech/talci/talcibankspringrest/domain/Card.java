package tech.talci.talcibankspringrest.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

public class Card {

    private Long id;

    private Long number;

    private User user;

    private String holdersName;

    private Instant issueDate;

    private Date expirationDate;

    private CardType cardType;

    private Currency currency;

    private BigDecimal balance;

    private int cvv;
}
