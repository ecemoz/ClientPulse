package com.yildiz.clientpulse.service;

import com.yildiz.clientpulse.dto.LoginRequest;
import com.yildiz.clientpulse.dto.RegisterRequest;
import com.yildiz.clientpulse.models.User;

public interface UserService {
    String register(RegisterRequest request);
    String login(LoginRequest request);
    User getCurrentUser();
}