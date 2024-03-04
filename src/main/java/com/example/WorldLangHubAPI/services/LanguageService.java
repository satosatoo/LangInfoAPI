package com.example.WorldLangHubAPI.services;

import com.example.WorldLangHubAPI.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public String getOneLanguage() {
        return null;
    }
}
