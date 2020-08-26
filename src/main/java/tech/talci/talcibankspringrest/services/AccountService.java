package tech.talci.talcibankspringrest.services;

import tech.talci.talcibankspringrest.api.v1.dto.AccountDTO;

public interface AccountService{

    AccountDTO findById(Long id);

    AccountDTO createNewAccount(AccountDTO accountDTO, Long userId);

    AccountDTO saveAccountDTO(Long id, AccountDTO accountDTO);

    void deleteAccountById(Long id);
}
