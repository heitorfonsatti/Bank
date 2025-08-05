package com.heitor.Bank.BankAccount.Controller;

import com.heitor.Bank.BankAccount.DTO.BankAccountRecordDTO;
import com.heitor.Bank.BankAccount.Model.BankAccountModel;
import com.heitor.Bank.BankAccount.Repository.BankAccountRepository;
import com.heitor.Bank.BankAccount.Service.BankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping("/bank_account")
    public ResponseEntity<Object> saveBankAccount (@RequestBody @Valid BankAccountRecordDTO bankAccountRecordDTO) {
        var bankAccountModel  = bankAccountService.createAccount(bankAccountRecordDTO);
        bankAccountModel.add(linkTo(methodOn(BankAccountController.class).getOneBankAccount(bankAccountModel.getIdAccount())).withSelfRel());
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountModel);
    }

    @GetMapping("/bank_account")
    public ResponseEntity<List<BankAccountModel>> getAllBankAccounts() {
        List<BankAccountModel> bankAccountList = bankAccountService.getAllAccounts();
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
        Optional<BankAccountModel> bankAccount0 = bankAccountService.getAccountById(id);
        if (bankAccount0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank Account not found");
        }
        BankAccountModel account = bankAccount0.get();
        account.add(linkTo(methodOn(BankAccountController.class).getAllBankAccounts()).withRel("Bank Accounts list"));
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @PutMapping("/bank_account/{id}")
    public ResponseEntity<Object> updateBankAccount(@PathVariable (value = "id") UUID id, @RequestBody @Valid BankAccountRecordDTO bankAccountRecordDTO) {
        Optional<BankAccountModel> bankAccount0 = bankAccountService.updateAccount(id, bankAccountRecordDTO);
        if (bankAccount0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank Account not found");
        }
        BankAccountModel account = bankAccount0.get();
        account.add(linkTo(methodOn(BankAccountController.class).getOneBankAccount(account.getIdAccount())).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @DeleteMapping("/bank_account/{id}")
    public ResponseEntity<Object> deleteBankAccount (@PathVariable (value = "id") UUID id) {
        boolean deleted = bankAccountService.deleteAccount(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Bank Account deleted successfully");
        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank Account not found");
        }
    }

}
