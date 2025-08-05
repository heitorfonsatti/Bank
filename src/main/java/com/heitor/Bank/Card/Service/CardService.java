package com.heitor.Bank.Card.Service;

import com.heitor.Bank.BankAccount.Model.BankAccountModel;
import com.heitor.Bank.BankAccount.Repository.BankAccountRepository;
import com.heitor.Bank.Card.DTO.CardRecordDTO;
import com.heitor.Bank.Card.Model.CardModel;
import com.heitor.Bank.Card.Repository.CardRepository;
import com.heitor.Bank.Card.Utils.CardDataGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final BankAccountRepository bankAccountRepository;

    public CardModel createCard(CardRecordDTO cardRecordDTO) {
        Optional<BankAccountModel> bankAccount0 = bankAccountRepository.findById(cardRecordDTO.bankAccountId());
        if (bankAccount0.isEmpty()) {
            throw new IllegalArgumentException("Bank Account not found");
        }
        BankAccountModel bankAccount = bankAccount0.get();

        CardModel card = new CardModel();
        card.setType(cardRecordDTO.type());
        card.setLimit(cardRecordDTO.limit());
        card.setBankAccount(bankAccount);

        card.setCardNumber(CardDataGenerator.generateCardNumber());
        card.setCvv(CardDataGenerator.generateCVV());
        card.setExpirationDate(CardDataGenerator.generateExpirationDate());

        return cardRepository.save(card);
    }
}
