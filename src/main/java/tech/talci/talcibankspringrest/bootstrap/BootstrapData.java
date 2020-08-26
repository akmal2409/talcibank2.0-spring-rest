package tech.talci.talcibankspringrest.bootstrap;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tech.talci.talcibankspringrest.domain.User;
import tech.talci.talcibankspringrest.repositories.UserRepository;

@Profile("dev")
@AllArgsConstructor
@Component
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0L){
            User user = new User();
            user.setFirstName("Loh");
            user.setLastName("Pidr");
            user.setEnabled(true);

            userRepository.save(user);
        }
    }
}
