package com.web.bookstore.dto;

import com.web.bookstore.dto.BookBriefDTO;
import lombok.Data;
import java.util.List;

@Data
public class GetBookListDTO {

    private Integer total;
    private List<BookBriefDTO> bookList;

    public GetBookListDTO(List<BookBriefDTO> bookList, Integer total) {
        this.bookList = bookList;
        this.total = total;
    }

}
