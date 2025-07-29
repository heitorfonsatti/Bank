package com.heitor.Bank.Client.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ClientRecordDTO(@NotBlank String name, @CPF @NotBlank String cpf, @Email @NotBlank String email, @NotBlank String phone, @NotBlank String address, @NotNull LocalDate dateOfBirth) {
}
