package com.sto.service;

import org.springframework.core.io.FileSystemResource;

public interface FileResourceService {
    FileSystemResource getByTitle(String title);
}
