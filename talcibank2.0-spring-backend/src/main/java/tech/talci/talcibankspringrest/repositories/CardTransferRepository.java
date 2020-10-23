package tech.talci.talcibankspringrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcibankspringrest.domain.CardTransfer;

import java.util.List;

public interface CardTransferRepository extends JpaRepository<CardTransfer, Long> {
}
