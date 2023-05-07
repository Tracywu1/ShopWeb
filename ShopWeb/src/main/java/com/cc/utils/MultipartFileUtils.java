package com.cc.utils;

import com.cc.po.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author 32119
 */
public class MultipartFileUtils {

    public static MultipartFile parseMultipartFile(HttpServletRequest request, String paramName) throws IOException, ServletException {
        Objects.requireNonNull(request, "HttpServletRequest cannot be null");
        Objects.requireNonNull(paramName, "paramName cannot be null");

        // Retrieve the multipart file part from the request
        Part filePart = request.getPart(paramName);

        // Extract the name and content type of the file from the Content-Disposition header
        String contentDispositionHeader = filePart.getHeader("Content-Disposition");
        String[] contentDispositionHeaderParts = contentDispositionHeader.split(";");

        String name = null;
        String originalFilename = null;
        for (String part : contentDispositionHeaderParts) {
            if (part.trim().startsWith("name=")) {
                name = part.substring(part.indexOf('=') + 1).trim().replace("\"", "");
            } else if (part.trim().startsWith("filename=")) {
                originalFilename = part.substring(part.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        // Extract the content type of the file from the Content-Type header
        String contentType = filePart.getContentType();

        // Extract the size and input stream of the file from the part
        long size = filePart.getSize();
        InputStream inputStream = filePart.getInputStream();

        // Create and return the MultipartFile object
        return new MultipartFile(name, originalFilename, contentType, size, inputStream);
    }
}
