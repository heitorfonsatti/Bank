package com.heitor.Bank.BankAccount.Service;

import com.heitor.Bank.BankAccount.DTO.BankAccountRecordDTO;
import com.heitor.Bank.BankAccount.Model.BankAccountModel;
import com.heitor.Bank.BankAccount.Repository.BankAccountRepository;
import com.heitor.Bank.BankAccount.Utils.BankAccountDataGenerator;
import com.heitor.Bank.Client.Model.ClientModel;
import com.heitor.Bank.Client.Repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;

    public BankAccountModel createAccount(BankAccountRecordDTO dto) {
        BankAccountModel account = new BankAccountModel();

        account.setAccountNumber(BankAccountDataGenerator.generateAccountNumber());
        account.setAgencyNumber(BankAccountDataGenerator.generateAgencyNumber());

        account.setType(dto.type());
        account.setBalance(BigDecimal.ZERO);

        // Associar client
        ClientModel client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return bankAccountRepository.save(account);
    }

    public List<BankAccountModel> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccountModel> getAccountById(UUID id) {
        return bankAccountRepository.findById(id);
    }

    public Optional<BankAccountModel> updateAccount(UUID id, BankAccountRecordDTO bankAccountRecordDTO) {
        Optional<BankAccountModel> account0 = bankAccountRepository.findById(id);
        if (account0.isEmpty()) {
            return Optional.empty();
        }

        BankAccountModel account = account0.get();
        account.setType(bankAccountRecordDTO.type());

        return Optional.of(bankAccountRepository.save(account));
    }

    public boolean deleteAccount(UUID id) {
        Optional<BankAccountModel> account0 = bankAccountRepository.findById(id);
        if (account0.isEmpty()) {
            return false;
        }
        bankAccountRepository.delete(account0.get());
        return true;
    }

}
