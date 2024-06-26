package com.web.bookstore.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class CosService {
    @Value("${cos.secretId}")
    private String secretId;

    @Value("${cos.secretKey}")
    private String secretKey;

    @Value("${cos.region}")
    private String regionName;

    @Value("${cos.bucketName}")
    private String bucketName;

    public String uploadImage(MultipartFile file) throws IOException {
        // 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        Region region = new Region(regionName);
        // ClientConfig中包含了设置region, https(默认http)等参数。
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议再设置一个自定义的文件名，确保唯一性
        String key = "images/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        COSClient cosClient = new COSClient(cred, clientConfig);
        try {
            File localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            cosClient.putObject(putObjectRequest);
            return "https://" + bucketName + ".cos." + regionName + ".myqcloud.com/" + key;
        } finally {
            cosClient.shutdown();
        }
    }
}