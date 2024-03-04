package com.example.WorldLangHubAPI.controllers;

import com.example.WorldLangHubAPI.models.Language;
import com.example.WorldLangHubAPI.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lang")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping("/{name}")
    public Language getLanguageByName(@PathVariable String name) {
        return languageService.findLanguageByName(name);
    }

    @GetMapping("/{id}")
    public Language getLanguageByName(@PathVariable int id) {
        return languageService.findLanguageById(id);
    }

    @GetMapping()
    public List<Language> getLanguages() {
        return languageService.findAllLanguages();
    }

    @PostMapping()
    public Language createLanguage(@RequestBody Language language) {
        return languageService.save(language);
    }

    @PutMapping("/{id}")
    public Language updateLanguage(@RequestBody Language updatedLanguage, @PathVariable int id) {
        return languageService.update(updatedLanguage, id);
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable int id) {
        languageService.deleteLangById(id);
    }
}
