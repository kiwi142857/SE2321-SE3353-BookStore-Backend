package com.web.bookstore.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.web.bookstore.model.TagNode;

public interface TagNodeRepository extends Neo4jRepository<TagNode, Integer> {

    @Query("MATCH (t:Tag {name: $tagName})-[:RELATED_TO*0..2]-(related:Tag) RETURN DISTINCT related")
    List<TagNode> findRelatedTags(@Param("tagName") String tagName);

    @Query("MATCH (a:Tag {id: $tagId1}), (b:Tag {id: $tagId2}) CREATE (a)-[:RELATED_TO]->(b)")
    void createRelationship(@Param("tagId1") Integer tagId1, @Param("tagId2") Integer tagId2);

    // create Node
    @Override
    TagNode save(TagNode tagNode);
}
