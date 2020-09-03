package tech.talci.talcibankspringrest.api.v1.mapper;

import org.junit.jupiter.api.Test;
import tech.talci.talcibankspringrest.api.v1.dto.WithdrawalDTO;
import tech.talci.talcibankspringrest.domain.Withdrawal;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawalMapperTest {

    static final BigDecimal AMOUNT = new BigDecimal("20.0");
    static final Instant DATE = Instant.now();

    WithdrawalMapper mapper = WithdrawalMapper.INSTANCE;

    @Test
    void withdrawalToWithdrawalDTO() {
        //given
        Withdrawal toConvert = new Withdrawal();
        toConvert.setAmount(AMOUNT);

        //when
        WithdrawalDTO convertedReturned = mapper.withdrawalToWithdrawalDTO(toConvert);

        //then
        assertNotNull(convertedReturned);
        assertEquals(AMOUNT, convertedReturned.getAmount());
    }

    @Test
    void withdrawalDTOToWithdrawal(){

        //given
        WithdrawalDTO toConvert = new WithdrawalDTO();
        toConvert.setAmount(AMOUNT);

        //when
        Withdrawal convertedReturned = mapper.withdrawalDTOToWithdrawal(toConvert);

        //then
        assertNotNull(convertedReturned);
        assertEquals(AMOUNT, convertedReturned.getAmount());
    }
}