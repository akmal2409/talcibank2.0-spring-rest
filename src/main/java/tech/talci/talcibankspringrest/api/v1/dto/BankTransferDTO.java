package tech.talci.talcibankspringrest.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.talci.talcibankspringrest.domain.Account;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankTransferDTO {
    private String description;
    private BigDecimal amount;
    private String recipientNumber;
}
