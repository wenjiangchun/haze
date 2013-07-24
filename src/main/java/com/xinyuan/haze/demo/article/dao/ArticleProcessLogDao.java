package com.xinyuan.haze.demo.article.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xinyuan.haze.core.jpa.repository.BaseRepository;
import com.xinyuan.haze.demo.article.entity.ArticleProcessLog;

@Repository
public interface ArticleProcessLogDao extends BaseRepository<ArticleProcessLog, Long> {

	List<ArticleProcessLog> getArticleProcessLogsByArticleId(Long articleId);
}
