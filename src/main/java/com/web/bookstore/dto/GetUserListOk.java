package com.web.bookstore.dto;

import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

import com.web.bookstore.model.User;
import com.web.bookstore.dto.UserDTO;

@Data
public class GetUserListOk {

    private List<UserDTO> users;
    private int total;

    /*
     * public GetUserListOk(List<UserDTO> users, int total) {
     * this.users = users;
     * this.total = total;
     * }
     */

    public GetUserListOk() {
    }

    public GetUserListOk(List<User> users, int total) {
        this.users = users.stream().map(UserDTO::new).collect(Collectors.toList());
        this.total = total;
    }
}
