package com.realworld_java.repository.article;

import com.realworld_java.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleAuthRepository extends JpaRepository<Article, Long> {

}
