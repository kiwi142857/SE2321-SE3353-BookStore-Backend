package com.web.bookstore.dao;

import com.web.bookstore.model.Book;
import com.web.bookstore.model.User;
import com.web.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class BookDAO {

    private final BookRepository bookRepository;

    public BookDAO(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> findByTitleContaining(String title, Pageable pageable) {
        return bookRepository.findByTitleContaining(title, pageable);
    }

    public Page<Book> findByAuthorContaining(String author, Pageable pageable) {
        return bookRepository.findByAuthorContaining(author, pageable);
    }

    public Optional<Book> findById(Integer id) {
        return bookRepository.findById(id);
    }

    public Page<Book> findByTag(String tag, Pageable pageable) {
        return bookRepository.findByTag(tag, pageable);
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Page<Book> findAllByOrderBySalesDesc(Pageable pageable) {
        return bookRepository.findAllByOrderBySalesDesc(pageable);
    }
}