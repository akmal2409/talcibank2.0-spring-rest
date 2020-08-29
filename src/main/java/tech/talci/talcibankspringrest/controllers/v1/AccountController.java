package tech.talci.talcibankspringrest.controllers.v1;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcibankspringrest.api.v1.dto.AccountDTO;
import tech.talci.talcibankspringrest.domain.User;
import tech.talci.talcibankspringrest.services.AccountService;
import tech.talci.talcibankspringrest.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/users/{userId}/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccount(@RequestBody AccountDTO accountDTO, @PathVariable Long userId){
        return accountService.createNewAccount(accountDTO, userId);
    }

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO showAccountById(@PathVariable Long accountId,
            @PathVariable Long userId, Principal principal){

        return accountService.findById(accountId);
    }
}
