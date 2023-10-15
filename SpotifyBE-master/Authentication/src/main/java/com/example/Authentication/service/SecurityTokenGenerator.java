package com.example.Authentication.service;

import com.example.Authentication.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);
}
