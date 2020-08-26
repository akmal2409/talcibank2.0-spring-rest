package tech.talci.talcibankspringrest.api.v1.mapper;

import com.sun.mail.imap.protocol.UIDSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.talci.talcibankspringrest.api.v1.model.AccountDTO;
import tech.talci.talcibankspringrest.domain.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {

    AccountMapper accountMapper;
    Account account;
    AccountDTO accountDTO;

    static final Long ID = 2L;
    static final String NAME = "TEST";
    static final Long NUMBER = 23123L;
    static final Long USER_ID = 1L;
    static final AccountType ACCOUNT_TYPE = AccountType.PERSONAL;
    static final Currency CURRENCY = Currency.DOLLAR;
    static final BigDecimal BALANCE = new BigDecimal(2.3);

    @BeforeEach
    void setUp() {
        accountMapper = AccountMapper.INSTANCE;
    }

    @Test
    void accountToAccountDTO() {
        //given
        Account account = new Account();
        account.setId(ID);
        account.setAccountType(ACCOUNT_TYPE);
        account.setName(NAME);
        account.setNumber(NUMBER);
        account.setBalance(BALANCE);
        account.setCurrency(CURRENCY);
        User user = new User();
        user.setUserId(USER_ID);
        account.setUser(user);
        account.getBankTransfers().add(new BankTransfer());
        account.getWithdrawals().add(new Withdrawal());
        account.getDeposits().add(new Deposit());

        //when
        AccountDTO accountDTO = accountMapper.accountToAccountDTO(account);

        //then
        assertNotNull(accountDTO);
        assertEquals(ID, accountDTO.getId());
        assertEquals(ACCOUNT_TYPE.toString(), accountDTO.getAccountType());
        assertEquals(NAME, accountDTO.getName());
        assertEquals(NUMBER, accountDTO.getNumber());
        assertEquals(CURRENCY.toString(), accountDTO.getCurrency());
        assertEquals(BALANCE, accountDTO.getBalance());
        assertEquals(1, accountDTO.getBankTransfers().size());
        assertEquals(1, accountDTO.getWithdrawals().size());
        assertEquals(1, accountDTO.getDeposits().size());
    }

    @Test
    void accountDTOToAccount() {
        //given
        AccountDTO account = new AccountDTO();
        account.setId(ID);
        account.setAccountType(ACCOUNT_TYPE.toString());
        account.setName(NAME);
        account.setNumber(NUMBER);
        account.setBalance(BALANCE);
        account.setCurrency(CURRENCY.toString());
        account.getBankTransfers().add(new BankTransfer());
        account.getWithdrawals().add(new Withdrawal());
        account.getDeposits().add(new Deposit());

        //when
        Account convertedAccount = accountMapper.accountDTOToAccount(account);

        //then
        assertNotNull(convertedAccount);
        assertEquals(ID, convertedAccount.getId());
        assertEquals(ACCOUNT_TYPE, convertedAccount.getAccountType());
        assertEquals(NAME, convertedAccount.getName());
        assertEquals(NUMBER, convertedAccount.getNumber());
        assertEquals(CURRENCY, convertedAccount.getCurrency());
        assertEquals(BALANCE, convertedAccount.getBalance());
        assertEquals(1, convertedAccount.getBankTransfers().size());
        assertEquals(1, convertedAccount.getWithdrawals().size());
        assertEquals(1, convertedAccount.getDeposits().size());
    }
}