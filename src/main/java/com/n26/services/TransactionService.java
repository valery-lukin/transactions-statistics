package com.n26.services;

import com.n26.dto.TransactionStatisticsDto;
import com.n26.entity.Transaction;

public interface TransactionService {

    void add(Transaction transaction);

    void deleteAll();

    TransactionStatisticsDto getStatistics();
}
