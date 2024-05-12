package com.web.bookstore.service;

import com.web.bookstore.dto.GetBookListDTO;
import com.web.bookstore.model.Book;
import com.web.bookstore.dto.GetBookDetailDTO;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface BookService {

    Optional<Book> getBookById(Integer id);

    GetBookListDTO searchBooks(String searchType, String keyWord, Integer page, Integer size);

    GetBookListDTO getRankList(Integer pageSize, Integer sizeIndex);
}
