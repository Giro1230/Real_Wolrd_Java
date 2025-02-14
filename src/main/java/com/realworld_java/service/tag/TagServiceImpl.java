package com.realworld_java.service.tag;

import com.realworld_java.controller.tag.res.TagRes;
import com.realworld_java.domain.Tag;
import com.realworld_java.repository.tag.TagRepository;
import com.realworld_java.service.tag.inf.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private Logger logger;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
        this.logger = LoggerFactory.getLogger(TagServiceImpl.class);
    }

    @Override
    public TagRes getAllTags() {
        logger.info("Call getAllTags By Service");
        List<Tag> tagList = tagRepository.findAll();

        return TagRes.fromTags(tagList);
    }
}
