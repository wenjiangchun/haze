package com.xinyuan.haze.demo.article.dao;

import org.springframework.stereotype.Repository;

import com.xinyuan.haze.core.jpa.repository.BaseRepository;
import com.xinyuan.haze.demo.article.entity.Article;

@Repository
public interface ArticleDao extends BaseRepository<Article, Long> {

}
