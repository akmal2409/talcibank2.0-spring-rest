package tech.talci.talcibankspringrest.validators;

import org.springframework.stereotype.Component;
import tech.talci.talcibankspringrest.domain.Account;
import tech.talci.talcibankspringrest.domain.Deposit;
import tech.talci.talcibankspringrest.domain.Withdrawal;

import java.math.BigDecimal;

@Component
public class DepositValidator {

    public boolean isValid(Deposit deposit, Account account){
        BigDecimal positiveAmount = new BigDecimal("0.0");
        return deposit.getAmount().compareTo(positiveAmount) == 1;
    }
}
