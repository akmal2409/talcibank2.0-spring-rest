package tech.talci.talcibankspringrest.services;

import tech.talci.talcibankspringrest.api.v1.dto.CardListDTO;
import tech.talci.talcibankspringrest.domain.User;

public interface CardService {

    CardListDTO findAllDTO();
}
