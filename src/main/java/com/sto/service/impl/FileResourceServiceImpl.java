package com.sto.service.impl;

import com.sto.data.constants.Constants;
import com.sto.service.FileResourceService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileResourceServiceImpl implements FileResourceService {

    public FileSystemResource getByTitle(String title) {
        return new FileSystemResource(new File(Constants.UPLOAD_PATH + title));
    }
}
