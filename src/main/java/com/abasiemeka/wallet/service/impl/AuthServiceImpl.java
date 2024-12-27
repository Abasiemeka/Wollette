package com.abasiemeka.wallet.service.impl;

import com.abasiemeka.wallet.model.User;
import com.abasiemeka.wallet.model.Wallet;
import com.abasiemeka.wallet.model.dto.request.SignupDto;
import com.abasiemeka.wallet.model.dto.response.GenericResponse;
import com.abasiemeka.wallet.model.enums.ROLE;
import com.abasiemeka.wallet.repository.UserRepository;
import com.abasiemeka.wallet.repository.WalletRepository;
import com.abasiemeka.wallet.service.AuthService;
import com.abasiemeka.wallet.service.WalletService;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.GeneralPath;
import java.math.BigDecimal;
import java.util.HashSet;

import static com.abasiemeka.wallet.model.enums.ROLE.USER_BRONZE;
import static org.springframework.security.config.http.MatcherType.regex;


public class AuthServiceImpl implements AuthService {
    
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final PasswordEncoder passwordEncoder;
	private final WalletService walletService;
	
	public AuthServiceImpl(UserRepository userRepository, WalletRepository walletRepository, PasswordEncoder passwordEncoder, WalletService walletService) {
		this.userRepository = userRepository;
		this.walletRepository = walletRepository;
		this.passwordEncoder = passwordEncoder;
		this.walletService = walletService;
	}
	
	@Override
	@Transactional
	public User registerUser(SignupDto signupDto) {
        if (userRepository.existsByEmail(signupDto.email())) {
            throw new RuntimeException("Email already exists");
        }
        
        // Mock BVN validation
        if (!validateBvn(signupDto.bvn())) {
            throw new RuntimeException("Invalid BVN");
        }
		
		User newUser = buildNewUser(signupDto);
		User savedUser = userRepository.save(newUser);
		
		walletService.assignWalletTo(savedUser);
				
		newUser.setWallet(
				
						
				
        
        
        Wallet wallet = new Wallet();
        wallet.setUser(savedUser);
        wallet.setBalance(BigDecimal.ZERO);
        walletRepository.save(wallet);
        
        return savedUser;
    }
	
	@Override
	public boolean validateBvn(@NotNull String bvn) {
        // Mock BVN validation
		bvn = bvn.trim();
        return bvn.length() == 11 && bvn.matches("\\d");
    }
	
	@Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
	
	private User buildNewUser(SignupDto signupDto) {
		return User.builder()
				.email(signupDto.email())
				.bvn(signupDto.bvn())
				.password(passwordEncoder.encode(signupDto.password()))
				.firstName(signupDto.firstName())
				.middleName(signupDto.middlename())
				.lastName(signupDto.lastName())
				.dateOfBirth(signupDto.dob())
				.roles(new HashSet<ROLE>())
				.build();
	}
}

