package tech.talci.talcibankspringrest.controllers.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.talci.talcibankspringrest.api.v1.dto.AccountDTO;
import tech.talci.talcibankspringrest.api.v1.dto.AccountListDTO;
import tech.talci.talcibankspringrest.api.v1.dto.AccountRequest;
import tech.talci.talcibankspringrest.domain.AccountType;
import tech.talci.talcibankspringrest.domain.Currency;
import tech.talci.talcibankspringrest.repositories.AccountRepository;
import tech.talci.talcibankspringrest.services.AccountService;

import java.time.Instant;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(profiles = "test")
class AccountControllerTest extends AbstractTestController{

    static final String BASE_URL = "/api/users/1/account";
    static final Long NUMBER = 243213243L;
    static final String NAME = "Test";
    static final Long ID = 1L;
    static final String CURRENCY_STRING = "EUR";
    static final String ACCOUNT_TYPE_STRING = "PERSONAL";

    @Mock
    AccountService accountService;

    @InjectMocks
    AccountController accountController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(RestResponseEntityExceptionHandler.class)
                .build();
    }

    @Test
    void createAccount() throws Exception{
        //given
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountType(ACCOUNT_TYPE_STRING);
        accountRequest.setCurrency(CURRENCY_STRING);
        accountRequest.setName(NAME);

        AccountDTO returnDTO = new AccountDTO();
        returnDTO.setAccountType(ACCOUNT_TYPE_STRING);
        returnDTO.setCreatedOn(Instant.now());
        returnDTO.setId(ID);
        returnDTO.setName(NAME);
        returnDTO.setNumber(NUMBER);

        given(accountService.createNewAccount(accountRequest, 1L)).willReturn(returnDTO);

        //when&then
        mockMvc.perform(post(BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .contentType(asJsonString(accountRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.number", equalTo(NUMBER)))
                .andExpect(jsonPath("$.name", equalTo(NAME)));
        verify(accountService, times(1)).createNewAccount(any(), any());
    }

    @Test
    void listAllAccount() throws Exception{
        //given
        AccountListDTO accountListDTO = new AccountListDTO(Arrays.asList(new AccountDTO(), new AccountDTO()));

        given(accountService.findAllDTO()).willReturn(accountListDTO);

        //when&then
        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accounts", hasSize(2)));

          verify(accountService, times(1)).findAllDTO();
    }
}