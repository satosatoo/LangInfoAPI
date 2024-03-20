package com.example.WorldLangHubAPI.services;

import com.example.WorldLangHubAPI.models.Role;
import com.example.WorldLangHubAPI.models.UserApplication;
import com.example.WorldLangHubAPI.repositories.RoleRepository;
import com.example.WorldLangHubAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found"));
    }

    public void addAdminRoleUser(String username) {
        UserApplication user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found"));

        Role adminRole = roleRepository.findByAuthority("ADMIN").get();

        List<Role> roles = user.getAuthorities().stream()
                .map(authority -> new Role(authority.getAuthority())).toList();

        if (!user.getAuthorities().contains(adminRole)) {
            roles.add(adminRole);
            user.setAuthorities(roles);
            userRepository.save(user);
        }
    }
}
