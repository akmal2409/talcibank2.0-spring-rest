package tech.talci.talcibankspringrest.controllers.v1;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcibankspringrest.api.v1.dto.AccountDTO;
import tech.talci.talcibankspringrest.services.AccountService;

@RestController
@RequestMapping("/api/{userId}/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccount(@RequestBody AccountDTO accountDTO, @PathVariable Long userId){
        return accountService.createNewAccount(accountDTO, userId);
    }
}
