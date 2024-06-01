package com.web.bookstore.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.bookstore.service.BookService;
import com.web.bookstore.service.UserService;
import com.web.bookstore.util.SessionUtils;
import com.web.bookstore.dto.GetBookDetailDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.model.Book;
import com.web.bookstore.dto.GetBookListDTO;
import com.web.bookstore.dto.GetBookRateDTO;
import com.web.bookstore.dto.CommentRequestDTO;
import com.web.bookstore.dto.PostBookDTO;

import javax.swing.text.html.Option;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.web.bookstore.model.User;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
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

    // 修改/新增书籍信息
    @PostMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable Integer id, @RequestBody PostBookDTO book) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            // check user's role
            if (user.getRole() == 0) {
                throw new Exception("Permission denied");
            }
            // 如果id 是-1，说明是新书
            if (id == -1) {
                return ResponseEntity.ok(bookService.addBook(id, book));
            }
            return ResponseEntity.ok(bookService.postBook(id, book));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

    // 删除书籍
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Integer id) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            // check user's role
            if (user.getRole() == 0) {
                throw new Exception("Permission denied");
            }
            return ResponseEntity.ok(bookService.deleteBook(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchBook(@RequestParam String searchType, @RequestParam String keyWord,
            @RequestParam Integer pageSize, @RequestParam Integer pageIndex) {
        if (pageIndex < 0 || pageSize <= 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(false, "Invalid page index or page size"));
        try {
            return ResponseEntity.ok(bookService.searchBooks(searchType, keyWord, pageIndex, pageSize));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @GetMapping("/rank")
    public ResponseEntity<Object> getRankList(
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "0") Integer pageIndex) {
        try {
            return ResponseEntity.ok(bookService.getRankList(pageSize, pageIndex));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @GetMapping("/{id}/rate")
    public ResponseEntity<Object> getBookRate(@PathVariable Integer id) {
        try {

            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            return ResponseEntity.ok(bookService.getBookRate(user, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<Object> rateBook(@PathVariable Integer id,
            @RequestParam Integer rate) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            System.out.println("userId: " + user.getId());
            return ResponseEntity.ok(bookService.rateBook(user, id, rate));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Object> getBookComments(@PathVariable Integer id,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "0") Integer pageIndex) {
        try {
            return ResponseEntity.ok(bookService.getCommentList(id, pageSize, pageIndex));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Object> addBookComment(@PathVariable Integer id,
            @RequestBody CommentRequestDTO request) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = userService.findUserById(sessionUser.getId());
            return ResponseEntity.ok(bookService.addComment(user, id, request.getContent()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, e.getMessage()));
        }
    }

}
