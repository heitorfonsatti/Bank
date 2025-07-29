package com.heitor.Bank.Transaction.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.heitor.Bank.Transaction.DTO.TransactionRecordDTO;
import com.heitor.Bank.Transaction.Model.TransactionModel;
import com.heitor.Bank.Transaction.Repository.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/transaction")
    public ResponseEntity<TransactionModel> saveTransaction (@RequestBody @Valid TransactionRecordDTO transactionRecordDTO) {
        var transactionModel = new TransactionModel();
        BeanUtils.copyProperties(transactionRecordDTO, transactionModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionRepository.save(transactionModel));
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<TransactionModel>> getAllTransactions () {
        List<TransactionModel> transactionList = transactionRepository.findAll();
        if (!transactionList.isEmpty()) {
            for (TransactionModel transaction : transactionList) {
                UUID id = transaction.getIdTransaction();
                transaction.add(linkTo(methodOn(TransactionController.class).getOneTransaction(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(transactionList);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Object> getOneTransaction (@PathVariable(value = "id") UUID id) {
        Optional<TransactionModel> transaction0 = transactionRepository.findById(id);
        if(transaction0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
        }
        transaction0.get().add(linkTo(methodOn(TransactionController.class).getAllTransactions()).withRel("Transactions list"));
        return ResponseEntity.status(HttpStatus.OK).body(transaction0.get());
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<Object> updateTransaction (@PathVariable (value = "id") UUID id, @RequestBody @Valid TransactionRecordDTO transactionRecordDTO) {
        Optional<TransactionModel> transaction0 = transactionRepository.findById(id);
        if (transaction0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
        }
        var transactionModel = transaction0.get();
        BeanUtils.copyProperties(transactionRecordDTO, transactionModel);
        return ResponseEntity.status(HttpStatus.OK).body(transactionRepository.save(transactionModel));
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<Object> deleteTransaction (@PathVariable (value = "id") UUID id) {
        Optional<TransactionModel> transaction0 = transactionRepository.findById(id);
        if (transaction0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
        }
        transactionRepository.delete(transaction0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Transaction deleted successfully");
    }

}
