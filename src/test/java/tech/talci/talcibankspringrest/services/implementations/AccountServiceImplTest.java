package tech.talci.talcibankspringrest.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.talci.talcibankspringrest.api.v1.model.AccountDTO;
import tech.talci.talcibankspringrest.domain.Account;
import tech.talci.talcibankspringrest.repositories.AccountRepository;
import tech.talci.talcibankspringrest.repositories.UserRepository;
import tech.talci.talcibankspringrest.services.AccountService;

import javax.print.attribute.standard.MediaSize;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AccountServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    AccountRepository accountRepository;

    AccountService accountService;

    final String NAME = "TEST";
    final Long NUMBER = 23123L;
    final Long ID = 2L;
    Account account;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        accountService = new AccountServiceImpl(accountRepository, userRepository);
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