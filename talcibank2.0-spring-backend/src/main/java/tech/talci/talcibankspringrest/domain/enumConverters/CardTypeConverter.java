package tech.talci.talcibankspringrest.domain.enumConverters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tech.talci.talcibankspringrest.domain.CardType;

@Component
public class CardTypeConverter implements Converter<String, CardType> {

    @Override
    public CardType convert(String type) {
        return CardType.valueOf(String.valueOf(type));
    }
}
