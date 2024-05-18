package com.web.bookstore.dao;

import com.web.bookstore.model.Book;
import com.web.bookstore.model.BookRate;
import com.web.bookstore.model.User;
import com.web.bookstore.repository.BookRateRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookRateDAO {

    private final BookRateRepository bookRateRepository;

    public BookRateDAO(BookRateRepository bookRateRepository) {
        this.bookRateRepository = bookRateRepository;
    }

    public Optional<BookRate> findByUserAndBook(User user, Book book) {
        return bookRateRepository.findByUserAndBook(user, book);
    }

    public void save(BookRate bookRate) {
        bookRateRepository.save(bookRate);
    }
}
