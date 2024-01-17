package com.flat.service.impl;

import com.flat.data.constants.Constants;
import com.flat.service.FileResourceService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileResourceServiceImpl implements FileResourceService {

    public FileSystemResource getByTitle(String title) {
        return new FileSystemResource(new File(Constants.UPLOAD_PATH + title));
    }
}
