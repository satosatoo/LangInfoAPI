package com.example.WorldLangHubAPI.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class LanguageDto {

    private String languageName;
    private List<String> languageCountries;
    private Long speakers;
    private List<Resource> resources;

    public LanguageDto() {
    }

    public LanguageDto(String languageName, List<String> languageCountries, Long speakers) {
        this.languageName = languageName.toLowerCase();
        this.languageCountries = languageCountries;
        this.speakers = speakers;
    }

    public LanguageDto(String languageName, List<String> languageCountries, Long speakers, List<Resource> resources) {
        this.languageName = languageName.toLowerCase();
        this.languageCountries = languageCountries;
        this.speakers = speakers;
        this.resources = resources;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName.toLowerCase();
    }

    public List<String> getLanguageCountries() {
        return languageCountries;
    }

    public void setLanguageCountries(List<String> languageCountries) {
        this.languageCountries = languageCountries;
    }

    public Long getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Long speakers) {
        this.speakers = speakers;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
