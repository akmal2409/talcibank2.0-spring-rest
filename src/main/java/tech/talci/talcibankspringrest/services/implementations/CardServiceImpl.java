package tech.talci.talcibankspringrest.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.talci.talcibankspringrest.api.v1.dto.CardCreateRequest;
import tech.talci.talcibankspringrest.api.v1.dto.CardDTO;
import tech.talci.talcibankspringrest.api.v1.dto.CardListDTO;
import tech.talci.talcibankspringrest.api.v1.mapper.CardMapper;
import tech.talci.talcibankspringrest.domain.*;
import tech.talci.talcibankspringrest.domain.enumConverters.CardTypeConverter;
import tech.talci.talcibankspringrest.exceptions.MoneyException;
import tech.talci.talcibankspringrest.exceptions.ResourceNotFoundException;
import tech.talci.talcibankspringrest.repositories.CardRepository;
import tech.talci.talcibankspringrest.repositories.CardTransferRepository;
import tech.talci.talcibankspringrest.repositories.UserRepository;
import tech.talci.talcibankspringrest.services.CardService;
import tech.talci.talcibankspringrest.validators.CardDepositValidator;
import tech.talci.talcibankspringrest.validators.CardTransferValidator;
import tech.talci.talcibankspringrest.validators.CardWithdrawalValidator;

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
    private final CardDepositValidator cardDepositValidator;
    private final CardWithdrawalValidator cardWithdrawalValidator;
    private final CardTransferValidator cardTransferValidator;
    private final CardTransferRepository cardTransferRepository;

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

    @Override
    public void deposit(Long cardId, BigDecimal amount) {

        Card requestedCard = cardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card was not found!"));

        if(cardDepositValidator.validate(requestedCard, amount)){
            BigDecimal newBalance = requestedCard.getBalance().add(amount);

            requestedCard.setBalance(newBalance);
            cardRepository.save(requestedCard);
        } else {
            throw new MoneyException("Deposit was unsuccessful");
        }
    }

    @Override
    public void withdraw(Long cardId, BigDecimal amount) {

        Card requestedCard = cardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card was not found"));

        if(cardWithdrawalValidator.validate(requestedCard, amount)){
            BigDecimal newBalance = requestedCard.getBalance().subtract(amount);

            requestedCard.setBalance(newBalance);
            cardRepository.save(requestedCard);
        } else {
            throw new MoneyException("Withdrawal was unsuccessful!");
        }
    }

    @Override
    public void transfer(Long senderCardId, Long recipientCardNumber, BigDecimal amount) {

        Card sender = cardRepository.findById(senderCardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card was not found!"));

        Card recipient = cardRepository.findByNumberContaining(recipientCardNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Recipient was not found!"));

        if(cardTransferValidator.validate(sender, amount)){
            BigDecimal sendersBalance = sender.getBalance().subtract(amount);
            BigDecimal recipientsBalance = recipient.getBalance().add(amount);

            CardTransfer cardTransfer = new CardTransfer();
            cardTransfer.setAmount(amount);
            cardTransfer.setCard(sender);
            cardTransfer.setDate(Instant.now());
            cardTransfer.setRecipientCardNumber(recipientCardNumber);

            sender.setBalance(sendersBalance);
            recipient.setBalance(recipientsBalance);

            cardRepository.save(sender);
            cardRepository.save(recipient);
            cardTransferRepository.save(cardTransfer);
        } else {
            throw new MoneyException("Transfer was unsuccessful");
        }

    }
}
