package com.web.bookstore.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
        initializeCache();
    }

    private void initializeCache() {
        System.out.println("Initializing cache...");
        try {
            List<Book> books = bookRepository.findAll();
            System.out.println("Books found: " + books.size());
            books.forEach(book -> {
                String cacheKey = "book:" + book.getId();
                try {
                    redisTemplate.opsForValue().set(cacheKey, new BookJsonDTO(book));
                    System.out.println("Cached book in redis");
                } catch (Exception e) {
                    System.err.println("Redis error: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            System.err.println("Error initializing cache: " + e.getMessage());
        }
    }

    public Page<Book> findByTitleContaining(String title, Pageable pageable) {
        List<Book> books = new ArrayList<>();
        try {
            // 获取所有键
            Set<String> keys = redisTemplate.keys("book:*");
            if (keys != null) {
                for (String key : keys) {
                    // 获取缓存中的 BookJsonDTO 对象
                    BookJsonDTO cachedBookDTO = (BookJsonDTO) redisTemplate.opsForValue().get(key);
                    if (cachedBookDTO != null && cachedBookDTO.getTitle().contains(title)) {
                        // 将 DTO 转换为实体对象并添加到列表中
                        books.add(new Book(cachedBookDTO));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Redis error: " + e.getMessage());
        }

        // 分页处理
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), books.size());
        List<Book> pagedBooks = books.subList(start, end);

        // 如果缓存中没有数据，则从数据库中读取
        if (pagedBooks.isEmpty()) {
            Page<Book> bookPage = bookRepository.findByTitleContaining(title, pageable);
            // 将数据库中的数据缓存到 Redis
            bookPage.forEach(book -> {
                try {
                    redisTemplate.opsForValue().set("book:" + book.getId(), new BookJsonDTO(book));
                } catch (Exception e) {
                    System.err.println("Redis error: " + e.getMessage());
                }
            });
            return bookPage;
        }

        // 返回分页结果
        return new PageImpl<>(pagedBooks, pageable, books.size());
    }

    @Override
    public Page<Book> findByStockGreaterThanAndTitleContaining(Integer stock, String title, Pageable pageable) {
        List<Book> books = new ArrayList<>();
        try {
            // 获取所有键
            Set<String> keys = redisTemplate.keys("book:*");
            if (keys != null) {
                for (String key : keys) {
                    // 获取缓存中的 BookJsonDTO 对象
                    BookJsonDTO cachedBookDTO = (BookJsonDTO) redisTemplate.opsForValue().get(key);
                    if (cachedBookDTO != null && cachedBookDTO.getTitle().contains(title) && cachedBookDTO.getStock() > stock) {
                        // 将 DTO 转换为实体对象并添加到列表中
                        books.add(new Book(cachedBookDTO));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Redis error: " + e.getMessage());
        }

        // 分页处理
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), books.size());
        List<Book> pagedBooks = books.subList(start, end);

        System.out.println("Read from cache: " + pagedBooks.size());

        // 如果缓存中没有数据，则从数据库中读取
        if (pagedBooks.isEmpty()) {
            Page<Book> bookPage = bookRepository.findByStockGreaterThanAndTitleContaining(stock, title, pageable);
            // 将数据库中的数据缓存到 Redis
            bookPage.forEach(book -> {
                try {
                    redisTemplate.opsForValue().set("book:" + book.getId(), new BookJsonDTO(book));
                } catch (Exception e) {
                    System.err.println("Redis error: " + e.getMessage());
                }
            });
            return bookPage;
        }

        // 返回分页结果
        return new PageImpl<>(pagedBooks, pageable, books.size());
    }

    @Override
    public Page<Book> findByAuthorContaining(String author, Pageable pageable) {
        List<Book> books = new ArrayList<>();
        try {
            // 获取所有键
            Set<String> keys = redisTemplate.keys("book:*");
            if (keys != null) {
                for (String key : keys) {
                    // 获取缓存中的 BookJsonDTO 对象
                    BookJsonDTO cachedBookDTO = (BookJsonDTO) redisTemplate.opsForValue().get(key);
                    if (cachedBookDTO != null && cachedBookDTO.getAuthor().contains(author)) {
                        // 将 DTO 转换为实体对象并添加到列表中
                        books.add(new Book(cachedBookDTO));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Redis error: " + e.getMessage());
        }

        // 分页处理
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), books.size());
        List<Book> pagedBooks = books.subList(start, end);

        // 如果缓存中没有数据，则从数据库中读取
        if (pagedBooks.isEmpty()) {
            Page<Book> bookPage = bookRepository.findByAuthorContaining(author, pageable);
            // 将数据库中的数据缓存到 Redis
            bookPage.forEach(book -> {
                try {
                    redisTemplate.opsForValue().set("book:" + book.getId(), new BookJsonDTO(book));
                } catch (Exception e) {
                    System.err.println("Redis error: " + e.getMessage());
                }
            });
            return bookPage;
        }

        // 返回分页结果
        return new PageImpl<>(pagedBooks, pageable, books.size());
    }

    @Override
    public Page<Book> findByAuthorContainingAndStockGreaterThanPageable(String author, Integer stock, Pageable pageable) {
        List<Book> books = new ArrayList<>();
        try {
            // 获取所有键
            Set<String> keys = redisTemplate.keys("book:*");
            if (keys != null) {
                for (String key : keys) {
                    // 获取缓存中的 BookJsonDTO 对象
                    BookJsonDTO cachedBookDTO = (BookJsonDTO) redisTemplate.opsForValue().get(key);
                    if (cachedBookDTO != null && cachedBookDTO.getAuthor().contains(author) && cachedBookDTO.getStock() > stock) {
                        // 将 DTO 转换为实体对象并添加到列表中
                        books.add(new Book(cachedBookDTO));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Redis error: " + e.getMessage());
        }

        // 分页处理
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), books.size());
        List<Book> pagedBooks = books.subList(start, end);

        // 如果缓存中没有数据，则从数据库中读取
        if (pagedBooks.isEmpty()) {
            Page<Book> bookPage = bookRepository.findByAuthorContainingAndStockGreaterThanPageable(author, stock, pageable);
            // 将数据库中的数据缓存到 Redis
            bookPage.forEach(book -> {
                try {
                    redisTemplate.opsForValue().set("book:" + book.getId(), new BookJsonDTO(book));
                } catch (Exception e) {
                    System.err.println("Redis error: " + e.getMessage());
                }
            });
            return bookPage;
        }

        // 返回分页结果
        return new PageImpl<>(pagedBooks, pageable, books.size());
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
                System.out.println("Book cached in redis:" + cachedBook.getId());

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
                System.out.println("Save book to redis");
            } catch (Exception e) {
                // 记录日志或处理异常
                System.err.println("Redis error: " + e.getMessage());
            }
        });
        System.out.println("Book from db: " + bookFromDb.get().getId());
        return bookFromDb;
    }

    @Override
    public Page<Book> findByTag(String tag, Pageable pageable) {
        // try to get from cache
        List<Book> books = new ArrayList<>();
        try {
            Set<String> keys = redisTemplate.keys("book:*");
            if (keys != null) {
                for (String key : keys) {
                    // 获取缓存中的 BookJsonDTO 对象
                    BookJsonDTO cachedBookDTO = (BookJsonDTO) redisTemplate.opsForValue().get(key);
                    if (cachedBookDTO != null && tag.equals(cachedBookDTO.getTag())) {
                        // 转换为 Book 对象并添加到列表中
                        books.add(new Book(cachedBookDTO));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Redis error: " + e.getMessage());
        }
        // 分页处理
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), books.size());
        List<Book> pagedBooks = books.subList(start, end);

        // 如果缓存中没有数据，则从数据库中读取
        if (pagedBooks.isEmpty()) {
            Page<Book> bookPage = bookRepository.findByTag(tag, pageable);
            // 将数据库中的数据缓存到 Redis
            bookPage.forEach(book -> {
                try {
                    redisTemplate.opsForValue().set("book:" + book.getId(), new BookJsonDTO(book));
                } catch (Exception e) {
                    System.err.println("Redis error: " + e.getMessage());
                }
            });
            return bookPage;
        }

        return new PageImpl<>(pagedBooks, pageable, books.size());
    }

    @Override
    public Page<Book> findByTagAndStockGreaterThanPageable(String tag, Integer stock, Pageable pageable) {
        List<Book> books = new ArrayList<>();
        try {
            // 获取所有键
            Set<String> keys = redisTemplate.keys("book:*");
            if (keys != null) {
                for (String key : keys) {
                    // 获取缓存中的 BookJsonDTO 对象
                    BookJsonDTO cachedBookDTO = (BookJsonDTO) redisTemplate.opsForValue().get(key);
                    if (cachedBookDTO != null && tag.equals(cachedBookDTO.getTag()) && cachedBookDTO.getStock() > stock) {
                        // 将 DTO 转换为实体对象并添加到列表中
                        books.add(new Book(cachedBookDTO));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Redis error: " + e.getMessage());
        }

        // 分页处理
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), books.size());
        List<Book> pagedBooks = books.subList(start, end);

        // 如果缓存中没有数据，则从数据库中读取
        if (pagedBooks.isEmpty()) {
            Page<Book> bookPage = bookRepository.findByTagAndStockGreaterThanPageable(tag, stock, pageable);
            // 将数据库中的数据缓存到 Redis
            bookPage.forEach(book -> {
                try {
                    redisTemplate.opsForValue().set("book:" + book.getId(), new BookJsonDTO(book));
                } catch (Exception e) {
                    System.err.println("Redis error: " + e.getMessage());
                }
            });
            return bookPage;
        }

        // 返回分页结果
        return new PageImpl<>(pagedBooks, pageable, books.size());
    }

    @Override
    @CachePut(value = "books", key = "#book.id")
    public Book save(Book book) {

        Book savedBook = bookRepository.save(book);

        // 更新缓存
        String cacheKey = "book:" + savedBook.getId();
        try {
            redisTemplate.opsForValue().set(cacheKey, new BookJsonDTO(savedBook));
            System.out.println("Updated book in redis");
        } catch (Exception e) {
            System.err.println("Redis error: " + e.getMessage());
        }
        return savedBook;
    }

    @Override
    // @CacheEvict(value = "books", key = "#book.id")
    public void delete(Book book) {
        bookRepository.delete(book);

        // 删除缓存中的记录
        String cacheKey = "book:" + book.getId();
        try {
            redisTemplate.delete(cacheKey);
            System.out.println("Deleted book from redis");
        } catch (Exception e) {
            System.err.println("Redis error: " + e.getMessage());
        }
    }

    @Override
    public Page<Book> findAllByOrderBySalesDesc(Pageable pageable) {
        List<Book> books = new ArrayList<>();
        try {
            // 获取所有键
            Set<String> keys = redisTemplate.keys("book:*");
            if (keys != null) {
                for (String key : keys) {
                    // 获取缓存中的 BookJsonDTO 对象
                    BookJsonDTO cachedBookDTO = (BookJsonDTO) redisTemplate.opsForValue().get(key);
                    if (cachedBookDTO != null) {
                        // 将 DTO 转换为实体对象并添加到列表中
                        books.add(new Book(cachedBookDTO));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Redis error: " + e.getMessage());
        }

        // 按照 sales 字段降序排序
        books.sort((b1, b2) -> b2.getSales().compareTo(b1.getSales()));

        // 分页处理
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), books.size());
        List<Book> pagedBooks = books.subList(start, end);

        // 如果缓存中没有数据，则从数据库中读取
        if (pagedBooks.isEmpty()) {
            Page<Book> bookPage = bookRepository.findAllByOrderBySalesDesc(pageable);
            // 将数据库中的数据缓存到 Redis
            bookPage.forEach(book -> {
                try {
                    redisTemplate.opsForValue().set("book:" + book.getId(), new BookJsonDTO(book));
                } catch (Exception e) {
                    System.err.println("Redis error: " + e.getMessage());
                }
            });
            return bookPage;
        }

        // 返回分页结果
        return new PageImpl<>(pagedBooks, pageable, books.size());
    }

    @Override
    public Optional<Book> findByIsbnAndIdNot(String isbn, Integer id) {
        try {
            // 获取所有键
            Set<String> keys = redisTemplate.keys("book:*");
            if (keys != null) {
                for (String key : keys) {
                    // 获取缓存中的 BookJsonDTO 对象
                    BookJsonDTO cachedBookDTO = (BookJsonDTO) redisTemplate.opsForValue().get(key);
                    if (cachedBookDTO != null && isbn.equals(cachedBookDTO.getIsbn()) && !id.equals(cachedBookDTO.getId())) {
                        // 将 DTO 转换为实体对象并返回
                        Book book = new Book(cachedBookDTO);
                        System.out.println("Book cached in redis: " + book.getId());
                        return Optional.of(book);
                    }
                }
            }
            System.out.println("Book not cached in redis: isbn=" + isbn + ", id!=" + id);
        } catch (Exception e) {
            System.err.println("Redis error: " + e.getMessage());
        }

        // 从数据库中获取数据
        Optional<Book> bookFromDb = bookRepository.findByIsbnAndIdNot(isbn, id);
        bookFromDb.ifPresent(book -> {
            try {
                // 将实体对象转换为 DTO 并缓存到 Redis
                redisTemplate.opsForValue().set("book:" + book.getId(), new BookJsonDTO(book));
                System.out.println("Save book to redis ");
            } catch (Exception e) {
                System.err.println("Redis error: " + e.getMessage());
            }
        });
        return bookFromDb;
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        try {
            // 获取所有键
            Set<String> keys = redisTemplate.keys("book:*");
            if (keys != null) {
                for (String key : keys) {
                    // 获取缓存中的 BookJsonDTO 对象
                    BookJsonDTO cachedBookDTO = (BookJsonDTO) redisTemplate.opsForValue().get(key);
                    if (cachedBookDTO != null && isbn.equals(cachedBookDTO.getIsbn())) {
                        // 将 DTO 转换为实体对象并返回
                        Book book = new Book(cachedBookDTO);
                        System.out.println("Book cached in redis");
                        return Optional.of(book);
                    }
                }
            }
            System.out.println("Book not cached in redis: " + isbn);
        } catch (Exception e) {
            System.err.println("Redis error: " + e.getMessage());
        }

        // 从数据库中获取数据
        Optional<Book> bookFromDb = bookRepository.findByIsbn(isbn);
        bookFromDb.ifPresent(book -> {
            try {
                // 将实体对象转换为 DTO 并缓存到 Redis
                redisTemplate.opsForValue().set("book:" + book.getId(), new BookJsonDTO(book));
                System.out.println("Save book to redis");
            } catch (Exception e) {
                System.err.println("Redis error: " + e.getMessage());
            }
        });
        return bookFromDb;
    }
}
