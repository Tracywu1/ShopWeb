package com.cc.po;

import java.io.InputStream;

public class MultipartFile {
    private String name;
    private String originalFilename;
    private String contentType;
    private long size;
    private InputStream inputStream;

    public MultipartFile(String name, String originalFilename, String contentType, long size, InputStream inputStream) {
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.size = size;
        this.inputStream = inputStream;
    }

    public String getName() {
        return name;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getContentType() {
        return contentType;
    }

    public long getSize() {
        return size;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
