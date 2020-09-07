package tech.talci.talcibankspringrest.services.implementations;


import org.springframework.stereotype.Service;
import tech.talci.talcibankspringrest.api.v1.dto.*;
import tech.talci.talcibankspringrest.api.v1.mapper.AccountMapper;
import tech.talci.talcibankspringrest.api.v1.mapper.DepositMapper;
import tech.talci.talcibankspringrest.api.v1.mapper.WithdrawalMapper;
import tech.talci.talcibankspringrest.domain.*;
import tech.talci.talcibankspringrest.exceptions.MoneyException;
import tech.talci.talcibankspringrest.exceptions.ResourceNotFoundException;
import tech.talci.talcibankspringrest.repositories.*;
import tech.talci.talcibankspringrest.services.AccountService;
import tech.talci.talcibankspringrest.validators.DepositValidator;
import tech.talci.talcibankspringrest.validators.TransferValidator;
import tech.talci.talcibankspringrest.validators.WithdrawalValidator;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
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
    private final BankTransferRepository bankTransferRepository;
    private final DepositRepository depositRepository;
    private final WithdrawalValidator withdrawalValidator;
    private final DepositValidator depositValidator;
    private final TransferValidator transferValidator;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository,
                              WithdrawalRepository withdrawalRepository, DepositRepository depositRepository,
                              WithdrawalValidator withdrawalValidator, DepositValidator depositValidator,
                              TransferValidator transferValidator,
                              BankTransferRepository bankTransferRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.withdrawalRepository = withdrawalRepository;
        this.depositRepository = depositRepository;
        this.withdrawalValidator = withdrawalValidator;
        this.depositValidator = depositValidator;
        this.transferValidator = transferValidator;
        this.bankTransferRepository = bankTransferRepository;
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

    @Transactional
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
    public AccountListDTO findAllDTO(Long userId) {
        return new AccountListDTO(
                accountRepository.findAll()
                        .stream()
                        .filter(account -> account.getUser().getUserId() == userId)
                        .map(accountMapper::accountToAccountDTO)
                        .collect(Collectors.toList())
        );

    }

    @Transactional
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

    @Transactional
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

    @Transactional
    @Override
    public void bankTransfer(BankTransferDTO bankTransferDTO, Long accountId) {
        Account sender = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account (Sender) was not found"));

        BankTransfer bankTransfer = new BankTransfer();
        bankTransfer.setRecipientNumber(Long.valueOf(bankTransferDTO.getRecipientNumber()));
        bankTransfer.setDescription(bankTransferDTO.getDescription());
        bankTransfer.setAmount(bankTransferDTO.getAmount());

        Long accountNumber = bankTransfer.getRecipientNumber();
        Account recipient = accountRepository.findAccountByNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account (Recipient) was not found!"));

        if(transferValidator.validateBankTransfer(sender, bankTransfer.getAmount())){
            BigDecimal newBalanceSender = sender.getBalance().subtract(bankTransfer.getAmount());
            sender.setBalance(newBalanceSender);

            BigDecimal newBalanceRecipient = recipient.getBalance().add(bankTransfer.getAmount());
            recipient.setBalance(newBalanceRecipient);

            bankTransfer.setDate(Instant.now());
            bankTransfer.setRecipientNumber(recipient.getNumber());
            bankTransfer.setSender(sender);

            BankTransfer savedTransfer = bankTransferRepository.save(bankTransfer);

            sender.getBankTransfers().add(savedTransfer);

            accountRepository.save(sender);
            accountRepository.save(recipient);
        } else {
            throw new MoneyException("Transaction was unsuccessful");
        }
    }

    @Override
    public AccountDTO saveAndReturnDTO(Account account){

        Account savedAccount = accountRepository.save(account);

        AccountDTO returnDTO = accountMapper.accountToAccountDTO(savedAccount);

        returnDTO.setId(savedAccount.getId());

        return returnDTO;
    }
}
