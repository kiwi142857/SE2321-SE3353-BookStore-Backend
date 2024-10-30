package com.web.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.web.bookstore.model.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

    Page<Book> findByTitleContaining(String title, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.stock > :stock AND b.title LIKE %:title%")
    Page<Book> findByStockGreaterThanAndTitleContaining(Integer stock, String title, Pageable pageable);

    Page<Book> findByAuthorContaining(String author, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.author LIKE %:author% AND b.stock > :stock")
    Page<Book> findByAuthorContainingAndStockGreaterThanPageable(String author, Integer stock, Pageable pageable);

    Optional<Book> findById(Integer id);

    Page<Book> findByTag(String tag, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.tag = :tag AND b.stock > :stock")
    Page<Book> findByTagAndStockGreaterThanPageable(String tag, Integer stock, Pageable pageable);

    Optional<Book> findByIsbnAndIdNot(String isbn, Integer id);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findAll();

    Book save(Book book);

    void delete(Book book);

    Page<Book> findAllByOrderBySalesDesc(Pageable pageable);
}
