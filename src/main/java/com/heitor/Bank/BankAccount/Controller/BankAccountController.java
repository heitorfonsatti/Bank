package com.heitor.Bank.BankAccount.Controller;

import com.heitor.Bank.BankAccount.DTO.BankAccountRecordDTO;
import com.heitor.Bank.BankAccount.Model.BankAccountModel;
import com.heitor.Bank.BankAccount.Repository.BankAccountRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BankAccountController {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @PostMapping("/bank_account")
    public ResponseEntity<Object> saveBankAccount (@RequestBody @Valid BankAccountRecordDTO bankAccountRecordDTO) {
        var bankAccountModel = new BankAccountModel();
        BeanUtils.copyProperties(bankAccountRecordDTO, bankAccountModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountRepository.save(bankAccountModel));
    }

    @GetMapping("/bank_account")
    public ResponseEntity<List<BankAccountModel>> getAllBankAccounts() {
        List<BankAccountModel> bankAccountList = bankAccountRepository.findAll();
        if (!bankAccountList.isEmpty()) {
            for (BankAccountModel bankAccount : bankAccountList) {
                UUID id = bankAccount.getIdAccount();
                bankAccount.add(linkTo(methodOn(BankAccountController.class).getOneBankAccount(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountList);
    }

    @GetMapping("/bank_account/{id}")
    public ResponseEntity<Object> getOneBankAccount(@PathVariable (value = "id") UUID id) {
        Optional<BankAccountModel> bankAccount0 = bankAccountRepository.findById(id);
        if (bankAccount0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank Account not found");
        }
        bankAccount0.get().add(linkTo(methodOn(BankAccountController.class).getAllBankAccounts()).withRel("Bank Accounts list"));
        return ResponseEntity.status(HttpStatus.OK).body(bankAccount0.get());
    }

    @PutMapping("/bank_account/{id}")
    public ResponseEntity<Object> updateBankAccount(@PathVariable (value = "id") UUID id, @RequestBody @Valid BankAccountRecordDTO bankAccountRecordDTO) {
        Optional<BankAccountModel> bankAccount0 = bankAccountRepository.findById(id);
        if (bankAccount0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank Account not found");
        }
        var bankAccountModel = bankAccount0.get();
        BeanUtils.copyProperties(bankAccountRecordDTO, bankAccountModel);
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountRepository.save(bankAccountModel));
    }

    @DeleteMapping("/bank_account/{id}")
    public ResponseEntity<Object> deleteBankAccount (@PathVariable (value = "id") UUID id) {
        Optional<BankAccountModel> bankAccount0 = bankAccountRepository.findById(id);
        if (bankAccount0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank Account not found");
        }
        bankAccountRepository.delete(bankAccount0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Bank Account deleted successfully");
    }

}
