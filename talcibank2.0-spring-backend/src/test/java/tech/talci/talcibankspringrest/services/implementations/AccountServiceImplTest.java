package tech.talci.talcibankspringrest.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import tech.talci.talcibankspringrest.api.v1.dto.AccountDTO;
import tech.talci.talcibankspringrest.api.v1.dto.AccountRequest;
import tech.talci.talcibankspringrest.domain.Account;
import tech.talci.talcibankspringrest.domain.AccountType;
import tech.talci.talcibankspringrest.domain.Currency;
import tech.talci.talcibankspringrest.domain.User;
import tech.talci.talcibankspringrest.repositories.*;
import tech.talci.talcibankspringrest.services.AccountService;
import tech.talci.talcibankspringrest.validators.DepositValidator;
import tech.talci.talcibankspringrest.validators.TransferValidator;
import tech.talci.talcibankspringrest.validators.WithdrawalValidator;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    TransferValidator transferValidator;

    @Mock
    DepositRepository depositRepository;

    @Mock
    BankTransferRepository bankTransferRepository;

    @Mock
    AuthService authService;

    AccountService accountService;

    final String NAME = "TEST";
    final Long NUMBER = 23123L;
    final Currency CURRENCY = Currency.DOLLAR;
    final AccountType ACCOUNT_TYPE = AccountType.BUSINESS;
    final Long ID = 2L;
    Account account;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        accountService = new AccountServiceImpl(accountRepository, userRepository, withdrawalRepository, depositRepository,
                withdrawalValidator, depositValidator, transferValidator, bankTransferRepository, authService);
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

    //todo: write an integration test for the method below
    /*
    @Test
    void createNewAccount() {
        //given
        AccountDTO accountRequest = new Account();
        accountRequest.setName(NAME);
        accountRequest.setAccountType(ACCOUNT_TYPE.toString());
        accountRequest.setCurrency(CURRENCY.toString());

        User user = new User();
        user.setUserId(ID);

        given(userRepository.findById(ID)).willReturn(Optional.of(user));
        given(accountService.saveAccountDTO(any(Account.class))).willReturn(accountRequest)

        //when
        AccountDTO returnDto = accountService.createNewAccount(accountRequest, ID);

        //then
        assertNotNull(returnDto);
        assertEquals(NAME, returnDto.getName());
        assertEquals(CURRENCY.toString(), returnDto.getCurrency());
        assertEquals(ACCOUNT_TYPE, returnDto.getAccountType());
    }
*/
    @Test
    void saveAndReturnDto() {
        //given
        Account account = new Account();
        account.setId(ID);
        account.setName(NAME);
        account.setNumber(NUMBER);

        given(accountRepository.save(any())).willReturn(account);

        //when
        AccountDTO accountDTO = accountService.saveAndReturnDTO(new Account());

        //then
        assertNotNull(accountDTO);
        assertEquals(ID, account.getId());
        assertEquals(NUMBER, account.getNumber());
        assertEquals(NAME, account.getName());
    }
}