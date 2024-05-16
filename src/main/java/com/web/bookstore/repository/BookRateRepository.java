package com.web.bookstore.repository;

import com.web.bookstore.model.Book;
import com.web.bookstore.model.BookRate;
import com.web.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRateRepository extends JpaRepository<BookRate, Integer> {
    Optional<BookRate> findByUserAndBook(User user, Book book);
}