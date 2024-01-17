package com.flat.service.impl;

import com.flat.data.constants.Constants;
import com.flat.service.FileService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @SneakyThrows
    @Override
    public String write(byte[] bytes, String memType) {
        File file = new File(Constants.UPLOAD_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        String fileTitle = UUID.randomUUID() + memType;

        FileOutputStream output = new FileOutputStream(Constants.UPLOAD_PATH + fileTitle);
        output.write(bytes);
        output.close();

        return fileTitle;
    }
}
