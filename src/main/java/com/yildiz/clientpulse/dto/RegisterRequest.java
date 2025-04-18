package com.yildiz.clientpulse.dto;

import com.yildiz.clientpulse.models.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String phone;
    private Role role;
}