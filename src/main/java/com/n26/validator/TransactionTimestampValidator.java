package com.n26.validator;

import com.n26.annotation.ValidTransaction;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.ZonedDateTime;

public final class TransactionTimestampValidator implements ConstraintValidator<ValidTransaction, String> {

    @Override
    public void initialize(ValidTransaction constraintAnnotation) {}

    @Override
    public boolean isValid(String s, ConstraintValidatorContext ctx) {
        if(s != null) {
            ZonedDateTime currentZonedDateTime = ZonedDateTime.now();
            ZonedDateTime currentZonedDateTimeMinusMinute = ZonedDateTime.now().minusSeconds(60);
            ZonedDateTime timestampZonedDateTime = ZonedDateTime.parse(s);
            if(timestampZonedDateTime.compareTo(currentZonedDateTime) > 0) {
                ctx.buildConstraintViolationWithTemplate("transaction's timestamp can not be for future date")
                        .addConstraintViolation();
                return false;
            }
            if(currentZonedDateTimeMinusMinute.compareTo(timestampZonedDateTime) > 0) {
                ctx.buildConstraintViolationWithTemplate("transaction's timestamp is old")
                        .addConstraintViolation();
                return false;
            }
            return true;
        }
        return false;
    }

}
