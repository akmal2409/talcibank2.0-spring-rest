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

@RestController
@RequestMapping(AccountController.BASE_URL)
@AllArgsConstructor
public class AccountController {

    static final String BASE_URL = "/api/v1/account";

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccount(@RequestBody AccountDTO accountDTO, @PathVariable Long userId){
        return null;
    }
}
