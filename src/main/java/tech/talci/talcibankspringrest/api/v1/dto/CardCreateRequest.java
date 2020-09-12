package tech.talci.talcibankspringrest.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import tech.talci.talcibankspringrest.domain.CardType;
import tech.talci.talcibankspringrest.domain.Currency;

@Data
@AllArgsConstructor
public class CardCreateRequest {

    private String holdersName;
    private String cardType;
    private String currency;
}
