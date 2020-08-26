package tech.talci.talcibankspringrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.talci.talcibankspringrest.api.v1.model.AccountDTO;
import tech.talci.talcibankspringrest.domain.Account;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO accountToAccountDTO(Account account);

    Account accountDTOToAccount(AccountDTO accountDTO);
}
