package com.heitor.Bank.Card.DTO;

import com.heitor.Bank.Card.Utils.CardType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CardRecordDTO(String cardNumber,
                            String cvv,
                            String expirationDate,
                            @NotBlank CardType type,
                            @NotNull BigDecimal limit,
                            @NotNull UUID bankAccountId) {
}
