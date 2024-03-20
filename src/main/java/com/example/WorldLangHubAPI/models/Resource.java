package com.example.WorldLangHubAPI.models;

import com.example.WorldLangHubAPI.utils.ResourceType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @Column(name = "resource_id")
    private int resourceId;

    @NotNull
    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "description")
    private String description;

    @URL
    @Column(name = "link")
    private String link;

    @NotNull
    @Column(name = "resource_type")
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    @ManyToOne
    @JoinColumn(name = "language_id")
    @JsonIgnoreProperties("resources")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("resources")
    private UserApplication author;

    public Resource() {
    }

    public Resource(String resourceName, String description, String link, ResourceType resourceType, Language language, UserApplication author) {
        this.resourceName = resourceName;
        this.description = description;
        this.link = link;
        this.resourceType = resourceType;
        this.language = language;
        this.author = author;
    }

    public Resource(int resourceId, String resourceName, String description, String link, ResourceType resourceType, Language language, UserApplication author) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.description = description;
        this.link = link;
        this.resourceType = resourceType;
        this.language = language;
        this.author = author;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public UserApplication getAuthor() {
        return author;
    }

    public void setAuthor(UserApplication author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", resourceName='" + resourceName + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", resourceType=" + resourceType +
                ", language=" + language +
                ", author=" + author +
                '}';
    }
}
