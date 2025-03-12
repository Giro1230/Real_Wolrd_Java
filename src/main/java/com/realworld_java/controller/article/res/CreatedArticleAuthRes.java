package com.realworld_java.controller.article.res;

import com.realworld_java.domain.Article;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatedArticleAuthRes {
    private ArticleResDTO article;
    private int articleCount;

    public static CreatedArticleAuthRes fromArticle(Article article) {
        return CreatedArticleAuthRes.builder()
                .article(ArticleResDTO.fromArticle(article))
                .build();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class ArticleResDTO {
        private String title;
        private String slug;
        private String body;
        private String description;
        private List<String> tagList;
        private AuthorDTO author;
        private boolean favorited;
        private int favoritesCount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static ArticleResDTO fromArticle(Article article) {
            return ArticleResDTO.builder()
                    .title(article.getTile())
                    .slug(article.getSlug())
                    .body(article.getBody())
                    .description(article.getDescription())
                    .tagList(article.getTags().stream().map(com.realworld_java.domain.Tag::getTagName).collect(Collectors.toList()))
                    .author(AuthorDTO.builder()
                            .username(article.getUser().getUsername())
                            .bio(article.getUser().getBio())
                            .image(article.getUser().getImage())
                            .following(false)
                            .build())
                    .favorited(false)
                    .favoritesCount(article.getFavorites().size())
                    .createdAt(article.getCreatedAt())
                    .updatedAt(article.getUpdatedAt())
                    .build();
        }
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class AuthorDTO {
        private String username;
        private String bio;
        private String image;
        private boolean following;
    }
}


