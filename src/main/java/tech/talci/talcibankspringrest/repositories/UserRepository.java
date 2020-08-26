package tech.talci.talcibankspringrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcibankspringrest.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
