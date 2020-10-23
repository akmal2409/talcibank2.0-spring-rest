package tech.talci.talcibankspringrest.validators;

import org.springframework.stereotype.Component;
import tech.talci.talcibankspringrest.domain.Account;

import java.math.BigDecimal;

@Component
public class TransferValidator {

    public boolean validateBankTransfer(Account sender, BigDecimal amount){

        BigDecimal positiveBalance = new BigDecimal("0.0");

        return sender.getBalance().subtract(amount).compareTo(positiveBalance) == 1
                && amount.compareTo(positiveBalance) == 1;
    }
}
