package tech.talci.talcibankspringrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.talci.talcibankspringrest.api.v1.dto.WithdrawalDTO;
import tech.talci.talcibankspringrest.domain.Withdrawal;

@Mapper
public interface WithdrawalMapper {

    WithdrawalMapper INSTANCE = Mappers.getMapper(WithdrawalMapper.class);

    WithdrawalDTO withdrawalToWithdrawalDTO(Withdrawal withdrawal);

    Withdrawal withdrawalDTOToWithdrawal(WithdrawalDTO withdrawalDTO);
}
