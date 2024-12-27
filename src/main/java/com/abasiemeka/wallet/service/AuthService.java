package com.abasiemeka.wallet.service;

import com.abasiemeka.wallet.model.User;
import com.abasiemeka.wallet.model.dto.request.SignupDto;
import com.abasiemeka.wallet.model.dto.response.GenericResponse;

public interface AuthService {
    
    GenericResponse registerUser(SignupDto signupDto);
    
    boolean validateBvn(String bvn);
    
    User getUserByEmail(String email);
}

