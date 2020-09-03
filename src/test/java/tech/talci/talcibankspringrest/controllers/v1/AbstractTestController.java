package tech.talci.talcibankspringrest.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = "test")
public class AbstractTestController {

    String asJsonString(Object object){
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String jsonString = objectMapper.writeValueAsString(object);
            return jsonString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
