package com.heitor.Bank.BankAccount.Service;

import com.heitor.Bank.BankAccount.DTO.BankAccountRecordDTO;
import com.heitor.Bank.BankAccount.Model.BankAccountModel;
import com.heitor.Bank.BankAccount.Repository.BankAccountRepository;
import com.heitor.Bank.BankAccount.Utils.BankAccountDataGenerator;
import com.heitor.Bank.Client.Model.ClientModel;
import com.heitor.Bank.Client.Repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        account.setBalance(dto.balance());

        // Associar client
        ClientModel client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return bankAccountRepository.save(account);
    }
}
