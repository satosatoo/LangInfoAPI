package com.example.WorldLangHubAPI.repositories;

import com.example.WorldLangHubAPI.models.Language;
import com.example.WorldLangHubAPI.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
    Optional<Resource> findByResourceName(String resourceName);

    Optional<Language> deleteByResourceName(String resourceName);
}
