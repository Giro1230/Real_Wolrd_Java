package com.realworld_java.service.article.inf;

import com.realworld_java.controller.article.res.ArticleNoAuthRes;

public interface ArticleNoAuthService {
    ArticleNoAuthRes getAllArticle();
    ArticleNoAuthRes getArticlesByAuthor(String name);
    ArticleNoAuthRes getArticlesByFavorite(String name);
    ArticleNoAuthRes getArticlesByTag(String tag);
}
