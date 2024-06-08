package com.web.bookstore.dao;

import com.web.bookstore.model.Book;
import com.web.bookstore.model.BookRate;
import com.web.bookstore.model.User;

import java.util.Optional;

public interface BookRateDAO {

    Optional<BookRate> findByUserAndBook(User user, Book book);

    void save(BookRate bookRate);
}