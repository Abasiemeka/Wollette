package com.abasiemeka.wallet.service.impl;

import com.abasiemeka.wallet.model.Transaction;
import com.abasiemeka.wallet.model.User;
import com.abasiemeka.wallet.repository.TransactionRepository;
import com.abasiemeka.wallet.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	public List<Transaction> getTransactionHistory(User user) {
        return transactionRepository.findByUserIdOrderByTimestampDesc(user.getId());
    }

    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }
}

