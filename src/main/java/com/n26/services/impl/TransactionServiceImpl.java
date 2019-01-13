package com.n26.services.impl;

import com.n26.collector.BigDecimalStatistics;
import com.n26.dto.TransactionStatisticsDto;
import com.n26.entity.Transaction;
import com.n26.services.TransactionService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.concurrent.ConcurrentSkipListSet;

@Service
@EnableScheduling
public final class TransactionServiceImpl implements TransactionService {

    private final ConcurrentSkipListSet<Transaction> transactions = new ConcurrentSkipListSet<>();

    @Override
    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public void deleteAll() {
        transactions.clear();
    }

    @Override
    public TransactionStatisticsDto getStatistics() {
        ZonedDateTime currentZonedDateTimeMinusMinute = ZonedDateTime.now().minusSeconds(60);
        BigDecimalStatistics bigDecimalStatistics = transactions.stream()
                .filter(transaction -> transaction.getTimestamp().compareTo(currentZonedDateTimeMinusMinute) > 0)
                .map(Transaction::getAmount)
                .collect(BigDecimalStatistics.statistics());
        return TransactionStatisticsDto.fromStatistics(bigDecimalStatistics);
    }

    // Cleaning transactions with fixed interval of 2 minutes, so not to pollute data structure with old entries
    @Scheduled(fixedDelay = 120000)
    public void scheduleFixedDelayTask() {
        ZonedDateTime currentZonedDateTimeMinusMinute = ZonedDateTime.now().minusSeconds(60);
        transactions.removeIf(transaction -> transaction.getTimestamp().compareTo(currentZonedDateTimeMinusMinute) < 0);
    }
}
