package com.n26.entity;

import com.n26.dto.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Comparator;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public final class Transaction implements Comparable<Transaction> {

    private BigDecimal amount;

    private ZonedDateTime timestamp;

    @Override
    public int compareTo(Transaction transaction) {
        return Comparator.comparing(Transaction::getTimestamp)
                .thenComparing(Transaction::getAmount)
                .compare(this, transaction);
    }

    public static Transaction fromTransactionDto(TransactionDto transactionDto) {
        BigDecimal amount = new BigDecimal(transactionDto.getAmount());
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(transactionDto.getTimestamp());
        return new Transaction(amount, zonedDateTime);
    }
}
