package tech.talci.talcibankspringrest.controllers.v1;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcibankspringrest.api.v1.dto.CardListDTO;
import tech.talci.talcibankspringrest.services.CardService;

@RestController
@RequestMapping("/api/users/{userId}/cards")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public CardListDTO getAllCards(){
        return cardService.findAllDTO();
    }
}
