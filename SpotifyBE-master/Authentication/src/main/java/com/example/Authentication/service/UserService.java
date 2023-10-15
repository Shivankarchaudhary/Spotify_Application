package com.example.Authentication.service;

import com.example.Authentication.domain.User;
import com.example.Authentication.exception.UserAlreadyFoundException;
import com.example.Authentication.exception.UserNotFoundException;

public interface UserService {
    public User saveUser(User user) throws UserAlreadyFoundException;
    public User getUserByEmailAndPassword (String email,String password) throws UserNotFoundException;
}
