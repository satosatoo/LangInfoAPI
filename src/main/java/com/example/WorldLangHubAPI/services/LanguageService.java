package com.example.WorldLangHubAPI.services;

import com.example.WorldLangHubAPI.dto.LanguageInfoDto;
import com.example.WorldLangHubAPI.models.Language;
import com.example.WorldLangHubAPI.dto.LanguageDto;
import com.example.WorldLangHubAPI.models.Resource;
import com.example.WorldLangHubAPI.repositories.LanguageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LanguageService {

    private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);

    @Autowired
    private LanguageRepository languageRepository;

    public LanguageInfoDto findLanguageByName(String languageName) {
        Language language = languageRepository.findByLanguageName(languageName).orElseThrow(() -> new EntityNotFoundException("Language with name " + languageName + " not found"));
        return customizedLanguageOutput(language);
    }

    public LanguageInfoDto findLanguageById(int id) {
        Language language = languageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Language with id " + id + " not found"));
        return customizedLanguageOutput(language);
    }

    public List<LanguageInfoDto> findAllLanguages() {
        return languageRepository.findAll().stream().map(this::customizedLanguageOutput).toList();
    }

    public LanguageInfoDto save(LanguageDto languageDto) {
        Language language = new Language();
        language.setLanguageName(languageDto.getLanguageName());
        language.setLanguageCountries(languageDto.getLanguageCountries());
        language.setSpeakers(languageDto.getSpeakers());
        language.setLanguageId(getNextLangId());
        return customizedLanguageOutput(languageRepository.save(language));
    }

    public void deleteLangById(int id) {
        languageRepository.deleteById(id);
    }

    public LanguageInfoDto update(LanguageDto updatedLanguageDto, int id) {
        Optional<Language> existingLanguageOptional = languageRepository.findById(id);
        if (existingLanguageOptional.isPresent()) {
            Language language = existingLanguageOptional.get();
            language.setLanguageCountries(updatedLanguageDto.getLanguageCountries());
            language.setSpeakers(updatedLanguageDto.getSpeakers());
            language.setResources(updatedLanguageDto.getResources());
            return customizedLanguageOutput(languageRepository.save(language));
        } else {
            logger.error("Language with id " + id + " not found");
            throw new EntityNotFoundException("Language with id " + id + " not found");
        }
    }

    public List<LanguageInfoDto> sortLanguagesBySpeakers() {
        List<Language> sortedListLang = languageRepository.findAll(Sort.by(Sort.Direction.DESC, "speakers"));
        return sortedListLang.stream().map(this::customizedLanguageOutput).toList();
    }



    // methods for main methods
    public LanguageInfoDto customizedLanguageOutput(Language language) {
        if (language != null) {
            LanguageInfoDto languageInfoDto = new LanguageInfoDto();
            languageInfoDto.setLanguageId(language.getLanguageId());
            languageInfoDto.setLanguageName(language.getLanguageName());
            languageInfoDto.setLanguageCountries(language.getLanguageCountries());
            languageInfoDto.setSpeakers(language.getSpeakers());
            if (language.getResources() != null) {
                List<String> resourceNames = language.getResources().stream().map(Resource::getResourceName).toList();
                languageInfoDto.setResourceNames(resourceNames);
            }
            return languageInfoDto;
        } else {
            throw new EntityNotFoundException("Language with name " + language.getLanguageName() + " not found");
        }
    }

    public int getNextLangId() {
        Language language = languageRepository.findFirstByOrderByLanguageIdDesc().orElse(null);
        if (language != null)
            return language.getLanguageId() + 1;
        else
            return 1;
    }
}
