package com.n26.controller;

import com.n26.dto.TransactionStatisticsDto;
import com.n26.services.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${urls.statistics}")
public final class StatisticController {

    private TransactionService transactionService;

    public StatisticController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public TransactionStatisticsDto getStatistics() {
        return transactionService.getStatistics();
    }
}
