package tech.talci.talcibankspringrest.api.v1.mapper;

import org.junit.jupiter.api.Test;
import tech.talci.talcibankspringrest.api.v1.dto.DepositDTO;
import tech.talci.talcibankspringrest.domain.Account;
import tech.talci.talcibankspringrest.domain.Deposit;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DepositMapperTest {
    static final BigDecimal AMOUNT = new BigDecimal("2.0");
    static final DepositMapper mapper = DepositMapper.INSTANCE;


    @Test
    void depositToDepositDTO() {
        //given
        Deposit deposit = new Deposit();
        deposit.setAmount(AMOUNT);

        //when
        DepositDTO depositDTO = mapper.depositToDepositDTO(deposit);

        //then
        assertNotNull(depositDTO);
        assertEquals(AMOUNT, depositDTO.getAmount());
    }
}