package tech.talci.talcibankspringrest.controllers.v1;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcibankspringrest.api.v1.dto.*;
import tech.talci.talcibankspringrest.services.AccountService;


@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccount(@RequestBody AccountRequest accountRequest){
        return accountService.createNewAccount(accountRequest);
    }

    @PostMapping("/{accountId}/deposit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> depositFunds(@RequestBody DepositDTO depositDTO, @PathVariable Long accountId){
        accountService.deposit(depositDTO, accountId);

        return new ResponseEntity<>("Deposit was successful", HttpStatus.OK);
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<String> withdrawFunds(@RequestBody WithdrawalDTO withdrawalDTO, @PathVariable Long accountId){
        accountService.withdraw(withdrawalDTO, accountId);

        return new ResponseEntity<>("Withdrawal was successful", HttpStatus.OK);
    }

    @PostMapping("/{accountId}/bankTransfer")
    public ResponseEntity<String> transferFunds(@RequestBody BankTransferDTO bankTransferDTO,
                                                @PathVariable Long accountId){
        accountService.bankTransfer(bankTransferDTO, accountId);

        return new ResponseEntity<>("Transfer was successful", HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO findById(@PathVariable Long accountId){
        return accountService.findById(accountId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountListDTO listAllAccounts(){
        return accountService.findAllDTO();
    }

    @DeleteMapping("/{accountId]/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccountById(accountId);

        return new ResponseEntity<>("Account was deleted", HttpStatus.OK);
    }
}
