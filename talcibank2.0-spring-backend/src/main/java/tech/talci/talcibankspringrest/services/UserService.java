package tech.talci.talcibankspringrest.services;

import tech.talci.talcibankspringrest.domain.User;

public interface UserService {

    User findById(Long id);
}
