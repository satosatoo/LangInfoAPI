package com.example.WorldLangHubAPI.dto;

import com.example.WorldLangHubAPI.models.Language;
import com.example.WorldLangHubAPI.models.Resource;
import com.example.WorldLangHubAPI.models.UserApplication;
import com.example.WorldLangHubAPI.utils.ResourceType;

public class ResourceDto {

    private int resourceId;
    private String resourceName;
    private String description;
    private String link;
    private ResourceType resourceType;
    private Language language;
    private UserApplication author;

    public ResourceDto() {
    }

    public ResourceDto(String resourceName, String description, String link, ResourceType resourceType, Language language, UserApplication author) {
        this.resourceName = resourceName;
        this.description = description;
        this.link = link;
        this.resourceType = resourceType;
        this.language = language;
        this.author = author;
    }

    public ResourceDto(int resourceId, String resourceName, String description, String link, ResourceType resourceType, Language language, UserApplication author) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.description = description;
        this.link = link;
        this.resourceType = resourceType;
        this.language = language;
        this.author = author;
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

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public static Resource fromResource(ResourceDto resourceDto) {
        return new Resource(
                resourceDto.getResourceId(),
                resourceDto.getResourceName(),
                resourceDto.getDescription(),
                resourceDto.getLink(),
                resourceDto.getResourceType(),
                resourceDto.getLanguage(),
                resourceDto.getAuthor()
        );
    }
}
