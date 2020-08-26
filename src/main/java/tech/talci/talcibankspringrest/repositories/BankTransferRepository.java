package tech.talci.talcibankspringrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcibankspringrest.domain.Account;
import tech.talci.talcibankspringrest.domain.BankTransfer;

import java.util.Date;
import java.util.List;

public interface BankTransferRepository extends JpaRepository<BankTransfer, Long> {
}
