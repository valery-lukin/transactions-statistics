package com.n26.dto;

import com.n26.annotation.ValidTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public final class TransactionDto {

    @NotBlank
    @NotNull
    private String amount;

    @NotBlank
    @NotNull
    @ValidTransaction
    private String timestamp;
}
