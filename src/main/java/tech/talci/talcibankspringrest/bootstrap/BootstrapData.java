package tech.talci.talcibankspringrest.bootstrap;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tech.talci.talcibankspringrest.domain.Account;
import tech.talci.talcibankspringrest.domain.AccountType;
import tech.talci.talcibankspringrest.domain.Currency;
import tech.talci.talcibankspringrest.domain.User;
import tech.talci.talcibankspringrest.repositories.AccountRepository;
import tech.talci.talcibankspringrest.repositories.UserRepository;

import java.math.BigDecimal;
import java.time.Instant;

@Profile("dev")
@AllArgsConstructor
@Component
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0L){
            User user = new User();
            user.setUserId(2L);
            user.setFirstName("Loh");
            user.setLastName("Pidr");
            user.setEnabled(true);

            Account account = new Account();
            account.setUser(user);
            account.setId(2L);
            user.getAccounts().add(account);
            account.setCurrency(Currency.DOLLAR);
            account.setBalance(new BigDecimal("20000.0"));
            account.setNumber(343424234324L);
            account.setAccountType(AccountType.BUSNESS);
            account.setCreatedOn(Instant.now());
            userRepository.save(user);
            accountRepository.save(account);
        }

    }
}
