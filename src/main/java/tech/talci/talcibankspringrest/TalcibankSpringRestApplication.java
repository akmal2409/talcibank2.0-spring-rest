package tech.talci.talcibankspringrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TalcibankSpringRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalcibankSpringRestApplication.class, args);
    }

}
