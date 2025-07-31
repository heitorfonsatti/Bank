package com.heitor.Bank.Transaction.DTO;

import com.heitor.Bank.Transaction.Utils.TransactionType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRecordDTO(@NotNull TransactionType type, @NotNull BigDecimal amount, UUID sourceAccountId, UUID destinationAccountId) {
}
