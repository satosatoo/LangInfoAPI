package com.example.WorldLangHubAPI.repositories;

import com.example.WorldLangHubAPI.models.UserApplication;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApplication, Integer> {
    Optional<UserApplication> findByUsername(String username);
    Optional<UserApplication> findFirstByOrderByUserIdDesc();
}
