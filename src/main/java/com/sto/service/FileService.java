package com.sto.service;

public interface FileService {
    String write(byte[] bytes, String memType);

    String getMemType(String fileTitle);
}
