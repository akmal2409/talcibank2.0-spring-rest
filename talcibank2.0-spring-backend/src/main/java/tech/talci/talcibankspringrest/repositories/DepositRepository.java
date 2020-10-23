package tech.talci.talcibankspringrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcibankspringrest.domain.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
