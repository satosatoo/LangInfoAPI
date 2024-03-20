package com.example.WorldLangHubAPI.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "languages")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "languageName")
public class Language {

    @Id
    @Column(name = "language_id")
    private int languageId;

    @NotNull
    @NotEmpty
    @Length(min = 4, max = 30)
    @Column(name = "language_name", unique = true)
    private String languageName;

    @NotNull
    @NotEmpty
    @ElementCollection
    @Column(name = "language_countries")
    private List<String> languageCountries;

    @Column(name = "speakers")
    private Long speakers;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Resource> resources;

    public Language() {
    }

    public Language(String languageName, List<String> languageCountries, Long speakers) {
        this.languageName = languageName.toLowerCase();
        this.languageCountries = languageCountries;
        this.speakers = speakers;
    }

    public Language(String languageName, List<String> languageCountries, Long speakers, List<Resource> resources) {
        this.languageName = languageName.toLowerCase();
        this.languageCountries = languageCountries;
        this.speakers = speakers;
        this.resources = resources;
    }

    public Language(int languageId, String languageName, List<String> languageCountries, Long speakers, List<Resource> resources) {
        this.languageId = languageId;
        this.languageName = languageName.toLowerCase();
        this.languageCountries = languageCountries;
        this.speakers = speakers;
        this.resources = resources;
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
        this.languageName = languageName;
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

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", languageName='" + languageName + '\'' +
                ", languageCountries=" + languageCountries +
                ", speakers=" + speakers +
                ", resources=" + resources +
                '}';
    }
}
