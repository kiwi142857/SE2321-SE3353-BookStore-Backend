package com.web.bookstore.serviceimpl;

import java.util.NoSuchElementException;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.web.bookstore.dto.GetBookDetailDTO;
import com.web.bookstore.repository.BookRepository;
import com.web.bookstore.service.BookService;
import com.web.bookstore.dto.BookBreifDTO;
import com.web.bookstore.dto.GetBookListDTO;
import com.web.bookstore.model.Book;
import com.web.bookstore.model.BookRate;

import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import java.util.Optional;

import com.web.bookstore.dto.GetBookRateDTO;
import com.web.bookstore.dto.GetCommentListDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.repository.BookRateRepository;
import com.web.bookstore.service.AuthService;
import com.web.bookstore.model.User;
import com.web.bookstore.model.Comment;
import com.web.bookstore.dao.BookDAO;
import com.web.bookstore.dao.BookRateDAO;
import com.web.bookstore.dao.AuthDAO;
import com.web.bookstore.dto.PostBookDTO;

@Service
public class BookServiceImpl implements BookService {

    public final BookDAO bookDAO;
    public final BookRateDAO bookRateDAO;
    public final AuthService authService;

    public BookServiceImpl(BookDAO bookDAO, BookRateDAO bookRateDAO, AuthService authService) {
        this.bookDAO = bookDAO;
        this.bookRateDAO = bookRateDAO;
        this.authService = authService;
    }

    public Optional<Book> getBookById(Integer id) {
        return bookDAO.findById(id);
    }

    public GetBookListDTO searchBooks(String searchType, String keyWord, Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Book> bookPage;
        if (searchType.equals("title")) {
            bookPage = bookDAO.findByTitleContaining(keyWord, pageable);
        } else if (searchType.equals("tag")) {
            bookPage = bookDAO.findByTag(keyWord, pageable);
        } else {
            bookPage = bookDAO.findByAuthorContaining(keyWord, pageable);
        }
        // System.out.println(bookList);
        List<Book> bookList = bookPage.getContent();
        List<BookBreifDTO> bookBreifDTOList = bookList.stream().map(BookBreifDTO::new).collect(Collectors.toList());
        // System.out.println(bookBreifDTOList);
        return new GetBookListDTO(bookBreifDTOList, Math.toIntExact(bookPage.getTotalElements()));
    }

    public GetBookListDTO getRankList(Integer pageSize, Integer sizeIndex) {
        PageRequest pageRequest = PageRequest.of(sizeIndex, pageSize, Sort.by(Sort.Direction.DESC, "sales"));
        Page<Book> bookPage = bookDAO.findAllByOrderBySalesDesc(pageRequest);
        List<Book> bookList = bookPage.getContent();
        List<BookBreifDTO> bookBreifDTOList = bookList.stream().map(BookBreifDTO::new).collect(Collectors.toList());
        return new GetBookListDTO(bookBreifDTOList, pageSize);
    }

    public GetBookRateDTO getBookRate(User user, Integer bookId) {
        Optional<Book> book = bookDAO.findById(bookId);
        if (book.isEmpty()) {
            throw new NoSuchElementException("Book not found");
        }

        Optional<BookRate> bookRate = bookRateDAO.findByUserAndBook(user, book.get());
        if (bookRate.isEmpty()) {
            return new GetBookRateDTO(0);
        }
        return new GetBookRateDTO(bookRate.get().getRate());
    }

    public ResponseDTO rateBook(User user, Integer bookId, Integer rate) {
        Optional<Book> book = bookDAO.findById(bookId);
        if (book.isEmpty()) {
            throw new NoSuchElementException("Book not found");
        }
        System.out.println("rate: " + rate);
        System.out.println("userId: " + user.getId());
        Optional<BookRate> bookRate = bookRateDAO.findByUserAndBook(user, book.get());
        if (bookRate.isEmpty()) {
            // 对书籍的评分更新
            book.get().addRate(rate);
            bookRateDAO.save(new BookRate(user, book.get(), rate));
        } else {
            book.get().updateRate(bookRate.get().getRate(), rate);
            bookRate.get().setRate(rate);
            bookRateDAO.save(bookRate.get());
        }

        return new ResponseDTO(true, "Rate success");
    }

    public GetCommentListDTO getCommentList(Integer bookId, Integer pageSize, Integer pageIndex) {
        Optional<Book> book = bookDAO.findById(bookId);
        if (book.isEmpty()) {
            throw new NoSuchElementException("Book not found");
        }
        Integer total = book.get().getComments().size();
        List<Comment> test_comments = book.get().getComments();
        for (Comment comment : test_comments) {
            System.out.println(comment);
        }
        List<Comment> comments = book.get().getComments().stream().skip((pageIndex) * pageSize).limit(pageSize)
                .collect(Collectors.toList());
        System.out.println(comments);
        return new GetCommentListDTO(total, comments);
    }

    public ResponseDTO addComment(User user, Integer bookId, String content) {
        Optional<Book> book = bookDAO.findById(bookId);
        if (book.isEmpty()) {
            throw new NoSuchElementException("Book not found");
        }
        // 评论内容不能为空
        if (content == null || content.equals("")) {
            throw new IllegalArgumentException("Comment content can not be empty");
        }
        Comment comment = new Comment(content, book.get(), user);
        book.get().getComments().add(comment);
        bookDAO.save(book.get());
        return new ResponseDTO(true, "Comment success");
    }

    public ResponseDTO replyComment(User user, Integer bookId, String content, String reply) {
        Optional<Book> book = bookDAO.findById(bookId);
        if (book.isEmpty()) {
            throw new NoSuchElementException("Book not found");
        }

        // 评论内容不能为空
        if (content == null || content.equals("")) {
            throw new IllegalArgumentException("Comment content can not be empty");
        }
        Comment comment = new Comment(content, book.get(), user, reply);
        book.get().getComments().add(comment);
        bookDAO.save(book.get());
        return new ResponseDTO(true, "Comment success");
    }

    public void updateBook(Book book) {
        bookDAO.save(book);
    }

    public ResponseDTO postBook(Integer id, PostBookDTO book) {
        Optional<Book> bookOptional = bookDAO.findById(id);
        if (bookOptional.isEmpty()) {
            return new ResponseDTO(false, "Book not found");
        }
        Book bookToUpdate = bookOptional.get();
        bookToUpdate.updateBook(book);
        bookDAO.save(bookToUpdate);
        return new ResponseDTO(true, "Update success");
    }

    public ResponseDTO addBook(Integer id, PostBookDTO book) {
        Book newBook = new Book(book);
        bookDAO.save(newBook);
        return new ResponseDTO(true, "Add success");
    }

    public ResponseDTO deleteBook(Integer id) {
        Optional<Book> book = bookDAO.findById(id);
        if (book.isEmpty()) {
            return new ResponseDTO(false, "Book not found");
        }
        bookDAO.delete(book.get());
        return new ResponseDTO(true, "Delete success");
    }
}
