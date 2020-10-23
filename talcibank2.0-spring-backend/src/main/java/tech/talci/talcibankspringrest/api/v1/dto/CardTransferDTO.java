package tech.talci.talcibankspringrest.api.v1.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardTransferDTO {

    private String description;
    private BigDecimal amount;
    private Long recipient;
}
