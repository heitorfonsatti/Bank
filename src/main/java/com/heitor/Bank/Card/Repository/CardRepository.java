package com.heitor.Bank.Card.Repository;

import com.heitor.Bank.Card.Model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<CardModel, UUID> {
}
