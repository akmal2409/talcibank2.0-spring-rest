package tech.talci.talcibankspringrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcibankspringrest.domain.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByNumber(Long number);
}
