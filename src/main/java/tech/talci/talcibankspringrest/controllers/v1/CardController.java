package tech.talci.talcibankspringrest.controllers.v1;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcibankspringrest.api.v1.dto.CardCreateRequest;
import tech.talci.talcibankspringrest.api.v1.dto.CardDTO;
import tech.talci.talcibankspringrest.api.v1.dto.CardListDTO;
import tech.talci.talcibankspringrest.services.CardService;

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
}
