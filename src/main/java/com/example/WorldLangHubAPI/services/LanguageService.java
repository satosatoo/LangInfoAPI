package com.example.WorldLangHubAPI.services;

import com.example.WorldLangHubAPI.models.Language;
import com.example.WorldLangHubAPI.models.LanguageDto;
import com.example.WorldLangHubAPI.repositories.LanguageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LanguageService {

    private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);

    @Autowired
    private LanguageRepository languageRepository;

    public Language findLanguageByName(String languageName) {
        return languageRepository.findByLanguageName(languageName).orElseThrow(() -> new EntityNotFoundException("Language with name " + languageName + " not found"));
    }

    public Language findLanguageById(int id) {
        return languageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Language with id " + id + " not found"));
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

    public Language update(LanguageDto updatedLanguageDto, int id) {
        Optional<Language> existingLanguageOptional = languageRepository.findById(id);
        if (existingLanguageOptional.isPresent()) {
            Language language = existingLanguageOptional.get();
            language.setLanguageCountries(updatedLanguageDto.getLanguageCountries());
            language.setSpeakers(updatedLanguageDto.getSpeakers());
            language.setResources(updatedLanguageDto.getResources());
            return languageRepository.save(language);
        } else {
            logger.error("Language with id " + id + " not found");
            throw new EntityNotFoundException("Language with id " + id + " not found");
        }
    }
}
