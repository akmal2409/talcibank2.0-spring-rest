package tech.talci.talcibankspringrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private Long id;
    private Long number;
    private String holdersName;
    private Date expirationDate;
    private Date issueDate;
    private String cardType;
    private String Currency;
    private BigDecimal balance;
}
