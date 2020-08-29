package tech.talci.talcibankspringrest.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

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
