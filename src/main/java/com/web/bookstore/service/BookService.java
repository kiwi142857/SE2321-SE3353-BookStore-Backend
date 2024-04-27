package com.web.bookstore.service;

import com.web.bookstore.dto.GetBookListDTO;
import com.web.bookstore.dto.GetBookOkDTO;

import java.util.NoSuchElementException;

public interface BookService {

    GetBookOkDTO getBookById(Integer id) throws NoSuchElementException;

    GetBookListDTO searchBooks(String searchType, String keyWord, Integer page, Integer size);
}
