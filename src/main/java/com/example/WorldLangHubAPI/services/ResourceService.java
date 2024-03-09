package com.example.WorldLangHubAPI.services;

import com.example.WorldLangHubAPI.models.Resource;
import com.example.WorldLangHubAPI.models.UserApplication;
import com.example.WorldLangHubAPI.repositories.LanguageRepository;
import com.example.WorldLangHubAPI.repositories.ResourceRepository;
import com.example.WorldLangHubAPI.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
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

    @Autowired
    private UserRepository userRepository;

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

        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (auth != null && auth.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) auth.getPrincipal();
            String username = jwt.getClaim("sub");
            UserApplication user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found"));
            resource.setAuthor(user);
        } else {
            throw new ClassCastException("Expected Jwt principal but found: " + (auth != null ? auth.getPrincipal().getClass() : "null"));
        }

        return resourceRepository.save(resource);
    }

    public Resource save(Resource resource, String langName) {
        resource.setLanguage(languageRepository.findByLanguageName(langName)
                .orElseThrow(() -> new EntityNotFoundException("Language with name " + langName + " not found")));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) auth.getPrincipal();
            String username = jwt.getClaim("sub");
            UserApplication user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found"));
            resource.setAuthor(user);
        } else {
            throw new ClassCastException("Expected Jwt principal but found: " + (auth != null ? auth.getPrincipal().getClass() : "null"));
        }

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
