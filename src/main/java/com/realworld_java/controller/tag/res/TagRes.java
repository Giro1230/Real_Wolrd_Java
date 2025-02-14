package com.realworld_java.controller.tag.res;

import com.realworld_java.domain.Tag;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagRes {
    private List<TagResDTO> tags;

    public static TagRes fromTags(List<Tag> tags) {
        return TagRes.builder()
                .tags(tags.stream()
                        .map(tag -> TagResDTO.builder().tagName(tag.getTagName()).build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class TagResDTO {
        private String tagName;
    }
}
