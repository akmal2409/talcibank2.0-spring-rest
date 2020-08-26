package tech.talci.talcibankspringrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private String name;
    private Long number;
    private String currency;
    private String accountType;
}
