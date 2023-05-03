package com.cc.controller;

import com.alibaba.fastjson.JSON;
import com.cc.exception.MyException;
import com.cc.exception.Result;
import com.cc.exception.ResultCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.UUID;

@WebServlet("/upload/*")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UploadServlet extends BaseServlet{
    private static final String FILE_UPLOAD_DIR = "/path/to/file/upload/dir/";
    /**
     * 上传文件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        // 获取上传的文件数据(getPart:用于获取使用multipart/form-data格式传递的http请求的请求体，通常用于获取上传文件。)
        Part filePart = request.getPart("file");
        // 获取文件名
        String fileName = filePart.getSubmittedFileName();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        if (!".jpg".equalsIgnoreCase(suffixName) && !".png".equalsIgnoreCase(suffixName)) {
            Result result = Result.error(ResultCode.WRONG_FILE_TYPE);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }

        if (filePart.getSize() == 0) {
            Result result = Result.error(ResultCode.EMPTY_FILE);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }
        try {
            // 生成文件名称UUID
            UUID uuid = UUID.randomUUID();
            String newFileName = uuid + suffixName;
            // 创建文件
            File fileDirectory = new File(FILE_UPLOAD_DIR);
            File destFile = new File(FILE_UPLOAD_DIR + newFileName);
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new MyException(ResultCode.MKDIR_FAILED);
                }
            }
            // 获取文件内容输入流
            inputStream = filePart.getInputStream();
            // 创建文件输出流
            outputStream = Files.newOutputStream(destFile.toPath());
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // 写入文件内容
                outputStream.write(buffer, 0, bytesRead);
            }
            // 返回上传成功的文件URL
            String fileUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "")
                    + "/images/" + newFileName;
            //响应成功标识
            Result<String> result = Result.success(fileUrl);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException e) {
            Result result = Result.error(ResultCode.UPLOAD_FAILED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(result));
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
