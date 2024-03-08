package com.example.WorldLangHubAPI.services;

import com.example.WorldLangHubAPI.models.LoginResponseDto;
import com.example.WorldLangHubAPI.models.Role;
import com.example.WorldLangHubAPI.models.UserApplication;
import com.example.WorldLangHubAPI.repositories.RoleRepository;
import com.example.WorldLangHubAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public UserApplication registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRoles = roleRepository.findByAuthority("USER").get();

        List<Role> authorities = new ArrayList<>();
        authorities.add(userRoles);

        return userRepository.save(new UserApplication(username, passwordEncoder.encode(password), authorities));
    }

    public LoginResponseDto loginUser(String username, String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = tokenService.generateJwt(auth);
        UserApplication user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found"));

        return new LoginResponseDto(user, token);
    }
}
