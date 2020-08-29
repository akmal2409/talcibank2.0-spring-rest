package tech.talci.talcibankspringrest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.talci.talcibankspringrest.domain.User;
import tech.talci.talcibankspringrest.exceptions.ResourceNotFoundException;
import tech.talci.talcibankspringrest.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with such id not found: "
                + id));
    }
}
