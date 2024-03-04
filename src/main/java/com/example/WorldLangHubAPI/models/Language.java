package com.example.WorldLangHubAPI.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "language_name")
    private String languageName;

    @NotNull
    @Column(name = "language_countries")
    private List<String> languageCountries;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(name = "proficiency_level")
    private int proficiencyLevel;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Resource> resources;

    public Language() {
    }

    public Language(String languageName, List<String> languageCountries, int proficiencyLevel) {
        this.languageName = languageName;
        this.languageCountries = languageCountries;
        this.proficiencyLevel = proficiencyLevel;
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

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", languageName='" + languageName + '\'' +
                ", languageCountries=" + languageCountries +
                ", proficiencyLevel=" + proficiencyLevel +
                ", resources=" + resources +
                '}';
    }
}
