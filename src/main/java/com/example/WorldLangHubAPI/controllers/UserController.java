package com.example.WorldLangHubAPI.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    @GetMapping("/")
    public String userController() {
        return "User access level";
    }
}
