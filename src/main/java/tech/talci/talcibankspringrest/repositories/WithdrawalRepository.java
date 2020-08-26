package tech.talci.talcibankspringrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcibankspringrest.domain.Withdrawal;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
}
