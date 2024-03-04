package com.example.WorldLangHubAPI.services;

import com.example.WorldLangHubAPI.models.Resource;
import com.example.WorldLangHubAPI.repositories.LanguageRepository;
import com.example.WorldLangHubAPI.repositories.ResourceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public Resource findResourceByName(String resourceName) {
        return resourceRepository.findByResourceName(resourceName).get();
    }

    public Resource findResourceById(int id) {
        return resourceRepository.findById(id).get();
    }

    public List<Resource> findAllResources() {
        return resourceRepository.findAll();
    }

    public Resource saveWithId(Resource resource, int langId) {
        resource.setLanguage(languageRepository.findById(langId).get());
        return resourceRepository.save(resource);
    }

    public Resource saveWithName(Resource resource, String langName) {
        resource.setLanguage(languageRepository.findByLanguageName(langName).get());
        return resourceRepository.save(resource);
    }

    public void deleteResourceById(int id) {
        resourceRepository.deleteById(id);
    }

    public Resource update(Resource updatedResource, int id) {
        Optional<Resource> existingResourceOptional = resourceRepository.findById(id);
        if (existingResourceOptional.isPresent()) {
            Resource resource = existingResourceOptional.get();
            resource.setResourceName(updatedResource.getResourceName());
            resource.setDescription(updatedResource.getDescription());
            resource.setLink(updatedResource.getLink());
            return resourceRepository.save(resource);
        } else {
            throw new EntityNotFoundException("Resource with id " + id + " not found");
        }
    }
}
