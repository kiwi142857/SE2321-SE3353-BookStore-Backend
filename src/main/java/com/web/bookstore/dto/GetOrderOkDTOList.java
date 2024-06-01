package com.web.bookstore.dto;

import java.util.List;
import com.web.bookstore.dto.GetOrderOkDTO;

import lombok.Data;

@Data
public class GetOrderOkDTOList {

    private List<GetOrderOkDTO> orders;
    private int total;

    public GetOrderOkDTOList(List<GetOrderOkDTO> orders, int total) {
        this.orders = orders;
        this.total = total;
    }
}
