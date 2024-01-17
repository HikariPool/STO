package com.flat.controller.rest;

import com.flat.service.FileResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload")
public class SourceController {
    @Autowired
    private FileResourceService fileResourceService;


    @GetMapping("/{source}")
    public FileSystemResource get(@PathVariable String source) {
        return fileResourceService.getByTitle(source);
    }
}