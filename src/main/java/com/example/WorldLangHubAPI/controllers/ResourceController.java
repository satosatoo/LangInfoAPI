package com.example.WorldLangHubAPI.controllers;

import com.example.WorldLangHubAPI.dto.ResourceDto;
import com.example.WorldLangHubAPI.dto.ResourceInfoDto;
import com.example.WorldLangHubAPI.models.Resource;
import com.example.WorldLangHubAPI.repositories.LanguageRepository;
import com.example.WorldLangHubAPI.services.LanguageService;
import com.example.WorldLangHubAPI.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private LanguageRepository languageRepository;

    @GetMapping("/lang-name/{languageName}")
    public List<ResourceInfoDto> getResourcesByLanguageName(@PathVariable String languageName) {
        return resourceService.findResourcesByLanguageName(languageName);
    }

    @GetMapping("/{id}")
    public ResourceInfoDto getResourceById(@PathVariable int id) {
        return resourceService.findResourceById(id);
    }

    @GetMapping()
    public List<ResourceInfoDto> getResources() {
        return resourceService.findAllResources();
    }

    @PostMapping("/lang-id/{id}")
    public ResourceInfoDto createResource(@RequestBody ResourceDto resourceDto, @PathVariable int id) {
        return resourceService.save(resourceDto, id);
    }

    @PostMapping("/lang-name/{name}")
    public ResourceInfoDto createResource(@RequestBody ResourceDto resourceDto, @PathVariable String name) {
        return resourceService.save(resourceDto, name.toLowerCase());
    }

    @PutMapping("/{id}")
    public ResourceInfoDto updateResource(@RequestBody ResourceDto updatedResourceDto, @PathVariable int id) {
        return resourceService.update(updatedResourceDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable int id) {
        resourceService.deleteResourceById(id);
    }
}
