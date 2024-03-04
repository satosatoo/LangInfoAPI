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

    @GetMapping("/{name}")
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

    @PostMapping("/id/{id}")
    public Resource createResourceWithId(@RequestBody Resource resource, @PathVariable int id) {
        return resourceService.saveWithId(resource, id);
    }

    @PostMapping("/name/{name}")
    public Resource createResourceWithName(@RequestBody Resource resource, @PathVariable String name) {
        return resourceService.saveWithName(resource, name);
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
