package com.web.bookstore.service;

import org.springframework.stereotype.Service;

@Service
public interface TagService {

    // addTagRelationship(tagId1, tagId2);
    public boolean addTagRelationship(String tagName1, String tagName2);

    // syncTagNode();
    public void syncTagNode();
}
