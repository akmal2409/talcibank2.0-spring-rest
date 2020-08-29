package tech.talci.talcibankspringrest.controllers.v1;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcibankspringrest.api.v1.dto.RegisterRequest;
import tech.talci.talcibankspringrest.services.implementations.AuthService;

@RestController
@RequestMapping(AuthController.BASE_URL)
@AllArgsConstructor
public class AuthController {

    static final String BASE_URL = "/api/auth";
    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
    }

    @PostMapping("/accountVerification/{token}")
    @ResponseStatus(HttpStatus.OK)
    public void accountVerification(@PathVariable String token){
        return;
    }
}
