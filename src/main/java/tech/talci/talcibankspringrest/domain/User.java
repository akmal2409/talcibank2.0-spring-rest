package tech.talci.talcibankspringrest.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    private Long userId;

    private String firstName;
    private String lastName;
    private String address;
    // private Passport passport;
    private String image;

    private String phoneNumber;

    private Date birthDate;
    private String secretPhrase;

    private Instant created;
    private String username;
    private String password;
    private String email;
    private boolean enabled;

    private List<Card> cards = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
}
