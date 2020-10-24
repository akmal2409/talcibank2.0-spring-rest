package tech.talci.talcibankspringrest.controllers.v1;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcibankspringrest.api.v1.dto.AuthenticationResponse;
import tech.talci.talcibankspringrest.api.v1.dto.LoginRequest;
import tech.talci.talcibankspringrest.api.v1.dto.RefreshTokenRequest;
import tech.talci.talcibankspringrest.api.v1.dto.RegisterRequest;
import tech.talci.talcibankspringrest.domain.RefreshToken;
import tech.talci.talcibankspringrest.services.implementations.AuthService;
import tech.talci.talcibankspringrest.services.implementations.RefreshTokenService;

import javax.validation.Valid;

@RestController
@RequestMapping(AuthController.BASE_URL)
@AllArgsConstructor
public class AuthController {

    static final String BASE_URL = "/api/auth";
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);

        return new ResponseEntity("User Registration was successful", HttpStatus.CREATED);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> accountVerification(@PathVariable String token){
        authService.verifyAccount(token);

        return new ResponseEntity<>("Account Activate Successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteByToken(refreshTokenRequest.getRefreshToken());
        return new ResponseEntity<>("You were successfully logged out", HttpStatus.OK);
    }
}
