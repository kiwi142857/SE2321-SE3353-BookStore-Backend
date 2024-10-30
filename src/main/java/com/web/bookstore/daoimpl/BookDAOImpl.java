package com.web.bookstore.daoimpl;

import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.web.bookstore.dao.BookDAO;
import com.web.bookstore.dto.BookJsonDTO;
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
        return bookRepository.findByAuthorContainingAndStockGreaterThanPageable(author, stock, pageable);
    }

    @Override
    public Optional<Book> findById(Integer id) {
        String cacheKey = "book:" + id;
        Book cachedBook = null;
        try {
            // 尝试从Redis缓存中获取数据
            try {

                cachedBook = new Book((BookJsonDTO) redisTemplate.opsForValue().get(cacheKey));

            } catch (Exception e) {
                // 记录日志或处理异常
                System.err.println("Parse redis error: " + e.getMessage());
            }
            if (cachedBook != null) {
                System.out.println("Book cached in redis: " + cachedBook);

                return Optional.of(cachedBook);
            }
            System.out.println("Book not cached in redis: " + id);
        } catch (Exception e) {
            // 记录日志或处理异常
            System.err.println("Redis error: " + e.getMessage());
        }

        // 如果Redis不可用或缓存中没有数据，则从数据库中获取数据
        Optional<Book> bookFromDb = bookRepository.findById(id);
        bookFromDb.ifPresent(book -> {
            try {

                redisTemplate.opsForValue().set(cacheKey, new BookJsonDTO(book));
                System.out.println("Save book to redis: " + new BookJsonDTO(book));
            } catch (Exception e) {
                // 记录日志或处理异常
                System.err.println("Redis error: " + e.getMessage());
            }
        });
        System.out.println("Book from db: " + bookFromDb);
        return bookFromDb;
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
    @CachePut(value = "books", key = "#book.id")
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    // @CacheEvict(value = "books", key = "#book.id")
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
