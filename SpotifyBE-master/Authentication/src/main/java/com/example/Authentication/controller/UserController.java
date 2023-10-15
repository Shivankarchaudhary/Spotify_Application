package com.example.Authentication.controller;

import com.example.Authentication.domain.User;
import com.example.Authentication.exception.UserAlreadyFoundException;
import com.example.Authentication.exception.UserNotFoundException;
import com.example.Authentication.service.SecurityTokenGenerator;
import com.example.Authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/user/v1")
public class UserController {
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator){
        this.userService=userService;
        this.securityTokenGenerator=securityTokenGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException{
        Map<String,String> map=null;
        try{
            User user1=userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
            if(user1.getEmail().equals(user.getEmail())){
                map=securityTokenGenerator.generateToken(user);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
            }

        }catch (UserNotFoundException e){
            throw new RuntimeException(e);
        }catch (Exception e){
            return new ResponseEntity<>("Try after sometimes", HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user)throws UserAlreadyFoundException {
        User userCreated=userService.saveUser(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

}
