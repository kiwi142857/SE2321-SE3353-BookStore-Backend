package com.web.bookstore.service;

import com.web.bookstore.dto.GetBookListDTO;
import com.web.bookstore.model.Book;
import com.web.bookstore.dto.GetBookDetailDTO;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.web.bookstore.dto.GetBookRateDTO;
import com.web.bookstore.dto.ResponseDTO;

public interface BookService {

    Optional<Book> getBookById(Integer id);

    GetBookListDTO searchBooks(String searchType, String keyWord, Integer page, Integer size);

    GetBookListDTO getRankList(Integer pageSize, Integer sizeIndex);

    GetBookRateDTO getBookRate(String token, Integer bookId);

    ResponseDTO rateBook(String token, Integer bookId, Integer rate);
}
