package tech.talci.talcibankspringrest.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.talci.talcibankspringrest.domain.CardTransfer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private Long id;
    private Long number;
    private String holdersName;
    private Date expirationDate;
    private Instant issueDate;
    private int cvv;
    private String cardType;
    private String currency;
    private BigDecimal balance;
    private List<CardTransfer> cardTransfers;
    private String cardUrl;
}
