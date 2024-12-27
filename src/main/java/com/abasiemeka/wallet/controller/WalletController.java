package com.abasiemeka.wallet.controller;

import com.abasiemeka.wallet.model.User;
import com.abasiemeka.wallet.model.dto.request.FundRequest;
import com.abasiemeka.wallet.model.dto.request.WithdrawRequest;
import com.abasiemeka.wallet.model.dto.request.TransferRequest;
import com.abasiemeka.wallet.service.AuthService;
import com.abasiemeka.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @Autowired
    private AuthService authService;

    @PostMapping("/fund")
    public ResponseEntity<?> fundWallet(@AuthenticationPrincipal User user, @RequestBody FundRequest fundRequest) {
        walletService.fundWallet(user, fundRequest.amount());
        return ResponseEntity.ok("Wallet funded successfully");
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferFunds(@AuthenticationPrincipal User sender, @RequestBody TransferRequest transferRequest) {
        User recipient = authService.getUserByEmail(transferRequest.recipientEmail());
        walletService.transferFunds(sender, recipient, transferRequest.amount());
        return ResponseEntity.ok("Funds transferred successfully");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawFunds(@AuthenticationPrincipal User user, @RequestBody WithdrawRequest withdrawRequest) {
        walletService.withdrawFunds(user, withdrawRequest.amount());
        return ResponseEntity.ok("Funds withdrawn successfully");
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getWalletBalance(@AuthenticationPrincipal User user) {
        BigDecimal balance = walletService.getWalletBalance(user);
        Map<String, BigDecimal> response = new HashMap<>();
        response.put("balance", balance);
        return ResponseEntity.ok(response);
    }
}

