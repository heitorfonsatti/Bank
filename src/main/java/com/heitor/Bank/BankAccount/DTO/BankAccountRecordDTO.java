package com.heitor.Bank.BankAccount.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BankAccountRecordDTO(@NotNull BigDecimal balance, @NotBlank String type, @NotNull LocalDate createdAt) {
}
