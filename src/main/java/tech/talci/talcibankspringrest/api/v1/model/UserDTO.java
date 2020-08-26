package tech.talci.talcibankspringrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String image;
    private String phoneNumber;
    private Date birthDate;
    private String secretPhrase;
    private Instant created;
    private String username;
    private String email;
    private boolean enabled;
    private List<CardDTO> cards;
    private List<AccountDTO> accounts;
    private String userUrl;
}
