package com.realworld_java.service.article;

import com.realworld_java.controller.article.req.ArticleAuthReq;
import com.realworld_java.controller.article.res.ArticleAuthRes;
import com.realworld_java.domain.Article;
import com.realworld_java.domain.User;
import com.realworld_java.repository.article.ArticleAuthRepository;
import com.realworld_java.repository.user.UserRepository;
import com.realworld_java.service.article.inf.ArticleAuthService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ArticleAuthServiceImpl implements ArticleAuthService {

    private ArticleAuthRepository articleRepository;
    private UserRepository userRepository;
    private Logger logger;

    public ArticleAuthServiceImpl(ArticleAuthRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.logger = LoggerFactory.getLogger(ArticleAuthServiceImpl.class);
    }

    @Override
    public ArticleAuthRes createdArticleAuth(Long userId, ArticleAuthReq articleAuthReq) {
        User findUser = userRepository.findById(userId).orElseThrow();

        logger.info("article auth req: {}", articleAuthReq);

        Article article = Article.builder()
                .tile(articleAuthReq.getArticle().getTitle())
                .description(articleAuthReq.getArticle().getDescription())
                .body(articleAuthReq.getArticle().getBody())
                .slug(converterSlug(articleAuthReq.getArticle().getTitle()))
                .user(findUser)
                .build();

        return null;
    }

    @Override
    public ArticleAuthRes getFeedArticles(Long userId) {
        return null;
    }

    @Override
    public ArticleAuthRes getAllArticles(Long userId) {
        return null;
    }

    @Override
    public ArticleAuthRes getAllArticlesByAuth(Long userId) {
        return null;
    }

    @Override
    public ArticleAuthRes getSearchArticlesByUserName(String userName) {
        return null;
    }

    @Override
    public ArticleAuthRes getSearchArticleBySlug(String slug) {
        return null;
    }

    @Override
    public ArticleAuthRes getSearchArticlesByTag(String tag) {
        return null;
    }

    @Override
    public ArticleAuthRes updateArticleAuth(Long userId, ArticleAuthReq articleAuthReq) {
        return null;
    }

    @Override
    public ArticleAuthRes deleteArticleAuth(Long userId, String slug) {
        return null;
    }

    public String converterSlug(String title) {
        return null;
    }
}
