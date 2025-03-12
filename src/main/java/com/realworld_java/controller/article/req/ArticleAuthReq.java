package com.realworld_java.controller.article.req;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleAuthReq {
    private ArticleAuthReqDTO article;

    @Getter
    public static class ArticleAuthReqDTO {
        private String title;
        private String description;
        private String body;
        private List<String> tagList;
    }
}
