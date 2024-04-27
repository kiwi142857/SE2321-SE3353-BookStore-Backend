package com.web.bookstore.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.bookstore.service.BookService;
import com.web.bookstore.dto.ResponseDTO;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(bookService.getBookById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @GetMapping("search")
    public ResponseEntity<Object> searchBook(@RequestParam String searchType, @RequestParam String keyWord,
            @RequestParam Integer pageSize, @RequestParam Integer pageIndex) {
        try {
            return ResponseEntity.ok(bookService.searchBooks(searchType, keyWord, pageIndex, pageSize));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }
}
