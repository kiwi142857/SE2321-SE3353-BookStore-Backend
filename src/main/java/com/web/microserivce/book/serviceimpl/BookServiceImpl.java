package com.web.microserivce.book.serviceimpl;

import org.springframework.stereotype.Service;

import com.web.microserivce.book.dao.BookDAO;
import com.web.microserivce.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public String getAuthor(String bookName) {
        return bookDAO.getAuthor(bookName);
    }
}
