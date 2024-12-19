package com.web.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.web.bookstore.dto.GetBookListDTO;
import com.web.bookstore.service.BookService;

@Controller
public class GraphQLBookController {

    @Autowired
    private BookService bookService;

    @QueryMapping
    public GetBookListDTO getBookByName(@Argument String name) {
        return bookService.searchBooks("title", name, 0, 10);
    }
}
