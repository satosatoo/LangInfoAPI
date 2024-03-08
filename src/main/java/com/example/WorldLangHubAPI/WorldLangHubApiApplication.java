package com.example.WorldLangHubAPI;

import com.example.WorldLangHubAPI.models.Role;
import com.example.WorldLangHubAPI.models.UserApplication;
import com.example.WorldLangHubAPI.repositories.RoleRepository;
import com.example.WorldLangHubAPI.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WorldLangHubApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorldLangHubApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			List<Role> roles = new ArrayList<>();
			roles.add(adminRole);

			UserApplication admin = new UserApplication(1, "stas_admin", passwordEncoder.encode("stasik"), roles);
			userRepository.save(admin);
		};
	}
}
