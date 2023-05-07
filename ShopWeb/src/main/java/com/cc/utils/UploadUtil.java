package com.cc.utils;

import com.cc.po.MultipartFile;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;

import java.util.UUID;

public class UploadUtil {
    //存储桶域名
    public static final String Tencent_DOMAIN = "https://ccwu-1316557530.cos.ap-guangzhou.myqcloud.com/";

    public static String uploadImage(MultipartFile file) {
        //生成文件名
        String originalFilename = file.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + suffixName;

        String accessKeyId = "*";
        String accessKeySecret = "*";
        COSCredentials cred = new BasicCOSCredentials(accessKeyId, accessKeySecret);
        //COS客户端对象
        COSClient cosClient = new COSClient(cred, new ClientConfig(new Region("ap-guangzhou")));
        ObjectMetadata metadata = new ObjectMetadata();

        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        metadata.setContentDisposition("inline;filename=" + file.getOriginalFilename());
        cosClient.putObject("ccwu-1316557530", fileName, file.getInputStream(), metadata);
        cosClient.shutdown();

        return Tencent_DOMAIN + fileName;
    }
}
