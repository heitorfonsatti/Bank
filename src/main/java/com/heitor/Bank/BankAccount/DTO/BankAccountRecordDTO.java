package com.heitor.Bank.BankAccount.DTO;

import com.heitor.Bank.BankAccount.Utils.BankAccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BankAccountRecordDTO(@NotNull BigDecimal balance, @NotBlank BankAccountType type, @NotNull LocalDate createdAt, @NotNull UUID clientId) {
}
