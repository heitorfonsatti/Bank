package com.heitor.Bank.BankAccount.DTO;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BankAccountRecordDTO(@NotNull BigDecimal balance, @NotNull LocalDate createdAt) {
}
