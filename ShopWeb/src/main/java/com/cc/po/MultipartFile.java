package com.cc.po;

import java.io.InputStream;

/**
 * @author 32119
 */
public class MultipartFile {
    /**
     * 上传文件的表单字段的名称
     */
    private final String name;
    /**
     * 上传文件的原始文件名(表单字段名和原始文件名不一定是同一件事。
     * 例如，用户可以从名为file的表单字段上传一个名为document.pdf的文件。
     * 在本例中，originalFilename字段将是document.pdf，而name字段将是file。)
     */
    private final String originalFilename;
    /**
     * 上传文件的内容类型。内容类型是描述文件格式的MIME类型，例如png图像对应image/png, pdf文件对应application/pdf
     */
    private final String contentType;
    /**
     * 上传文件的大小
     */
    private final long size;
    /**
     * 上传文件的inputStream对象。InputStream可用于从服务器的文件系统或内存中读取上传文件的内容
     */
    private final InputStream inputStream;

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
