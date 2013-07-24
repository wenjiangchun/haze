package com.xinyuan.haze.demo.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinyuan.haze.core.service.AbstractBaseService;
import com.xinyuan.haze.demo.article.dao.ArticleDao;
import com.xinyuan.haze.demo.article.entity.Article;

@Service
@Transactional(readOnly = true)
public class ArticleService extends AbstractBaseService<Article, Long> {
	
	private ArticleDao articleDao;
	
	@Autowired
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
		super.setDao(articleDao);
	}

}
