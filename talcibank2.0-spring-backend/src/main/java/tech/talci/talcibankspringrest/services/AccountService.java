package tech.talci.talcibankspringrest.services;

import tech.talci.talcibankspringrest.api.v1.dto.*;
import tech.talci.talcibankspringrest.domain.Account;

import java.util.List;

public interface AccountService{

    AccountDTO findById(Long id);

    AccountDTO createNewAccount(AccountRequest accountRequest);

    AccountListDTO findAllDTO();

    AccountDTO saveAccountDTO(Long id, AccountDTO accountDTO);

    void deleteAccountById(Long id);

    void withdraw(WithdrawalDTO withdrawalDTO, Long accountId);

    void deposit(DepositDTO depositDTO, Long accountId);

    AccountDTO saveAndReturnDTO(Account account);

    void bankTransfer(BankTransferDTO bankTransferDTO, Long accountId);
}
