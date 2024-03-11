package com.example.WorldLangHubAPI.repositories;

import com.example.WorldLangHubAPI.dto.LanguageDto;
import com.example.WorldLangHubAPI.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Optional<Language> findByLanguageName(String languageName);
}
