package com.example.WorldLangHubAPI.services;

import com.example.WorldLangHubAPI.dto.ResourceInfoDto;
import com.example.WorldLangHubAPI.models.Language;
import com.example.WorldLangHubAPI.models.Resource;
import com.example.WorldLangHubAPI.models.UserApplication;
import com.example.WorldLangHubAPI.repositories.LanguageRepository;
import com.example.WorldLangHubAPI.repositories.ResourceRepository;
import com.example.WorldLangHubAPI.repositories.UserRepository;
import com.example.WorldLangHubAPI.utils.UnauthorizedException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public List<ResourceInfoDto> findResourcesByLanguageName(String languageName) {
        Language language = languageRepository.findByLanguageName(languageName)
                .orElseThrow(() -> new EntityNotFoundException("Language with name " + languageName + " not found"));

        if (language != null)
            return resourceRepository.findResourcesByLanguageId(language.getLanguageId()).stream().map(this::customizedResourceOutput).toList();
        else
            throw new EntityNotFoundException("Language with name " + languageName + " not found");
    }

    public ResourceInfoDto findResourceById(int id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resource with id " + id + " not found"));
        return customizedResourceOutput(resource);
    }

    public List<ResourceInfoDto> findAllResources() {
        return resourceRepository.findAll().stream().map(this::customizedResourceOutput).toList();
    }

    public ResourceInfoDto save(Resource resource, int langId) {
        resource.setLanguage(languageRepository.findById(langId)
                .orElseThrow(() -> new EntityNotFoundException("Language with id " + langId + " not found")));

        UserApplication authUser = getAuthUser();
        if (authUser != null)
            resource.setAuthor(authUser);
        else
            throw new UnauthorizedException("User is not authenticated");

        return customizedResourceOutput(resourceRepository.save(resource));
    }

    public ResourceInfoDto save(Resource resource, String langName) {
        resource.setLanguage(languageRepository.findByLanguageName(langName)
                .orElseThrow(() -> new EntityNotFoundException("Language with name " + langName + " not found")));

        UserApplication authUser = getAuthUser();
        if (authUser != null)
            resource.setAuthor(authUser);
        else
            throw new UnauthorizedException("User is not authenticated");

        return customizedResourceOutput(resourceRepository.save(resource));
    }

    public void deleteResourceById(int id) {
        resourceRepository.deleteById(id);
    }

    public ResourceInfoDto update(Resource updatedResource, int id) {
        Optional<Resource> existingResourceOptional = resourceRepository.findById(id);
        if (existingResourceOptional.isPresent()) {
            Resource resource = existingResourceOptional.get();
            resource.setResourceName(updatedResource.getResourceName());
            resource.setDescription(updatedResource.getDescription());
            resource.setLink(updatedResource.getLink());
            return customizedResourceOutput(resourceRepository.save(resource));
        } else {
            logger.error("Resource with id " + id + " not found");
            throw new EntityNotFoundException("Resource with id " + id + " not found");
        }
    }



    // methods for main methods
    public UserApplication getAuthUser() {
        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (auth != null && auth.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) auth.getPrincipal();
            String username = jwt.getClaim("sub");
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found"));

        } else {
            throw new ClassCastException("Expected Jwt principal but found: " + (auth != null ? auth.getPrincipal().getClass() : "null"));
        }
    }

    public ResourceInfoDto customizedResourceOutput(Resource resource) {
        if (resource != null) {
            ResourceInfoDto resourceInfoDto = new ResourceInfoDto();
            resourceInfoDto.setResourceId(resource.getResourceId());
            resourceInfoDto.setResourceName(resource.getResourceName());
            resourceInfoDto.setDescription(resource.getDescription());
            resourceInfoDto.setResourceType(resource.getResourceType());
            resourceInfoDto.setLink(resource.getLink());
            resourceInfoDto.setAuthor(resource.getAuthor().getUsername());
            resourceInfoDto.setLanguage(resource.getLanguage().getLanguageName());
            return resourceInfoDto;
        } else {
            throw new EntityNotFoundException("Resource with id " + resource.getResourceId() + " not found");
        }
    }
}
