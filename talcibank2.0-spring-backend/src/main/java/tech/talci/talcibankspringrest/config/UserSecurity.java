package tech.talci.talcibankspringrest.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import tech.talci.talcibankspringrest.services.UserService;

@Component("userSecurity")
@AllArgsConstructor
public class UserSecurity {

    private final UserService userService;

    public boolean hasUserId(Authentication authentication, Long userId){
        User principal = (User) authentication.getPrincipal();

        tech.talci.talcibankspringrest.domain.User user =
                userService.findById(userId);

        return principal.getUsername().equals(user.getUsername());
    }
}
