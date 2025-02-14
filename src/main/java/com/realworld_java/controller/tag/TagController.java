package com.realworld_java.controller.tag;

import com.realworld_java.controller.tag.res.TagRes;
import com.realworld_java.service.tag.inf.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;
    private final Logger logger;

    public TagController(TagService tagService) {
        this.tagService = tagService;
        this.logger = LoggerFactory.getLogger(TagController.class);
    }

    @GetMapping
    public ResponseEntity<TagRes> getAllTags() {
        logger.info("Get all tags");
        TagRes tagRes = tagService.getAllTags();
        return ResponseEntity.ok(tagRes);
    }
}
