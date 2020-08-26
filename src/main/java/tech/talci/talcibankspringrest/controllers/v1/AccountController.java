package tech.talci.talcibankspringrest.controllers.v1;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcibankspringrest.api.v1.mapper.AccountMapper;
import tech.talci.talcibankspringrest.api.v1.model.AccountDTO;
import tech.talci.talcibankspringrest.domain.Account;
import tech.talci.talcibankspringrest.repositories.AccountRepository;
import tech.talci.talcibankspringrest.repositories.UserRepository;
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
