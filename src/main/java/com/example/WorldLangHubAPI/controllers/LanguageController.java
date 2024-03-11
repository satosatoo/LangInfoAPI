package com.example.WorldLangHubAPI.controllers;

import com.example.WorldLangHubAPI.dto.LanguageInfoDto;
import com.example.WorldLangHubAPI.models.Language;
import com.example.WorldLangHubAPI.dto.LanguageDto;
import com.example.WorldLangHubAPI.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lang")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping("/name/{name}")
    public LanguageInfoDto getLanguageByName(@PathVariable String name) {
        return languageService.findLanguageByName(name);
    }

    @GetMapping("/id/{id}")
    public LanguageInfoDto getLanguageById(@PathVariable int id) {
        return languageService.findLanguageById(id);
    }

    @GetMapping()
    public List<LanguageInfoDto> getLanguages() {
        return languageService.findAllLanguages();
    }

    @PostMapping()
    public LanguageInfoDto createLanguage(@RequestBody Language language) {
        return languageService.save(language);
    }

    @PutMapping("/{id}")
    public LanguageInfoDto updateLanguage(@RequestBody LanguageDto updatedLanguageDto, @PathVariable int id) {
        return languageService.update(updatedLanguageDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable int id) {
        languageService.deleteLangById(id);
    }
}
