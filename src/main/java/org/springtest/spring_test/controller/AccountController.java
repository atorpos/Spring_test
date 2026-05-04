package org.springtest.spring_test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springtest.spring_test.model.Transaction;
import org.springtest.spring_test.repository.TransactionRepository;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final TransactionRepository repo;


    public AccountController(TransactionRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        transaction.setCreatedAt(LocalDateTime.now());
        Transaction saved = repo.save(transaction);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{accountId}/summary")
    public ResponseEntity<?> getSummary(@PathVariable String accountId) {
        return repo.findSummaryByAccountId(accountId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("Welcome to the Account API");
    }

}
