package com.n26;

import com.n26.dto.TransactionDto;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.ZonedDateTime;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTimestampValidatorTests {

    private static Validator validator;

    private final static ZonedDateTime currentZonedDateTime = ZonedDateTime.now();
    private final static ZonedDateTime validZonedDateTime = currentZonedDateTime.minusSeconds(10);
    private final static ZonedDateTime oldZonedDateTime = currentZonedDateTime.minusSeconds(70);
    private final static ZonedDateTime futureZonedDateTime = currentZonedDateTime.plusSeconds(10);
    private final static String amount = "21.111";

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenTimestampIsAccurate_thenNoConstraintViolations() {

        TransactionDto validTransactionDto = new TransactionDto(amount, validZonedDateTime.toString());

        Set<ConstraintViolation<TransactionDto>> constraintViolations = validator.validate(validTransactionDto);

        Assert.assertEquals(constraintViolations.size(), 0);
    }

    @Test
    public void whenTimestampIsOld_thenContainsConstraintViolations() {

        TransactionDto oldTransactionDto = new TransactionDto(amount, oldZonedDateTime.toString());

        Set<ConstraintViolation<TransactionDto>> constraintViolations = validator.validate(oldTransactionDto);

        Assert.assertEquals(constraintViolations.size(), 2);
    }

    @Test
    public void whenTimestampInFuture_thenContainsConstraintViolations() {

        TransactionDto futureTransactionDto = new TransactionDto(amount, futureZonedDateTime.toString());

        Set<ConstraintViolation<TransactionDto>> constraintViolations = validator.validate(futureTransactionDto);

        Assert.assertEquals(constraintViolations.size(), 2);
    }
}
