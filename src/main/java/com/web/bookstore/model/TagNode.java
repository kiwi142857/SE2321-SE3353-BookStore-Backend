package com.web.bookstore.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import lombok.Data;

import java.util.List;

@Data
@Node("Tag")
public class TagNode {

    @Id
    private Integer id;

    private String name;

    @Relationship(type = "RELATED_TO", direction = Relationship.Direction.OUTGOING)
    private List<TagNode> relatedTags;

    public TagNode() {
    }

    public TagNode(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
