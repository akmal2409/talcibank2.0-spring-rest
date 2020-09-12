package tech.talci.talcibankspringrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcibankspringrest.domain.Card;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByNumber(Long number);
}
