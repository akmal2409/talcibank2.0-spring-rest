package tech.talci.talcibankspringrest.controllers.v1;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcibankspringrest.api.v1.dto.CardCreateRequest;
import tech.talci.talcibankspringrest.api.v1.dto.CardDTO;
import tech.talci.talcibankspringrest.api.v1.dto.CardListDTO;
import tech.talci.talcibankspringrest.services.CardService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/users/{userId}/cards")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CardListDTO getAllCards(){
        return cardService.findAllDTO();
    }

    @PostMapping
    public CardDTO createCard(@PathVariable Long userId , @RequestBody CardCreateRequest cardCreateRequest){
        return cardService.createCard(userId, cardCreateRequest);
    }

    @PostMapping("/{cardId}/deposit/{amount}")
    public ResponseEntity<String> depositFunds(@PathVariable Long cardId, @PathVariable BigDecimal amount){
        cardService.deposit(cardId, amount);

        return new ResponseEntity<>("Funds were deposited!", HttpStatus.OK);
    }

    @PostMapping("/{cardId}/withdraw/{amount}")
    public ResponseEntity<String> withdrawFunds(@PathVariable Long cardId, @PathVariable BigDecimal amount){
        cardService.withdraw(cardId, amount);

        return new ResponseEntity<>("Withdrawal was successful!", HttpStatus.OK);
    }

    @PostMapping("/{cardId}/transfer/{recipientNumber}/{amount}")
    public ResponseEntity<String> transfer(@PathVariable Long cardId, @PathVariable Long recipientNumber,
                                            @PathVariable BigDecimal amount){
        cardService.transfer(cardId, recipientNumber, amount);

        return new ResponseEntity<>("Transfer was successful", HttpStatus.OK);
    }
}
