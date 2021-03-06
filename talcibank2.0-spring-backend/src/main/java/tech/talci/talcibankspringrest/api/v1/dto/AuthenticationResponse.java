package tech.talci.talcibankspringrest.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    private Instant expiresAt;
    private String refreshToken;
    private String authenticationToken;
    private String username;
}
