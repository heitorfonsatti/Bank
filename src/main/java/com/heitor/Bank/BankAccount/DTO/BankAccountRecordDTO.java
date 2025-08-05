package com.heitor.Bank.BankAccount.DTO;

import com.heitor.Bank.BankAccount.Utils.BankAccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BankAccountRecordDTO(@NotBlank BankAccountType type, @NotNull LocalDate createdAt, @NotNull UUID clientId) {
}
