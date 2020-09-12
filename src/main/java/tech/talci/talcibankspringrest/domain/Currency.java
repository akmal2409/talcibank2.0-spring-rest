package tech.talci.talcibankspringrest.domain;


public enum Currency {
    EURO("EUR"), DOLLAR("USD"), RUBBLE("Rub");

//    private String currencyName;
//
    Currency(String currencyName){
    }
//
//    public String getCurrency() {
//        return this.currencyName;
//    }
//
//    public static Currency fromString(String currencyName){
//        for(Currency currency: Currency.values()){
//            if(currency.currencyName.equalsIgnoreCase(currencyName)){
//                return currency;
//            }
//        }
//        return null;
//    }
}
