package tech.talci.talcibankspringrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import tech.talci.talcibankspringrest.config.SwaggerConfiguration;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class TalcibankSpringRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalcibankSpringRestApplication.class, args);
    }

}
