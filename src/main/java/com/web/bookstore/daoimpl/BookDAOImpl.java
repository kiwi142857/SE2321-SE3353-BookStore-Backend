package com.web.bookstore.daoimpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.web.bookstore.dao.BookDAO;
import com.web.bookstore.model.Book;
import com.web.bookstore.repository.BookRepository;

@Service
public class BookDAOImpl implements BookDAO {

    private final BookRepository bookRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public BookDAOImpl(BookRepository bookRepository, RedisTemplate<String, Object> redisTemplate) {
        this.bookRepository = bookRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Page<Book> findByTitleContaining(String title, Pageable pageable) {
        return bookRepository.findByTitleContaining(title, pageable);
    }

    @Override
    public Page<Book> findByStockGreaterThanAndTitleContaining(Integer stock, String title, Pageable pageable) {
        return bookRepository.findByStockGreaterThanAndTitleContaining(stock, title, pageable);
    }

    @Override
    public Page<Book> findByAuthorContaining(String author, Pageable pageable) {
        return bookRepository.findByAuthorContaining(author, pageable);
    }

    @Override
    public Page<Book> findByAuthorContainingAndStockGreaterThanPageable(String author, Integer stock, Pageable pageable) {
        // 从Redis中获取书籍ID集合
        Set<Object> bookIds = redisTemplate.opsForSet().members("author:" + author);
        if (bookIds == null || bookIds.isEmpty()) {
            // 如果Redis中没有数据，从数据库中查询并存入Redis
            Page<Book> books = bookRepository.findByAuthorContainingAndStockGreaterThanPageable(author, stock, pageable);
            books.forEach(book -> {
                redisTemplate.opsForSet().add("author:" + author, book.getId());
                redisTemplate.opsForValue().set("book:" + book.getId(), book);
            });
            return books;
        } else {
            // 从Redis中获取书籍信息
            List<Book> books = bookIds.stream()
                    .map(id -> (Book) redisTemplate.opsForValue().get("book:" + id))
                    .filter(book -> book != null && book.getStock() > stock)
                    .collect(Collectors.toList());
            return new PageImpl<>(books, pageable, books.size());
        }
    }

    @Override
    @Cacheable(value = "books", key = "#id")
    public Optional<Book> findById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public Page<Book> findByTag(String tag, Pageable pageable) {
        return bookRepository.findByTag(tag, pageable);
    }

    @Override
    public Page<Book> findByTagAndStockGreaterThanPageable(String tag, Integer stock, Pageable pageable) {
        return bookRepository.findByTagAndStockGreaterThanPageable(tag, stock, pageable);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    @CachePut(value = "books", key = "#book.id")
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @CacheEvict(value = "books", key = "#book.id")
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Page<Book> findAllByOrderBySalesDesc(Pageable pageable) {
        return bookRepository.findAllByOrderBySalesDesc(pageable);
    }

    @Override
    public Optional<Book> findByIsbnAndIdNot(String isbn, Integer id) {
        return bookRepository.findByIsbnAndIdNot(isbn, id);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
}
