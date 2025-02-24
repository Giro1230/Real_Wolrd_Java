package com.realworld_java.service.article;

import com.realworld_java.controller.article.req.ArticleAuthReq;
import com.realworld_java.controller.article.res.ArticleAuthRes;
import com.realworld_java.domain.Article;
import com.realworld_java.domain.Tag;
import com.realworld_java.domain.User;
import com.realworld_java.repository.article.ArticleAuthRepository;
import com.realworld_java.repository.tag.TagRepository;
import com.realworld_java.repository.user.UserRepository;
import com.realworld_java.service.article.inf.ArticleAuthService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArticleAuthServiceImpl implements ArticleAuthService {

    private ArticleAuthRepository articleRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;
    private Logger logger;

    public ArticleAuthServiceImpl(ArticleAuthRepository articleRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.logger = LoggerFactory.getLogger(ArticleAuthServiceImpl.class);
    }

    @Override
    public ArticleAuthRes createdArticleAuth(Long userId, ArticleAuthReq articleAuthReq) {
        logger.info("article auth req: {}", articleAuthReq);

        User findUser = userRepository.findById(userId).orElseThrow();

        List<String> tagNames = articleAuthReq.getArticle().getTagList();

        List<Tag> existingTags = tagRepository.findByTagNameIn(tagNames);  // 한 번에 조회

        Set<String> existingTagNames = existingTags.stream()
                .map(Tag::getTagName)
                .collect(Collectors.toSet());

        List<Tag> newTags = tagNames.stream()
                .filter(tagName -> !existingTagNames.contains(tagName))  // 없는 태그만 필터링
                .map(tagName -> Tag.builder().tagName(tagName).build())
                .toList();

        if (!newTags.isEmpty()) {
            tagRepository.saveAll(newTags);
        }

        List<Tag> finalTags = Stream
                .concat(existingTags.stream(), newTags.stream())
                .toList();

        logger.info("Final Tags : {}", finalTags);


        Article article = Article.builder()
                .tile(articleAuthReq.getArticle().getTitle())
                .description(articleAuthReq.getArticle().getDescription())
                .body(articleAuthReq.getArticle().getBody())
                .slug(converterSlug(articleAuthReq.getArticle().getTitle()))
                .user(findUser)
                .tags(finalTags)
                .build();

        articleRepository.save(article);

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
        return Normalizer.normalize(title, Normalizer.Form.NFKD) // 유니코드 정규화
                .replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}\\s]", "") // 특수문자 제거
                .trim() // 앞뒤 공백 제거
                .toLowerCase(Locale.ENGLISH)    // 영어를 전부 소문자로 변경
                .replaceAll("\\s+", "-");   // 공백을 '-'로 변경
    }
}
