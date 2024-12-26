package com.abasiemeka.wallet.controller;

import com.abasiemeka.wallet.model.Transaction;
import com.abasiemeka.wallet.model.User;
import com.abasiemeka.wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/history")
    public ResponseEntity<?> getTransactionHistory(@AuthenticationPrincipal User user) {
        List<Transaction> transactions = transactionService.getTransactionHistory(user);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransactionById(@PathVariable Long transactionId) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        return ResponseEntity.ok(transaction);
    }
}

