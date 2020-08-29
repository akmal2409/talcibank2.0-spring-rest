package tech.talci.talcibankspringrest.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

    private String username;
    private String password;
}
