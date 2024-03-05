package com.example.WorldLangHubAPI.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Table(name = "languages")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "languageName")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private int languageId;

    @NotNull
    @Column(name = "language_name", unique = true)
    private String languageName;

    @NotNull
    @Column(name = "language_countries")
    private List<String> languageCountries; // possibility to add countries

    @Column(name = "speakers")
    private Long speakers;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Resource> resources;

    public Language() {
    }

    public Language(String languageName, List<String> languageCountries, Long speakers) {
        this.languageName = languageName;
        this.languageCountries = languageCountries;
        this.speakers = speakers;
    }

    public Language(String languageName, List<String> languageCountries, Long speakers, List<Resource> resources) {
        this.languageName = languageName;
        this.languageCountries = languageCountries;
        this.speakers = speakers;
        this.resources = resources;
    }

    public Language(int languageId, String languageName, List<String> languageCountries, Long speakers, List<Resource> resources) {
        this.languageId = languageId;
        this.languageName = languageName;
        this.languageCountries = languageCountries;
        this.speakers = speakers;
        this.resources = resources;
    }
}
