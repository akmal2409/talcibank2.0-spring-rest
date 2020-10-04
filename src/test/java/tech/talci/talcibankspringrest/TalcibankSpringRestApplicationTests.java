package tech.talci.talcibankspringrest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@TestPropertySource(locations="classpath:application-dev.properties")
class TalcibankSpringRestApplicationTests {

    @Test
    void contextLoads() {
    }

}
