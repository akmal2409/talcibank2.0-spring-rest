package tech.talci.talcibankspringrest.controllers.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.talci.talcibankspringrest.api.v1.dto.RegisterRequest;
import tech.talci.talcibankspringrest.services.implementations.AuthService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(profiles = "test")
class AuthControllerTest extends AbstractTestController{

    @Mock
    AuthService authService;

    @InjectMocks
    AuthController authController;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void signup() throws Exception{

        mockMvc.perform(post(AuthController.BASE_URL + "/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new RegisterRequest())))
                .andExpect(status().isCreated());
    }

    @Test
    void accountVerification() {
    }
}