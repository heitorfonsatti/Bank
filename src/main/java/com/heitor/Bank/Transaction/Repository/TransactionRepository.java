package com.heitor.Bank.Transaction.Repository;

import com.heitor.Bank.Transaction.Model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionModel, UUID> {
}
