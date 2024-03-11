package com.example.WorldLangHubAPI.dto;

import com.example.WorldLangHubAPI.models.Language;
import com.example.WorldLangHubAPI.utils.ResourceType;

public class ResourceInfoDto {
    private int resourceId;
    private String resourceName;
    private String description;
    private String link;
    private ResourceType resourceType;
    private String language;
    private String author;

    public ResourceInfoDto() {
    }

    public ResourceInfoDto(int resourceId, String resourceName, String description, String link, ResourceType resourceType, String language, String author) {
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
