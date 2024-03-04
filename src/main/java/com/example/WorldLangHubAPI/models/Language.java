package com.example.WorldLangHubAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "languages")
@Getter
@Setter
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private int languageId;

    @Column(name = "language_name")
    private String languageName;

    @Column(name = "language_countries")
    private List<String> languageCountries;

    @Column(name = "proficiency_level")
    private int proficiencyLevel;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
    private List<Resource> resources;

    public Language() {
    }

    public Language(String languageName, List<String> languageCountries, int proficiencyLevel, List<Resource> resources) {
        this.languageName = languageName;
        this.languageCountries = languageCountries;
        this.proficiencyLevel = proficiencyLevel;
        this.resources = resources;
    }

    public Language(int languageId, String languageName, List<String> languageCountries, int proficiencyLevel, List<Resource> resources) {
        this.languageId = languageId;
        this.languageName = languageName;
        this.languageCountries = languageCountries;
        this.proficiencyLevel = proficiencyLevel;
        this.resources = resources;
    }
}
