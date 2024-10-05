package com.web.bookstore.dto;

import lombok.Data;
import java.util.List;

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

    public GetUserListOk(List<UserDTO> users, int total) {
        this.users = users;
        this.total = total;
    }
}
