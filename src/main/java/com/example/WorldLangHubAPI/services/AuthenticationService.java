package com.example.WorldLangHubAPI.services;

import com.example.WorldLangHubAPI.models.Role;
import com.example.WorldLangHubAPI.models.UserApplication;
import com.example.WorldLangHubAPI.repositories.RoleRepository;
import com.example.WorldLangHubAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserApplication registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRoles = roleRepository.findByAuthority("USER").get();

        List<Role> authorities = new ArrayList<>();
        authorities.add(userRoles);

        return userRepository.save(new UserApplication(username, passwordEncoder.encode(password), authorities));
    }
}
