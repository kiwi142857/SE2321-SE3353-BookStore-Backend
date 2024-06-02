package com.web.bookstore.repository;

import com.web.bookstore.model.Book;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
    Page<Book> findByTitleContaining(String title, Pageable pageable);

    Page<Book> findByAuthorContaining(String author, Pageable pageable);

    Optional<Book> findById(Integer id);

    Page<Book> findByTag(String tag, Pageable pageable);

    Book save(Book book);

    void delete(Book book);

    Page<Book> findAllByOrderBySalesDesc(Pageable pageable);
}