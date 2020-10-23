package tech.talci.talcibankspringrest.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.talci.talcibankspringrest.domain.BankTransfer;
import tech.talci.talcibankspringrest.domain.Deposit;
import tech.talci.talcibankspringrest.domain.Withdrawal;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private String name;
    private Long number;
    private String currency;
    private String accountType;
    private Instant createdOn;
    private List<BankTransfer> bankTransfers;
    private List<Withdrawal> withdrawals;
    private List<Deposit> deposits;
    private String accountUrl;
    private BigDecimal balance;
}
