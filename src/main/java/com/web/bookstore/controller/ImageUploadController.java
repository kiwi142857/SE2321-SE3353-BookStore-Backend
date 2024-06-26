package com.web.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.bookstore.service.CosService;

@RestController
public class ImageUploadController {

    @Autowired
    private CosService cosService;

    @PostMapping(value = "/uploadImage", consumes = { "multipart/form-data" })
    public String uploadImage(@RequestPart("image") MultipartFile image) {
        try {
            String url = cosService.uploadImage(image);
            // 这里可以将URL保存到数据库中
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return "Upload failed";
        }
    }
}