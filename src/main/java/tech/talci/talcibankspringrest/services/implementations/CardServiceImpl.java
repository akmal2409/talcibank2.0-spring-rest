package tech.talci.talcibankspringrest.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.talci.talcibankspringrest.api.v1.dto.CardListDTO;
import tech.talci.talcibankspringrest.api.v1.mapper.CardMapper;
import tech.talci.talcibankspringrest.repositories.CardRepository;
import tech.talci.talcibankspringrest.services.CardService;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    CardRepository cardRepository;
    CardMapper cardMapper = CardMapper.INSTANCE;

    @Override
    public CardListDTO findAllDTO() {
        return new CardListDTO(
                cardRepository.findAll()
                .stream()
                .map(cardMapper::cardToCardDTO)
                .collect(Collectors.toList())
                );
    }
}
