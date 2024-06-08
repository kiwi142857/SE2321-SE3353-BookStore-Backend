package com.web.bookstore.dao;

import com.web.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookDAO {

    Page<Book> findByTitleContaining(String title, Pageable pageable);

    Page<Book> findByAuthorContaining(String author, Pageable pageable);

    Optional<Book> findById(Integer id);

    Page<Book> findByTag(String tag, Pageable pageable);

    Page<Book> findAll(Pageable pageable);

    Book save(Book book);

    void delete(Book book);

    Page<Book> findAllByOrderBySalesDesc(Pageable pageable);
}