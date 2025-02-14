package com.realworld_java.service.tag;

import com.realworld_java.controller.tag.res.TagRes;
import com.realworld_java.service.tag.inf.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TagServiceTest {

    private final TagService tagService;

    @Autowired
    public TagServiceTest(TagServiceImpl tagService) {
        this.tagService = tagService;
    }

    @Test
    void getAllTags() {
        // when
        TagRes tags = tagService.getAllTags();

        // then
        assertThat(tags).isNotNull();
    }
}
