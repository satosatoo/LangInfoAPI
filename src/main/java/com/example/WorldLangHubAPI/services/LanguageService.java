package com.example.WorldLangHubAPI.services;

import com.example.WorldLangHubAPI.models.Language;
import com.example.WorldLangHubAPI.repositories.LanguageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public Language findLanguageByName(String languageName) {
        return languageRepository.findByLanguageName(languageName).get();
    }

    public Language findLanguageById(int id) {
        return languageRepository.findById(id).get();
    }

    public List<Language> findAllLanguages() {
        return languageRepository.findAll();
    }

    public Language save(Language language) {
        return languageRepository.save(language);
    }

    public void deleteLangById(int id) {
        languageRepository.deleteById(id);
    }

    public Language update(Language updatedLanguage, int id) {
        Optional<Language> existingLanguageOptional = languageRepository.findById(id);
        if (existingLanguageOptional.isPresent()) {
            Language language = existingLanguageOptional.get();
            language.setLanguageCountries(updatedLanguage.getLanguageCountries());
            language.setProficiencyLevel(updatedLanguage.getProficiencyLevel());
            language.setResources(updatedLanguage.getResources());
            return languageRepository.save(language);
        } else {
            throw new EntityNotFoundException("Language with id " + id + " not found");
        }
    }
}
