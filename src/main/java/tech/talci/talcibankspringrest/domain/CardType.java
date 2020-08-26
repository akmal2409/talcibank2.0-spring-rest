package tech.talci.talcibankspringrest.domain;

public enum CardType {
    VISA_SILVER("VISA Silver"),
    VISA_GOLD ("VISA Gold"),
    VISA_PLATINUM ("VISA Platinum"),
    MASTERCARD_SILVER("MASTERCARD Silver"),
    MASTERCARD_GOLD("MASTERCARD Gold"),
    MASTERCARD_PLATINUM("MASTERCARD Platinum"),
    AMEX("American Express");

    CardType(String type){

    }
}
