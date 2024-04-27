package com.web.bookstore.dto;

import com.web.bookstore.dto.BookBreifDTO;
import lombok.Data;
import java.util.List;

@Data
public class GetBookListDTO {

    private Integer total;
    private List<BookBreifDTO> bookList;

    public GetBookListDTO(List<BookBreifDTO> bookList, Integer total) {
        this.bookList = bookList;
        this.total = total;
    }

}
