package tech.talci.talcibankspringrest.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import tech.talci.talcibankspringrest.api.v1.dto.AccountDTO;
import tech.talci.talcibankspringrest.domain.Account;
import tech.talci.talcibankspringrest.repositories.AccountRepository;
import tech.talci.talcibankspringrest.repositories.DepositRepository;
import tech.talci.talcibankspringrest.repositories.UserRepository;
import tech.talci.talcibankspringrest.repositories.WithdrawalRepository;
import tech.talci.talcibankspringrest.services.AccountService;
import tech.talci.talcibankspringrest.validators.DepositValidator;
import tech.talci.talcibankspringrest.validators.WithdrawalValidator;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AccountServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    WithdrawalRepository withdrawalRepository;


    WithdrawalValidator withdrawalValidator;

    DepositValidator depositValidator;

    @Mock
    DepositRepository depositRepository;


    AccountService accountService;

    final String NAME = "TEST";
    final Long NUMBER = 23123L;
    final Long ID = 2L;
    Account account;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        accountService = new AccountServiceImpl(accountRepository, userRepository, withdrawalRepository, depositRepository,
                withdrawalValidator, depositValidator);
        account = new Account();
        account.setId(ID);
        account.setName(NAME);
        account.setNumber(NUMBER);
    }

    @Test
    void findById() {
        //given
        given(accountRepository.findById(ID)).willReturn(Optional.of(account));

        //when
        AccountDTO returnedDTO = accountService.findById(ID);

        assertEquals(ID, returnedDTO.getId());
        assertEquals(NAME, returnedDTO.getName());
        assertEquals(NUMBER, returnedDTO.getNumber());

        verify(accountRepository, times(1)).findById(anyLong());
    }

    @Test
    void createNewAccount() {
    }

    @Test
    void saveAccountDTO() {
    }

    @Test
    void deleteAccountById() {
    }
}