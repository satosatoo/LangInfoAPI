package com.example.WorldLangHubAPI.models;

import com.example.WorldLangHubAPI.utils.ResourceType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resources")
@Getter
@Setter
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private int resourceId;

    @NotNull
    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String link;

    @Column(name = "resource_type")
    private ResourceType resourceType;

    @ManyToOne
    @JoinColumn(name = "language_id")
    @JsonIgnoreProperties("resources")
    private Language language;

    public Resource() {
    }

    public Resource(String resourceName, String description, String link, ResourceType resourceType) {
        this.resourceName = resourceName;
        this.description = description;
        this.link = link;
        this.resourceType = resourceType;
    }

    public Resource(int resourceId, String resourceName, String description, String link, ResourceType resourceType) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.description = description;
        this.link = link;
        this.resourceType = resourceType;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", resourceName='" + resourceName + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", resourceType=" + resourceType +
                ", language=" + language.getLanguageName() +
                '}';
    }
}
