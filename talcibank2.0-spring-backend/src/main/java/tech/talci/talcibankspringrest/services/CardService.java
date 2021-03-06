package tech.talci.talcibankspringrest.services;

import org.springframework.web.bind.annotation.PathVariable;
import tech.talci.talcibankspringrest.api.v1.dto.CardCreateRequest;
import tech.talci.talcibankspringrest.api.v1.dto.CardDTO;
import tech.talci.talcibankspringrest.api.v1.dto.CardListDTO;
import tech.talci.talcibankspringrest.api.v1.dto.CardTransferDTO;
import tech.talci.talcibankspringrest.domain.User;

import java.math.BigDecimal;

public interface CardService {

    CardListDTO findAllDTO();

    CardDTO createCard(CardCreateRequest cardCreateRequest);

    void deposit(Long cardId, BigDecimal amount);

    void withdraw(Long cardId, BigDecimal amount);

    void transfer(Long senderCardId, CardTransferDTO cardTransferDTO);

    void deleteById(Long cardId);
}
