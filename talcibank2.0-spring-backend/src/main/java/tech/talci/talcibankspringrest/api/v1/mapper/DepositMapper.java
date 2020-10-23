package tech.talci.talcibankspringrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.talci.talcibankspringrest.api.v1.dto.DepositDTO;
import tech.talci.talcibankspringrest.domain.Deposit;

@Mapper
public interface DepositMapper {

    DepositMapper INSTANCE = Mappers.getMapper(DepositMapper.class);

    DepositDTO depositToDepositDTO(Deposit deposit);

    Deposit depositDToToDeposit(DepositDTO depositDTO);
}
