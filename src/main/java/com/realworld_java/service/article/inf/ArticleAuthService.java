package com.realworld_java.service.article.inf;

import com.realworld_java.controller.article.req.ArticleAuthReq;
import com.realworld_java.controller.article.res.ArticleAuthRes;

public interface ArticleAuthService {
    ArticleAuthRes createdArticleAuth(Long userId, ArticleAuthReq articleAuthReq);
    ArticleAuthRes getFeedArticles(Long userId);
    ArticleAuthRes getAllArticles(Long userId);
    ArticleAuthRes getAllArticlesByAuth(Long userId);
    ArticleAuthRes getSearchArticlesByUserName(String userName);
    ArticleAuthRes getSearchArticleBySlug(String slug);
    ArticleAuthRes getSearchArticlesByTag(String tag);
    ArticleAuthRes updateArticleAuth(Long userId, ArticleAuthReq articleAuthReq);
    ArticleAuthRes deleteArticleAuth(Long userId, String slug);
}
