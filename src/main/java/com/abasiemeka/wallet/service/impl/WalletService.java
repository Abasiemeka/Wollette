package com.abasiemeka.wallet.service.impl;

import com.abasiemeka.wallet.model.Transaction;
import com.abasiemeka.wallet.model.User;
import com.abasiemeka.wallet.model.Wallet;
import com.abasiemeka.wallet.repository.TransactionRepository;
import com.abasiemeka.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class WalletService {
    
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
	
	public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
		this.walletRepository = walletRepository;
		this.transactionRepository = transactionRepository;
	}
	
	@Transactional
    public void fundWallet(User user, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTransactionType("FUND");
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription("Wallet funding");
        transactionRepository.save(transaction);
    }

    @Transactional
    public void transferFunds(User sender, User recipient, BigDecimal amount) {
        Wallet senderWallet = walletRepository.findByUserId(sender.getId())
                .orElseThrow(() -> new RuntimeException("Sender wallet not found"));
        Wallet recipientWallet = walletRepository.findByUserId(recipient.getId())
                .orElseThrow(() -> new RuntimeException("Recipient wallet not found"));

        if (senderWallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        senderWallet.setBalance(senderWallet.getBalance().subtract(amount));
        recipientWallet.setBalance(recipientWallet.getBalance().add(amount));

        walletRepository.save(senderWallet);
        walletRepository.save(recipientWallet);

        Transaction senderTransaction = new Transaction();
        senderTransaction.setUser(sender);
        senderTransaction.setTransactionType("TRANSFER_OUT");
        senderTransaction.setAmount(amount.negate());
        senderTransaction.setTimestamp(LocalDateTime.now());
        senderTransaction.setDescription("Transfer to " + recipient.getEmail());
        transactionRepository.save(senderTransaction);

        Transaction recipientTransaction = new Transaction();
        recipientTransaction.setUser(recipient);
        recipientTransaction.setTransactionType("TRANSFER_IN");
        recipientTransaction.setAmount(amount);
        recipientTransaction.setTimestamp(LocalDateTime.now());
        recipientTransaction.setDescription("Transfer from " + sender.getEmail());
        transactionRepository.save(recipientTransaction);
    }

    @Transactional
    public void withdrawFunds(User user, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTransactionType("WITHDRAW");
        transaction.setAmount(amount.negate());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription("Wallet withdrawal");
        transactionRepository.save(transaction);
    }

    public BigDecimal getWalletBalance(User user) {
        Wallet wallet = walletRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
        return wallet.getBalance();
    }
}

