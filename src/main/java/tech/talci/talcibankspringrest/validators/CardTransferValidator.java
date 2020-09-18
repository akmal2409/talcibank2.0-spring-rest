package tech.talci.talcibankspringrest.validators;

import org.springframework.stereotype.Component;
import tech.talci.talcibankspringrest.domain.Card;

import java.math.BigDecimal;

@Component
public class CardTransferValidator {

    BigDecimal positive = new BigDecimal("0.0");

    public boolean validate(Card sender, BigDecimal amount){

//        return (sender.getBalance().subtract(amount)).compareTo(positive) == 1 &&
//                amount.compareTo(positive) == 1;
        return true;
    }
}
