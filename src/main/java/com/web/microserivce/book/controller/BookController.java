package com.web.microserivce.book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.microserivce.book.dto.AuthorDTO;
import com.web.microserivce.book.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 从url获取书名，返回对应的作者
    @GetMapping("/getAuthor")
    public ResponseEntity<Object> getAuthor(@RequestParam String bookName) {
        return ResponseEntity.ok(new AuthorDTO(bookService.getAuthor(bookName)));
    }
}
