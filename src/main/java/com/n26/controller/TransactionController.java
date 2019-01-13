package com.n26.controller;


import com.n26.dto.TransactionDto;
import com.n26.entity.Transaction;
import com.n26.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("${urls.transactions}")
public final class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody TransactionDto transactionDto) {
        transactionService.add(Transaction.fromTransactionDto(transactionDto));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        transactionService.deleteAll();
    }
}
