package com.abasiemeka.wallet.controller;

import com.abasiemeka.wallet.model.User;
import com.abasiemeka.wallet.model.dto.request.LoginRequest;
import com.abasiemeka.wallet.model.dto.request.SignupDto;
import com.abasiemeka.wallet.model.dto.response.GenericResponse;
import com.abasiemeka.wallet.service.AuthService;
import com.abasiemeka.wallet.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final AuthService authService;
	
	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, AuthService authService) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
		this.authService = authService;
	}
	
	@PostMapping("/register")
    public GenericResponse registerUser(@RequestBody SignupDto signupDto) {
        return authService.registerUser(signupDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return GenericResponse.success(authenticateUser(loginRequest.getEmail(), loginRequest.getPassword()));
    }
}

