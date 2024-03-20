package com.example.WorldLangHubAPI.dto;

import java.util.List;

public class LanguageInfoDto {
    private int languageId;
    private String languageName;
    private List<String> languageCountries;
    private Long speakers;
    private List<String> resourceNames;

    public LanguageInfoDto() {
    }

    public LanguageInfoDto(int languageId, String languageName, List<String> languageCountries, Long speakers, List<String> resourceNames) {
        this.languageId = languageId;
        this.languageName = languageName.toLowerCase();
        this.languageCountries = languageCountries;
        this.speakers = speakers;
        this.resourceNames = resourceNames;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
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

    public List<String> getResourceNames() {
        return resourceNames;
    }

    public void setResourceNames(List<String> resourceNames) {
        this.resourceNames = resourceNames;
    }
}
