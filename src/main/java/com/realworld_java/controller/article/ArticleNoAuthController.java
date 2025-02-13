package com.realworld_java.controller.article;

import com.realworld_java.controller.article.res.ArticleNoAuthRes;
import com.realworld_java.service.article.ArticleNoAuthServiceImpl;
import com.realworld_java.service.article.inf.ArticleNoAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleNoAuthController {

    private final ArticleNoAuthService articleService;
    private final Logger logger;

    public ArticleNoAuthController(ArticleNoAuthServiceImpl articleService) {
        this.articleService = articleService;
        this.logger = LoggerFactory.getLogger(ArticleNoAuthController.class);
    }

    @GetMapping
    public ResponseEntity<ArticleNoAuthRes> getArticles(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String favorited,
            @RequestParam(required = false) String tag) {

        if (author != null) {
            logger.info("Get '/articles?author={}'", author);
            return ResponseEntity.ok(articleService.getArticlesByAuthor(author));
        }

        if (favorited != null) {
            logger.info("Get '/articles?favorited={}'", favorited);
            return ResponseEntity.ok(articleService.getArticlesByFavorite(favorited));
        }

        if (tag != null) {
            logger.info("Get '/articles?tag={}'", tag);
            return ResponseEntity.ok(articleService.getArticlesByTag(tag));
        }

        logger.info("Get '/articles' - All articles");
        return ResponseEntity.ok(articleService.getAllArticle());
    }
}
