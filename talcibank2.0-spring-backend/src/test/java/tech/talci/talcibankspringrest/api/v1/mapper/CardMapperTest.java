package tech.talci.talcibankspringrest.api.v1.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.talci.talcibankspringrest.api.v1.dto.CardDTO;
import tech.talci.talcibankspringrest.domain.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CardMapperTest {

    CardMapper cardMapper;

    static final Long ID = 2L;
    static final String NAME = "TEST";
    static final Long NUMBER = 23123L;
    static final CardType CARD_TYPE = CardType.AMEX;
    static final Currency CURRENCY = Currency.DOLLAR;
    static final String HOLDERS_NAME = "Test name";
    static final int CVV = 123;
    static final Instant ISSUE_DATE = Instant.now();
    static final Date EXPIRATION_DATE = Date.from(Instant.now());
    static final BigDecimal BALANCE = new BigDecimal(2.3);

    @BeforeEach
    void setUp() {
        cardMapper = CardMapper.INSTANCE;
    }

    @Test
    void cardToCardDTO() {
        //given
        Card cardToConvert = new Card();
        cardToConvert.setBalance(BALANCE);
        cardToConvert.setCardType(CARD_TYPE);
        cardToConvert.setCurrency(CURRENCY);
        cardToConvert.setCvv(CVV);
        cardToConvert.setExpirationDate(EXPIRATION_DATE);
        cardToConvert.setIssueDate(ISSUE_DATE);
        cardToConvert.setHoldersName(HOLDERS_NAME);
        cardToConvert.setNumber(NUMBER);

        //when
        CardDTO convertedCard = cardMapper.cardToCardDTO(cardToConvert);

        //then
        assertNotNull(convertedCard);
        //assertEquals(ID, convertedCard.getId()); Mapstruct doesnt convert ids, will implement manually in services
        assertEquals(HOLDERS_NAME, convertedCard.getHoldersName());
        assertEquals(NUMBER, convertedCard.getNumber());
        assertEquals(CVV, convertedCard.getCvv());
        assertEquals(CURRENCY.toString(), convertedCard.getCurrency());
        assertEquals(CARD_TYPE.toString(), convertedCard.getCardType());
        assertEquals(EXPIRATION_DATE, convertedCard.getExpirationDate());
        assertEquals(ISSUE_DATE, convertedCard.getIssueDate());

    }

    @Test
    void cardDTOToCard() {
        //given
        CardDTO cardToConvert = new CardDTO();
        cardToConvert.setBalance(BALANCE);
        cardToConvert.setCardType(CARD_TYPE.toString());
        cardToConvert.setCurrency(CURRENCY.toString());
        cardToConvert.setCvv(CVV);
        cardToConvert.setExpirationDate(EXPIRATION_DATE);
        cardToConvert.setIssueDate(ISSUE_DATE);
        cardToConvert.setHoldersName(HOLDERS_NAME);
        cardToConvert.setNumber(NUMBER);

        //when
        Card convertedCard = cardMapper.cardDTOToCard(cardToConvert);

        //then
        assertNotNull(convertedCard);
        //assertEquals(ID, convertedCard.getId()); Mapstruct doesnt convert ids, will implement manually in services
        assertEquals(HOLDERS_NAME, convertedCard.getHoldersName());
        assertEquals(NUMBER, convertedCard.getNumber());
        assertEquals(CVV, convertedCard.getCvv());
        assertEquals(CURRENCY, convertedCard.getCurrency());
        assertEquals(CARD_TYPE, convertedCard.getCardType());
        assertEquals(EXPIRATION_DATE, convertedCard.getExpirationDate());
        assertEquals(ISSUE_DATE, convertedCard.getIssueDate());
    }
}