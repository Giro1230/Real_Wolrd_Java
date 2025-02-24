package com.realworld_java.repository.tag;

import com.realworld_java.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTagName(@Param("tagName") String tagName);

    List<Tag> findByTagNameIn(List<String> tagNames);
}
