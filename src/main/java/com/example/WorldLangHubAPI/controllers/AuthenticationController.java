package com.example.WorldLangHubAPI.controllers;

import com.example.WorldLangHubAPI.models.LoginResponseDto;
import com.example.WorldLangHubAPI.models.RegistrationDto;
import com.example.WorldLangHubAPI.models.UserApplication;
import com.example.WorldLangHubAPI.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserApplication registerUser(@RequestBody RegistrationDto body) {
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDto loginUser(@RequestBody RegistrationDto body) {
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}
