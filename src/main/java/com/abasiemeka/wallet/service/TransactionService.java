package com.abasiemeka.wallet.service;

import com.abasiemeka.wallet.model.Transaction;
import com.abasiemeka.wallet.model.User;

import java.util.List;

public interface TransactionService {
    
    List<Transaction> getTransactionHistory(User user);
    Transaction getTransactionById(Long transactionId);
    
}

