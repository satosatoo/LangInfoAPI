package com.example.WorldLangHubAPI.services;

import com.example.WorldLangHubAPI.models.Resource;
import com.example.WorldLangHubAPI.repositories.LanguageRepository;
import com.example.WorldLangHubAPI.repositories.ResourceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public Resource findResourceByName(String resourceName) {
        return resourceRepository.findByResourceName(resourceName)
                .orElseThrow(() -> new EntityNotFoundException("Resource with name " + resourceName + " not found"));
    }

    public Resource findResourceById(int id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resource with id " + id + " not found"));
    }

    public List<Resource> findAllResources() {
        return resourceRepository.findAll();
    }

    public Resource save(Resource resource, int langId) {
        resource.setLanguage(languageRepository.findById(langId)
                .orElseThrow(() -> new EntityNotFoundException("Language with id " + langId + " not found")));
        return resourceRepository.save(resource);
    }

    public Resource save(Resource resource, String langName) {
        resource.setLanguage(languageRepository.findByLanguageName(langName)
                .orElseThrow(() -> new EntityNotFoundException("Language with name " + langName + " not found")));
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
            logger.error("Resource with id " + id + " not found");
            throw new EntityNotFoundException("Resource with id " + id + " not found");
        }
    }
}
