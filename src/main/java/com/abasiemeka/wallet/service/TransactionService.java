package com.abasiemeka.wallet.service;

import com.abasiemeka.wallet.model.Transaction;
import com.abasiemeka.wallet.model.User;
import com.abasiemeka.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactionHistory(User user) {
        return transactionRepository.findByUserIdOrderByTimestampDesc(user.getId());
    }

    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }
}

