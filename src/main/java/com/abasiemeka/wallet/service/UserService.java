package com.abasiemeka.wallet.service;

import com.abasiemeka.wallet.model.User;
import com.abasiemeka.wallet.model.Wallet;
import com.abasiemeka.wallet.repository.UserRepository;
import com.abasiemeka.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Mock BVN validation
        if (!validateBvn(user.getBvn())) {
            throw new RuntimeException("Invalid BVN");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        Wallet wallet = new Wallet();
        wallet.setUser(savedUser);
        wallet.setBalance(BigDecimal.ZERO);
        walletRepository.save(wallet);

        return savedUser;
    }

    private boolean validateBvn(String bvn) {
        // Mock BVN validation
        return bvn != null && bvn.length() == 11;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

