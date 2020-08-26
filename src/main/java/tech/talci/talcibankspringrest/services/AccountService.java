package tech.talci.talcibankspringrest.services;

import tech.talci.talcibankspringrest.api.v1.model.AccountDTO;
import tech.talci.talcibankspringrest.domain.Account;

import java.util.List;

public interface AccountService{

    AccountDTO findById(Long id);

    AccountDTO createNewAccount(AccountDTO accountDTO, Long userId);

    AccountDTO saveAccountDTO(Long id, AccountDTO accountDTO);

    void deleteAccountById(Long id);
}
