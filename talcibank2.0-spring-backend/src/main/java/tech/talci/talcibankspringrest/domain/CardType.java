package tech.talci.talcibankspringrest.domain;

public enum CardType {
    VISA_SILVER("VISA Silver"),
    VISA_GOLD ("VISA Gold"),
    VISA_PLATINUM ("VISA Platinum"),
    MASTERCARD_SILVER("MASTERCARD Silver"),
    MASTERCARD_GOLD("MASTERCARD Gold"),
    MASTERCARD_PLATINUM("MASTERCARD Platinum"),
    AMEX("American Express");

//    private String type;
//
    CardType(String type){
    }
//
//    public String getType() {
//        return this.type;
//    }
//
//    public static CardType fromString(String type) {
//        for (CardType cardType : CardType.values()) {
//            if (cardType.type.equalsIgnoreCase(type)) {
//                return cardType;
//            }
//        }
//        return null;
//    }

}
