package com.web.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.bookstore.dto.ImageUpLoadDTO;
import com.web.bookstore.exception.UserBannedException;
import com.web.bookstore.model.Book;
import com.web.bookstore.model.User;
import com.web.bookstore.service.BookService;
import com.web.bookstore.service.CosService;
import com.web.bookstore.service.UserService;
import com.web.bookstore.util.SessionUtils;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/image")
public class ImageUploadController {

    private CosService cosService;

    private UserService userService;
    private BookService bookService;

    public ImageUploadController(CosService cosService, UserService userService, BookService bookService) {
        this.cosService = cosService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping(value = "/upload", consumes = { "multipart/form-data" })
    public ResponseEntity<Object> uploadImage(@RequestPart("image") MultipartFile image) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            if (user.getStatus() == 1) {
                throw new UserBannedException("您的账号已被禁用");
            }
            if (user.getRole() == 0) {
                throw new Exception("Permission denied");
            }
            String url = cosService.uploadImage(image);

            ImageUpLoadDTO imageUpLoadDTO = new ImageUpLoadDTO();
            imageUpLoadDTO.setImageUrl(url);
            return ResponseEntity.ok(imageUpLoadDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Upload failed");
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

    @PostMapping(value = "/book/cover/{id}", consumes = { "multipart/form-data" })
    public ResponseEntity<Object> uploadBookProfile(@RequestPart("image") MultipartFile image,
            @PathVariable Integer id) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            if (user.getStatus() == 1) {
                throw new UserBannedException("您的账号已被禁用");
            }
            if (user.getRole() == 0) {
                throw new Exception("Permission denied");
            }
            Book book = bookService.getBookById(id).get();
            if (book == null) {
                return ResponseEntity.badRequest().body("Book not found");
            }
            String url = cosService.uploadImage(image);
            // 这里可以将URL保存到数据库中
            book.setCover(url);
            bookService.updateBook(book);
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