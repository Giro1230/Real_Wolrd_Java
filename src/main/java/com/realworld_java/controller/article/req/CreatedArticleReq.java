package com.realworld_java.controller.article.req;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatedArticleReq {
    private CreatedArticleReqDTO article;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class CreatedArticleReqDTO {
        private String title;
        private String description;
        private String body;
        private List<String> tags;
    }
}
