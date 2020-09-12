package tech.talci.talcibankspringrest.validators;

import tech.talci.talcibankspringrest.domain.Card;

import java.math.BigDecimal;

public class CardWithdrawalValidator {

    public boolean validate(Card card, BigDecimal amount){

        return (card.getBalance().subtract(amount)).compareTo(new BigDecimal("0.0")) == 1 &&
                amount.compareTo(new BigDecimal("0.0")) == 1;
    }
}
