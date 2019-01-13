package com.n26;

import com.n26.dto.TransactionDto;
import com.n26.entity.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionConstructorTests {

    private final static ZonedDateTime currentZonedDateTime = ZonedDateTime.now();
    private final static ZonedDateTime validZonedDateTime = currentZonedDateTime.minusSeconds(10);
    private static final TransactionDto transactionDtoWithValidAmount = new TransactionDto("122.311", validZonedDateTime.toString());
    private static final TransactionDto transactionDtoWithInvalidAmount = new TransactionDto("122.3RR", validZonedDateTime.toString());
    private static final TransactionDto transactionDtoWithInvalidTimestamp = new TransactionDto("122.311", "2019-01-13T16:42:22.350+06:00Asishkek]");

    private static final Transaction validTransaction = new Transaction(new BigDecimal("122.311"), validZonedDateTime);

    @Test
    public void whenAmountAndTransactionCanBeParsed_thenCreateTransaction() {
        Assert.assertEquals(validTransaction, Transaction.fromTransactionDto(transactionDtoWithValidAmount));
    }

    @Test(expected = NumberFormatException.class)
    public void whenAmountCanNotBeParsed_thenThrowNumberFormatException() {
        Transaction.fromTransactionDto(transactionDtoWithInvalidAmount);
    }

    @Test(expected = DateTimeParseException.class)
    public void whenTransactionCanNotBeParsed_thenThrowDateTimeParseException() {
        Transaction.fromTransactionDto(transactionDtoWithInvalidTimestamp);
    }
}
