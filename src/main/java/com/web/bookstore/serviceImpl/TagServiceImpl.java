package com.web.bookstore.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.bookstore.model.Tag;
import com.web.bookstore.model.TagNode;
import com.web.bookstore.repository.TagNodeRepository;
import com.web.bookstore.repository.TagRepository;
import com.web.bookstore.service.TagService;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private final TagNodeRepository tagNodeRepository;

    @Autowired
    private final TagRepository tagRepository;

    public TagServiceImpl(TagNodeRepository tagNodeRepository, TagRepository tagRepository) {
        this.tagNodeRepository = tagNodeRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public boolean addTagRelationship(String tagName1, String tagName2) throws RuntimeException {
        Optional<Tag> tag1 = tagRepository.findByName(tagName1);
        Optional<Tag> tag2 = tagRepository.findByName(tagName2);
        if (!tag1.isPresent() || !tag2.isPresent()) {
            if (tag1.isEmpty()) {
                throw new RuntimeException("Tag not found: " + tagName1);
            }
            if (tag2.isEmpty()) {
                throw new RuntimeException("Tag not found: " + tagName2);
            }
            throw new RuntimeException("Tag not found.");
        }

        tagNodeRepository.createRelationship(tag1.get().getId(), tag2.get().getId());
        return true;
    }

    @Override
    public void syncTagNode() {
        // we get all the nodes from TagRepository
        // we try to add them to TagNodeRepository
        // if they already exist, we skip
        // if they don't exist, we add them

        tagRepository.findAll().forEach(tag -> {
            TagNode tagNode = new TagNode(tag.getId(), tag.getName());
            tagNodeRepository.save(tagNode);
        });
    }
}
