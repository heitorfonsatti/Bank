package com.heitor.Bank.Client.Repository;

import com.heitor.Bank.Client.Model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientModel, UUID> {
}
