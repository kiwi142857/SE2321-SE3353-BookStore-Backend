package com.web.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.bookstore.dto.ImageUpLoadDTO;
import com.web.bookstore.exception.UserBannedException;
import com.web.bookstore.model.User;
import com.web.bookstore.service.CosService;
import com.web.bookstore.service.UserService;
import com.web.bookstore.util.SessionUtils;

@RestController
@RequestMapping("/api/image")
public class ImageUploadController {

    private CosService cosService;

    private UserService userService;

    public ImageUploadController(CosService cosService, UserService userService) {
        this.cosService = cosService;
        this.userService = userService;
    }

    @PostMapping(value = "/upload", consumes = { "multipart/form-data" })
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

    @PostMapping(value = "/user/profile", consumes = { "multipart/form-data" })
    public ResponseEntity<Object> uploadUserProfile(@RequestPart("image") MultipartFile image) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            if (user.getStatus() == 1) {
                throw new UserBannedException("您的账号已被禁用");
            }
            String url = cosService.uploadImage(image);
            // 这里可以将URL保存到数据库中
            user.setAvatar(url);
            userService.save(user);
            ImageUpLoadDTO imageUpLoadDTO = new ImageUpLoadDTO();
            imageUpLoadDTO.setImageUrl(url);
            return ResponseEntity.ok(imageUpLoadDTO);
        } catch (UserBannedException e) {
            return ResponseEntity.status(403).body("User banned");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Upload failed");
        }
    }

    @PostMapping(value = "/book/{id}/cover", consumes = { "multipart/form-data" })
    public ResponseEntity<Object> uploadBookProfile(@RequestPart("image") MultipartFile image) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            if (user.getStatus() == 1) {
                throw new UserBannedException("您的账号已被禁用");
            }
            String url = cosService.uploadImage(image);
            // 这里可以将URL保存到数据库中
            user.setAvatar(url);
            userService.save(user);
            ImageUpLoadDTO imageUpLoadDTO = new ImageUpLoadDTO();
            imageUpLoadDTO.setImageUrl(url);
            return ResponseEntity.ok(imageUpLoadDTO);
        } catch (UserBannedException e) {
            return ResponseEntity.status(403).body("User banned");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Upload failed");
        }
    }
}