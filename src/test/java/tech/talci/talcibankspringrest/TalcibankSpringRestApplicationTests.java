package tech.talci.talcibankspringrest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class TalcibankSpringRestApplicationTests {

    @Test
    void contextLoads() {
    }

}
