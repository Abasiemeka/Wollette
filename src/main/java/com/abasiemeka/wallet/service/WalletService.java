package com.abasiemeka.wallet.service;

import com.abasiemeka.wallet.model.User;

import java.math.BigDecimal;

public interface WalletService {
    
    void fundWallet(User user, BigDecimal amount);
    void transferFunds(User sender, User recipient, BigDecimal amount);
    void withdrawFunds(User user, BigDecimal amount);
    BigDecimal getWalletBalance(User user);
}

