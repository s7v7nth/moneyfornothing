package com.seventh.moneyfornothing.controller;

import com.seventh.moneyfornothing.model.Transaction;
import com.seventh.moneyfornothing.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
public class TransactionController {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/transactions")
    Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/transactions/{id}")
    ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/transactions")
    ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) throws URISyntaxException {
        Transaction result = transactionRepository.save(transaction);
        return ResponseEntity.created(new URI("/transactions/" + result.getId())).body(result);
    }

    @PutMapping("/transactions/{id}")
    ResponseEntity<Transaction> updateTransaction(@Valid @RequestBody Transaction transaction, @PathVariable Long id) {
        Transaction result = transactionRepository.save(transaction);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/transactions/{id}")
    ResponseEntity<Transaction> deleteTransaction(@PathVariable Long id) {
        transactionRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
