package tech.talci.talcibankspringrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.talci.talcibankspringrest.api.v1.model.CardDTO;
import tech.talci.talcibankspringrest.domain.Card;

@Mapper
public interface CardMapper {

    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardDTO cardToCardDTO(Card card);

    Card cardDTOToCard(CardDTO cardDTO);
}
