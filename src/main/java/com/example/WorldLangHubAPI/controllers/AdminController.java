package com.example.WorldLangHubAPI.controllers;

import com.example.WorldLangHubAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String adminController() {
        return "Admin access level";
    }

    @PostMapping("/add-admin/{username}")
    public String addAdminToUser(@PathVariable String username) {
        userService.addAdminRoleUser(username);
        return "The admin role has been successfully added to the user named " + username + ".";
    }
}
