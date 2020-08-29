package tech.talci.talcibankspringrest.services;

import tech.talci.talcibankspringrest.api.v1.dto.RegisterRequest;

public interface AuthService {

    void signup(RegisterRequest registerRequest);
}
