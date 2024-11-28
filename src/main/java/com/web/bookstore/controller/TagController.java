package com.web.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.bookstore.service.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/addRelationship")
    public ResponseEntity<String> addTagRelationship(@RequestParam String tagName1, @RequestParam String tagName2) {
        try {
            tagService.addTagRelationship(tagName1, tagName2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Relationship added successfully.");
    }

    // sync TagNode with Tag
    @PostMapping("/sync")
    public ResponseEntity<String> syncTagNode() {
        try {
            tagService.syncTagNode();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("TagNode synced successfully.");
    }
}
