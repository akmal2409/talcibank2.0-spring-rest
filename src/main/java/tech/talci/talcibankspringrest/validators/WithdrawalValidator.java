package tech.talci.talcibankspringrest.validators;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.talci.talcibankspringrest.api.v1.dto.AccountRequest;
import tech.talci.talcibankspringrest.domain.Account;
import tech.talci.talcibankspringrest.domain.Withdrawal;
import tech.talci.talcibankspringrest.repositories.AccountRepository;

import java.math.BigDecimal;

@Component
public class WithdrawalValidator {

    public boolean isValid(Withdrawal withdrawal, Account account){

        BigDecimal positiveBalance = new BigDecimal("0.0");
        if((account.getBalance().subtract(withdrawal.getAmount())).compareTo(positiveBalance) == 1
        && withdrawal.getAmount().compareTo(positiveBalance) == 1){
            return true;
        }
        return false;
    }
}
