package tech.talci.talcibankspringrest.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.talci.talcibankspringrest.api.v1.dto.CardCreateRequest;
import tech.talci.talcibankspringrest.api.v1.dto.CardDTO;
import tech.talci.talcibankspringrest.api.v1.dto.CardListDTO;
import tech.talci.talcibankspringrest.api.v1.mapper.CardMapper;
import tech.talci.talcibankspringrest.domain.Card;
import tech.talci.talcibankspringrest.domain.CardType;
import tech.talci.talcibankspringrest.domain.Currency;
import tech.talci.talcibankspringrest.domain.User;
import tech.talci.talcibankspringrest.domain.enumConverters.CardTypeConverter;
import tech.talci.talcibankspringrest.exceptions.ResourceNotFoundException;
import tech.talci.talcibankspringrest.repositories.CardRepository;
import tech.talci.talcibankspringrest.repositories.UserRepository;
import tech.talci.talcibankspringrest.services.CardService;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final CardMapper cardMapper = CardMapper.INSTANCE;
    private final CardTypeConverter cardTypeConverter;

    private static Long cardNumber = 4023060102037654L;
    private static Integer cvv = 111;

    @Override
    public CardListDTO findAllDTO() {
        return new CardListDTO(
                cardRepository.findAll()
                .stream()
                .map(cardMapper::cardToCardDTO)
                .collect(Collectors.toList())
                );
    }

    @Override
    public CardDTO createCard(Long userId, CardCreateRequest cardCreateRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        Card createdCard = new Card();
        createdCard.setNumber(cardNumber);
        cardNumber++;
        createdCard.setHoldersName(cardCreateRequest.getHoldersName());
        createdCard.setIssueDate(Instant.now());
        createdCard.setExpirationDate(Date.valueOf(LocalDate.now().plusYears(4)));
        createdCard.setCvv(cvv);
        cvv++;
        createdCard.setUser(user);
        createdCard.setBalance(new BigDecimal("0.0"));
        createdCard.setCardType(CardType.valueOf(cardCreateRequest.getCardType()));
        Currency currency = Currency.valueOf(cardCreateRequest.getCurrency());
        createdCard.setCurrency(currency);

        return saveAndReturnDTO(createdCard);
    }

    private CardDTO saveAndReturnDTO(Card card){
        Card savedCard = cardRepository.save(card);
        CardDTO returnDTO = cardMapper.cardToCardDTO(card);

        returnDTO.setId(savedCard.getId());
        return returnDTO;
    }
}
