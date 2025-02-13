package com.realworld_java.service.article;

import com.realworld_java.controller.article.res.ArticleNoAuthRes;
import com.realworld_java.domain.Article;
import com.realworld_java.repository.article.ArticleNoAuthRepository;
import com.realworld_java.service.article.inf.ArticleNoAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleNoAuthServiceImpl implements ArticleNoAuthService {

    private final ArticleNoAuthRepository articleNoAuthRepository;
    private final Logger logger;

    public ArticleNoAuthServiceImpl(ArticleNoAuthRepository articleNoAuthRepository) {
        this.articleNoAuthRepository = articleNoAuthRepository;
        this.logger = LoggerFactory.getLogger(ArticleNoAuthServiceImpl.class);
    }

    @Override
    public ArticleNoAuthRes getAllArticle() {
        List<Article> articles = articleNoAuthRepository.findAll();
        return ArticleNoAuthRes.fromArticle(articles);
    }

    @Override
    public ArticleNoAuthRes getArticlesByAuthor(String name) {
        logger.info("getArticlesByAuthor name : {}", name);
        List<Article> articles = articleNoAuthRepository.findArticlesByUserName(name);
        return ArticleNoAuthRes.fromArticle(articles);
    }

    @Override
    public ArticleNoAuthRes getArticlesByFavorite(String name) {
        logger.info("getArticlesByFavorite name : {}", name);
        List<Article> articles = articleNoAuthRepository.findArticlesByFavoritesUser(name);
        return ArticleNoAuthRes.fromArticle(articles);
    }

    @Override
    public ArticleNoAuthRes getArticlesByTag(String tag) {
        logger.info("getArticlesByTag tag : {}", tag);
        List<Article> articles = articleNoAuthRepository.findArticlesByTag(tag);
        return ArticleNoAuthRes.fromArticle(articles);
    }
}
