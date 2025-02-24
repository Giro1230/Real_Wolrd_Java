package com.realworld_java.controller.article;

import com.realworld_java.controller.article.req.*;
import com.realworld_java.controller.article.res.*;
import com.realworld_java.service.article.inf.ArticleAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleAuthController {

    private ArticleAuthService articleAuthService;
    private Logger logger;

    public ArticleAuthController(ArticleAuthService articleAuthService) {
        this.articleAuthService = articleAuthService;
        this.logger = LoggerFactory.getLogger(ArticleAuthController.class);
    }

    @PostMapping
    public ResponseEntity<ArticleAuthRes> created(@AuthenticationPrincipal Long userId, @RequestBody ArticleAuthReq articleAuthReq) {
        logger.info("Article Auth Created Call");
        articleAuthService.createdArticleAuth(userId, articleAuthReq);

        return ResponseEntity.ok(null);
    }
}
