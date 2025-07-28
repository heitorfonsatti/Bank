package com.heitor.Bank.BankAccount.Repository;

import com.heitor.Bank.BankAccount.Model.BankAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccountModel, UUID> {
}
