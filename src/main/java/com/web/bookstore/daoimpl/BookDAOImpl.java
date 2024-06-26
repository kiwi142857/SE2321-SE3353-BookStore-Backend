package com.web.bookstore.daoimpl;

import com.web.bookstore.model.Book;
import com.web.bookstore.dao.BookDAO;
import com.web.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class BookDAOImpl implements BookDAO {

    private final BookRepository bookRepository;

    public BookDAOImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> findByTitleContaining(String title, Pageable pageable) {
        return bookRepository.findByTitleContaining(title, pageable);
    }

    public Page<Book> findByStockGreaterThanAndTitleContaining(Integer stock, String title, Pageable pageable) {
        return bookRepository.findByStockGreaterThanAndTitleContaining(stock, title, pageable);
    }

    public Page<Book> findByAuthorContaining(String author, Pageable pageable) {
        return bookRepository.findByAuthorContaining(author, pageable);
    }

    public Page<Book> findByAuthorContainingAndStockGreaterThanPageable(String author, Integer stock,
            Pageable pageable) {
        return bookRepository.findByAuthorContainingAndStockGreaterThanPageable(author, stock, pageable);
    }

    public Optional<Book> findById(Integer id) {
        return bookRepository.findById(id);
    }

    public Page<Book> findByTag(String tag, Pageable pageable) {
        return bookRepository.findByTag(tag, pageable);
    }

    public Page<Book> findByTagAndStockGreaterThanPageable(String tag, Integer stock, Pageable pageable) {
        return bookRepository.findByTagAndStockGreaterThanPageable(tag, stock, pageable);
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public Page<Book> findAllByOrderBySalesDesc(Pageable pageable) {
        return bookRepository.findAllByOrderBySalesDesc(pageable);
    }

}