package com.web.bookstore.dao;

import com.web.bookstore.model.Book;
import com.web.bookstore.model.User;
import com.web.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDAO {

    private final BookRepository bookRepository;

    public BookDAO(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findByTitleContaining(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    public List<Book> findByAuthorContaining(String author) {
        return bookRepository.findByAuthorContaining(author);
    }

    public Optional<Book> findById(Integer id) {
        return bookRepository.findById(id);
    }

    public List<Book> findByTag(String tag) {
        return bookRepository.findByTag(tag);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }
}