package com.example.WorldLangHubAPI.models;

import com.example.WorldLangHubAPI.utils.ResourceType;
import jakarta.persistence.*;
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
    private Language language;

    public Resource() {
    }

    public Resource(String resourceName, String description, String link, ResourceType resourceType, Language language) {
        this.resourceName = resourceName;
        this.description = description;
        this.link = link;
        this.resourceType = resourceType;
        this.language = language;
    }

    public Resource(int resourceId, String resourceName, String description, String link, ResourceType resourceType, Language language) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.description = description;
        this.link = link;
        this.resourceType = resourceType;
        this.language = language;
    }
}
