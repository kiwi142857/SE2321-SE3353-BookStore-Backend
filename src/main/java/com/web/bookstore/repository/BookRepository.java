package com.web.bookstore.repository;

import com.web.bookstore.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContaining(String title);

    List<Book> findByAuthorContaining(String author);

    Optional<Book> findById(Integer id);

    List<Book> findByTag(String tag);
}