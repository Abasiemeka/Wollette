package com.abasiemeka.wallet.service;

import com.abasiemeka.wallet.model.User;

public interface AuthService {
    
    User registerUser(User user);
    
    boolean validateBvn(String bvn);
    
    User getUserByEmail(String email);
}

