package com.web.bookstore.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import lombok.Data;

@Data
@Node("Book")
public class BookNode {

    @Id
    private Integer id;

    public BookNode() {
    }

    public BookNode(Integer id) {
        this.id = id;
    }
}
