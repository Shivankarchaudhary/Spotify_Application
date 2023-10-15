package com.example.Authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.UNAUTHORIZED,reason = "User Not Found")
public class UserNotFoundException extends Exception{
}
