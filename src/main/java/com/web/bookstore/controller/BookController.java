package com.web.bookstore.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.bookstore.service.BookService;
import com.web.bookstore.dto.GetBookDetailDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.model.Book;
import com.web.bookstore.dto.GetBookListDTO;

import javax.swing.text.html.Option;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable Integer id) {
        try {
            Optional<Book> book = bookService.getBookById(id);
            if (book.isPresent()) {
                return ResponseEntity.ok(new GetBookDetailDTO(book.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Book not found"));
            }
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

    @GetMapping("rank")
    public ResponseEntity<Object> getRankList(
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "0") Integer pageIndex) {
        try {
            return ResponseEntity.ok(bookService.getRankList(pageSize, pageIndex));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

    // 获取用户对书籍的评分，需要token来验证用户身份
    @GetMapping("/{id}/rate")
    public ResponseEntity<Object> getBookRate(@PathVariable Integer id,
            @CookieValue(value = "token") String token) {
        try {
            return ResponseEntity.ok(bookService.getBookRate(token, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<Object> rateBook(@PathVariable Integer id, @CookieValue(value = "token") String token,
            @RequestParam Integer value) {
        try {
            return ResponseEntity.ok(bookService.rateBook(token, id, value));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }
}
