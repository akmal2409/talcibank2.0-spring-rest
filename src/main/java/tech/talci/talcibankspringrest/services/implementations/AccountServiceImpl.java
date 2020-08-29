package tech.talci.talcibankspringrest.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.talci.talcibankspringrest.api.v1.mapper.AccountMapper;
import tech.talci.talcibankspringrest.api.v1.dto.AccountDTO;
import tech.talci.talcibankspringrest.domain.Account;
import tech.talci.talcibankspringrest.domain.User;
import tech.talci.talcibankspringrest.exceptions.ResourceNotFoundException;
import tech.talci.talcibankspringrest.repositories.AccountRepository;
import tech.talci.talcibankspringrest.repositories.UserRepository;
import tech.talci.talcibankspringrest.services.AccountService;


@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper = AccountMapper.INSTANCE;

    @Override
    public AccountDTO findById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::accountToAccountDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Account Not Found ID: " + id));

    }

    @Override
    public AccountDTO createNewAccount(AccountDTO accountDTO, Long userId){
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found ID: " + userId));

        Account account = accountMapper.accountDTOToAccount(accountDTO);

        account.setUser(user);
        user.getAccounts().add(account);
        userRepository.save(user);

        return saveAndReturnDTO(account);
    }

    @Override
    public AccountDTO saveAccountDTO(Long id, AccountDTO accountDTO) {
        Account account = accountMapper.accountDTOToAccount(accountDTO);
        account.setId(id);

        return saveAndReturnDTO(account);
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    public AccountDTO saveAndReturnDTO(Account account){

        Account savedAccount = accountRepository.save(account);

        AccountDTO returnDTO = accountMapper.accountToAccountDTO(savedAccount);

        returnDTO.setId(savedAccount.getId());

        return returnDTO;
    }
}
