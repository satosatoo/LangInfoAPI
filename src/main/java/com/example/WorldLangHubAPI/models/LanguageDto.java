package com.example.WorldLangHubAPI.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LanguageDto {

    private String languageName;
    private List<String> languageCountries;
    private Long speakers;
    private List<Resource> resources;

    public LanguageDto() {
    }

    public LanguageDto(String languageName, List<String> languageCountries, Long speakers) {
        this.languageName = languageName;
        this.languageCountries = languageCountries;
        this.speakers = speakers;
    }

    public LanguageDto(String languageName, List<String> languageCountries, Long speakers, List<Resource> resources) {
        this.languageName = languageName;
        this.languageCountries = languageCountries;
        this.speakers = speakers;
        this.resources = resources;
    }
}
