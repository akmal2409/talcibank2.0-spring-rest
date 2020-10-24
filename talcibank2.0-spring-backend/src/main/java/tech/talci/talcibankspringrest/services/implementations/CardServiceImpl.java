package tech.talci.talcibankspringrest.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.talci.talcibankspringrest.api.v1.dto.CardCreateRequest;
import tech.talci.talcibankspringrest.api.v1.dto.CardDTO;
import tech.talci.talcibankspringrest.api.v1.dto.CardListDTO;
import tech.talci.talcibankspringrest.api.v1.dto.CardTransferDTO;
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
    private final AuthService authService;

    private static Long cardNumber = 4023060102037654L;
    private static Integer cvv = 111;

    @Override
    public CardListDTO findAllDTO() {
        User user = getCurrentUser();
        return new CardListDTO(
                cardRepository.findAll()
                .stream()
                .filter(card -> card.getUser().getUserId() == user.getUserId())
                .map(cardMapper::cardToCardDTO)
                .collect(Collectors.toList()));
    }

    @Override
    public CardDTO createCard(CardCreateRequest cardCreateRequest) {

        User user = getCurrentUser();

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

    private User getCurrentUser() {
        return userRepository.findByUsername(authService.getCurrentUser().getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User was not found! User:" +
                        authService.getCurrentUser().getUsername()));
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

        User user = getCurrentUser();

        if(user.getUserId() == requestedCard.getUser().getUserId()) {
            if(cardDepositValidator.validate(requestedCard, amount)){
                BigDecimal newBalance = requestedCard.getBalance().add(amount);

                requestedCard.setBalance(newBalance);
                cardRepository.save(requestedCard);
            } else {
                throw new MoneyException("Deposit was unsuccessful");
            }
        } else {
            throw new MoneyException("Deposit was unsuccessful! User/Card not found!");
        }
    }

    @Override
    public void withdraw(Long cardId, BigDecimal amount) {

        Card requestedCard = cardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card was not found"));

        User user = getCurrentUser();

        if(user.getUserId() == requestedCard.getUser().getUserId()) {
            if(cardWithdrawalValidator.validate(requestedCard, amount)){
                BigDecimal newBalance = requestedCard.getBalance().subtract(amount);

                requestedCard.setBalance(newBalance);
                cardRepository.save(requestedCard);
            } else {
                throw new MoneyException("Withdrawal was unsuccessful!");
            }
        } else {
            throw new MoneyException("Withdrawal was unsuccessful! User/card not found! ");
        }
    }

    @Override
    public void transfer(Long senderCardId, CardTransferDTO cardTransferDTO) {

        Card sender = cardRepository.findById(senderCardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card was not found!"));

        User user = getCurrentUser();

        if(sender.getUser().getUserId() == user.getUserId()) {
            Card recipient = cardRepository.findByNumber(Long.valueOf(cardTransferDTO.getRecipient()))
                    .orElseThrow(() -> new ResourceNotFoundException("Recipient was not found!"));

            if(cardTransferValidator.validate(sender, cardTransferDTO.getAmount())){
                BigDecimal sendersBalance = sender.getBalance().subtract(cardTransferDTO.getAmount());
                BigDecimal recipientsBalance = recipient.getBalance().add(cardTransferDTO.getAmount());

                CardTransfer cardTransfer = new CardTransfer();
                cardTransfer.setAmount(cardTransferDTO.getAmount());
                cardTransfer.setCard(sender);
                cardTransfer.setDate(Instant.now());
                cardTransfer.setRecipientCardNumber(cardTransferDTO.getRecipient());

                CardTransfer savedTransfer = cardTransferRepository.save(cardTransfer);
                sender.getCardTransfers().add(savedTransfer);
                sender.setBalance(sendersBalance);
                recipient.setBalance(recipientsBalance);

                cardRepository.save(sender);
                cardRepository.save(recipient);
            } else {
                throw new MoneyException("Transfer was unsuccessful");
            }
        } else {
            throw  new MoneyException("Transfer was unsuccessful! User/card not found");
        }

    }
}
