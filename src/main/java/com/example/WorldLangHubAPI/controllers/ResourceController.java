package com.example.WorldLangHubAPI.controllers;

import com.example.WorldLangHubAPI.models.Resource;
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

    @GetMapping("/{name}") // Ваши URL-адреса должны быть организованы логически и представлять ресурсы, с которыми работает ваше приложение. Например, /languages для списка языков и /resources/{languageId} для ресурсов, связанных с определенным языком.
    public Resource getResourceByName(@PathVariable String name) {
        return resourceService.findResourceByName(name);
    }

    @GetMapping("/{id}")
    public Resource getResourceByName(@PathVariable int id) {
        return resourceService.findResourceById(id);
    }

    @GetMapping()
    public List<Resource> getResources() {
        return resourceService.findAllResources();
    }

    @PostMapping("/lang-id/{id}")
    public Resource createResourceWithLangId(@RequestBody Resource resource, @PathVariable int id) {
        return resourceService.save(resource, id);
    }

    @PostMapping("/lang-name/{name}")
    public Resource createResourceWithLangName(@RequestBody Resource resource, @PathVariable String name) {
        return resourceService.save(resource, name.toLowerCase());
    }

    @PutMapping("/{id}")
    public Resource updateResource(@RequestBody Resource updatedResource, @PathVariable int id) {
        return resourceService.update(updatedResource, id);
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable int id) {
        resourceService.deleteResourceById(id);
    }
}
