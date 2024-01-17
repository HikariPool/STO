package com.flat.service;

import org.springframework.core.io.FileSystemResource;

public interface FileResourceService {
    FileSystemResource getByTitle(String title);
}
