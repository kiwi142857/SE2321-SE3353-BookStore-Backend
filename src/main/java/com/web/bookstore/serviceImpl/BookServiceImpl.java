package com.web.bookstore.serviceimpl;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import com.web.bookstore.dto.GetBookDetailDTO;
import com.web.bookstore.repository.BookRepository;
import com.web.bookstore.service.BookService;
import com.web.bookstore.dto.BookBreifDTO;
import com.web.bookstore.dto.GetBookListDTO;
import com.web.bookstore.model.Book;

import java.util.stream.Collectors;
import java.util.List;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    public final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }

    public GetBookListDTO searchBooks(String searchType, String keyWord, Integer page, Integer size) {
        List<Book> bookList;
        if (searchType.equals("title")) {
            bookList = bookRepository.findByTitleContaining(keyWord);
        } else if (searchType.equals("tag")) {
            bookList = bookRepository.findByTag(keyWord);
        } else {
            bookList = bookRepository.findByAuthorContaining(keyWord);
        }
        System.out.println(bookList);
        Integer total = bookList.size();
        bookList = bookList.stream().skip((page) * size).limit(size).collect(Collectors.toList());
        List<BookBreifDTO> bookBreifDTOList = bookList.stream().map(BookBreifDTO::new).collect(Collectors.toList());
        // System.out.println(bookBreifDTOList);
        return new GetBookListDTO(bookBreifDTOList, total);
    }

    public GetBookListDTO getRankList(Integer pageSize, Integer sizeIndex) {
        List<Book> bookList = bookRepository.findAll();
        bookList = bookList.stream().skip((sizeIndex) * pageSize).limit(pageSize).collect(Collectors.toList());
        List<BookBreifDTO> bookBreifDTOList = bookList.stream().map(BookBreifDTO::new).collect(Collectors.toList());
        return new GetBookListDTO(bookBreifDTOList, pageSize);
    }
}
