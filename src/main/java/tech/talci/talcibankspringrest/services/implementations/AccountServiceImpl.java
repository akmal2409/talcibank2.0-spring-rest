package tech.talci.talcibankspringrest.services.implementations;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.With;
import org.springframework.stereotype.Service;
import tech.talci.talcibankspringrest.api.v1.dto.*;
import tech.talci.talcibankspringrest.api.v1.mapper.AccountMapper;
import tech.talci.talcibankspringrest.api.v1.mapper.DepositMapper;
import tech.talci.talcibankspringrest.api.v1.mapper.WithdrawalMapper;
import tech.talci.talcibankspringrest.domain.*;
import tech.talci.talcibankspringrest.exceptions.MoneyException;
import tech.talci.talcibankspringrest.exceptions.ResourceNotFoundException;
import tech.talci.talcibankspringrest.repositories.AccountRepository;
import tech.talci.talcibankspringrest.repositories.DepositRepository;
import tech.talci.talcibankspringrest.repositories.UserRepository;
import tech.talci.talcibankspringrest.repositories.WithdrawalRepository;
import tech.talci.talcibankspringrest.services.AccountService;
import tech.talci.talcibankspringrest.validators.DepositValidator;
import tech.talci.talcibankspringrest.validators.WithdrawalValidator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper = AccountMapper.INSTANCE;
    private Long generatedAccountNumber = 200000000L;
    private final WithdrawalMapper withdrawalMapper = WithdrawalMapper.INSTANCE;
    private final DepositMapper depositMapper = DepositMapper.INSTANCE;
    private final WithdrawalRepository withdrawalRepository;
    private final DepositRepository depositRepository;
    private final WithdrawalValidator withdrawalValidator;
    private final DepositValidator depositValidator;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository,
                              WithdrawalRepository withdrawalRepository, DepositRepository depositRepository,
                              WithdrawalValidator withdrawalValidator, DepositValidator depositValidator) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.withdrawalRepository = withdrawalRepository;
        this.depositRepository = depositRepository;
        this.withdrawalValidator = withdrawalValidator;
        this.depositValidator = depositValidator;
    }

    @Override
    public AccountDTO findById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::accountToAccountDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Account Not Found ID: " + id));

    }

    @Override
    public AccountDTO createNewAccount(AccountRequest accountRequest, Long userId){
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found ID: " + userId));

        Account account = new Account();
        account.setUser(user);
        user.getAccounts().add(account);

        account.setName(accountRequest.getName());
        account.setCurrency(Currency.valueOf(accountRequest.getCurrency()));
        account.setAccountType(AccountType.valueOf(accountRequest.getAccountType()));
        account.setNumber(generatedAccountNumber);
        account.setCreatedOn(Instant.now());
        account.setBalance(new BigDecimal("0.0"));

        generatedAccountNumber++;

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

    @Override
    public AccountListDTO findAllDTO() {
        return new AccountListDTO(
                accountRepository.findAll()
                        .stream()
                        .map(accountMapper::accountToAccountDTO)
                        .collect(Collectors.toList())
        );

    }

    @Override
    public void withdraw(WithdrawalDTO withdrawalDTO, Long accountId) {

        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new ResourceNotFoundException("Account not found: " + accountId));

        Withdrawal withdrawal = withdrawalMapper.withdrawalDTOToWithdrawal(withdrawalDTO);

        if(withdrawalValidator.isValid(withdrawal, account)){
            BigDecimal newBalance = account.getBalance().subtract(withdrawal.getAmount());
            account.setBalance(newBalance);
            withdrawal.setAccount(account);
            Withdrawal savedWithdrawal = withdrawalRepository.save(withdrawal);
            account.getWithdrawals().add(savedWithdrawal);
            saveAndReturnDTO(account);
        } else {
            throw new MoneyException("Withdrawal was unsuccessful!");
        }
    }

    @Override
    public void deposit(DepositDTO depositDTO, Long accountId) {
        Account account = accountRepository.findById(Long.valueOf(accountId)).orElseThrow(() ->
                new ResourceNotFoundException("Account not found: " + accountId));

        Deposit deposit = depositMapper.depositDToToDeposit(depositDTO);

        if(depositValidator.isValid(deposit, account)){
            BigDecimal newBalance = account.getBalance().add(deposit.getAmount());
            account.setBalance(newBalance);
            deposit.setAccount(account);
            deposit.setDate(Instant.now());
            Deposit savedDeposit = depositRepository.save(deposit);
            account.getDeposits().add(savedDeposit);
            saveAndReturnDTO(account);
        } else {
            throw new MoneyException("Deposit was unsuccessful!");
        }
    }

    public AccountDTO saveAndReturnDTO(Account account){

        Account savedAccount = accountRepository.save(account);

        AccountDTO returnDTO = accountMapper.accountToAccountDTO(savedAccount);

        returnDTO.setId(savedAccount.getId());

        return returnDTO;
    }
}
