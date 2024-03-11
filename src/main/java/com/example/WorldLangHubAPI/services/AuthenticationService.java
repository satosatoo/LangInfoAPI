package com.example.WorldLangHubAPI.services;

import com.example.WorldLangHubAPI.dto.LoginResponseDto;
import com.example.WorldLangHubAPI.dto.ResourceInfoDto;
import com.example.WorldLangHubAPI.dto.UserInfoDto;
import com.example.WorldLangHubAPI.models.Resource;
import com.example.WorldLangHubAPI.models.Role;
import com.example.WorldLangHubAPI.models.UserApplication;
import com.example.WorldLangHubAPI.repositories.RoleRepository;
import com.example.WorldLangHubAPI.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

    public UserInfoDto registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRoles = roleRepository.findByAuthority("USER").get();

        List<Role> authorities = new ArrayList<>();
        authorities.add(userRoles);

        return customizedUserOutput(userRepository.save(new UserApplication(username, passwordEncoder.encode(password), authorities)));
    }

    public LoginResponseDto loginUser(String username, String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = tokenService.generateJwt(auth);
        UserApplication user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found"));

        return new LoginResponseDto(customizedUserOutput(user), token);
    }

    public UserInfoDto customizedUserOutput(UserApplication user) {
        if (user != null) {
            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto.setUserId(user.getUserId());
            userInfoDto.setUsername(user.getUsername());
            userInfoDto.setPassword(user.getPassword());
            userInfoDto.setAuthorities(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
            List<String> resNames = user.getResources().stream().map(Resource::getResourceName).toList();
            userInfoDto.setResourceNames(resNames);
            return userInfoDto;
        } else {
            throw new EntityNotFoundException("User with id " + user.getUserId() + " not found");
        }
    }
}
