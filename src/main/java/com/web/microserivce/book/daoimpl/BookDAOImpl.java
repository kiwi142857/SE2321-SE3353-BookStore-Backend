package com.web.microserivce.book.daoimpl;

import org.springframework.stereotype.Service;

import com.web.microserivce.book.dao.BookDAO;
import com.web.microserivce.book.repository.BookRepository;

@Service
public class BookDAOImpl implements BookDAO {

    private final BookRepository bookRepository;

    public BookDAOImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public String getAuthor(String bookName) {
        return bookRepository.findByTitle(bookName).getAuthor();
    }

}
