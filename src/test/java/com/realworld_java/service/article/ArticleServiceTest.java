package com.realworld_java.service.article;

import com.realworld_java.controller.article.res.ArticleNoAuthRes;
import com.realworld_java.service.article.inf.ArticleNoAuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ArticleNoAuthServiceTest {

    private ArticleNoAuthService articleNoAuthService;

    @Autowired
    public ArticleNoAuthServiceTest(ArticleNoAuthService articleNoAuthService) {
        this.articleNoAuthService = articleNoAuthService;
    }

    @Test
    void getAllArticle() {
        // when
        ArticleNoAuthRes articleAuthRes = articleNoAuthService.getAllArticle();

        // then
        assertThat(articleAuthRes).isNotNull();
    }

    @Test
    void getArticlesByAuthor() {
        // given
        String author = "test";

        // when
        ArticleNoAuthRes articleAuthRes = articleNoAuthService.getArticlesByAuthor(author);

        // then
        assertThat(articleAuthRes).isNotNull();
    }

    @Test
    void getArticlesByFavorite() {
        // given
        String favorite = "test2";

        // when
        ArticleNoAuthRes articleAuthRes = articleNoAuthService.getArticlesByFavorite(favorite);

        // then
        assertThat(articleAuthRes).isNotNull();
    }

    @Test
    void getArticlesByTag() {
        // given
        String tag = "dragon";

        // when
        ArticleNoAuthRes articleAuthRes = articleNoAuthService.getArticlesByTag(tag);

        // then
        assertThat(articleAuthRes).isNotNull();
    }
}
