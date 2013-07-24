package com.xinyuan.haze.demo.article.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinyuan.haze.core.service.AbstractBaseService;
import com.xinyuan.haze.demo.article.dao.ArticleProcessLogDao;
import com.xinyuan.haze.demo.article.entity.Article;
import com.xinyuan.haze.demo.article.entity.ArticleProcessLog;
import com.xinyuan.haze.system.entity.User;

@Service
@Transactional(readOnly = true)
public class ArticleProcessLogService extends AbstractBaseService<ArticleProcessLog, Long> {

	private ArticleProcessLogDao articleProcessLogDao;
	
	@Autowired(required=false)
	private TaskService taskService;
	
	@Autowired
	public void setArticleProcessLogDao(ArticleProcessLogDao articleProcessLogDao) {
		this.articleProcessLogDao = articleProcessLogDao;
		super.setDao(articleProcessLogDao);
	}

	public Article saveArticle(Article article) {
		return article;
	}

	public List<ArticleProcessLog> getArticleProcessLogsByArticleId(
			Long articleId) {
		return articleProcessLogDao.getArticleProcessLogsByArticleId(articleId);
	}

	@Transactional(readOnly = false)
	public void saveArticleProcessLog(Map<String, Object> articleVariables) {
		ArticleProcessLog log = new ArticleProcessLog();
		Article article = new Article();
		article.setId(Long.valueOf((String)articleVariables.get(WorkflowVariable.ARTICLE_ID)));
		log.setArticle(article);
		log.setContent((String) articleVariables.get(WorkflowVariable.CONTENT));
		String taskId = (String) articleVariables.get(WorkflowVariable.TASKID);
		log.setTaskId(taskId);
		User user = new User();
		user.setId(Long.valueOf((String) articleVariables.get(WorkflowVariable.USERID)));
		log.setUser(user);
		log.setTaskName(taskService.createTaskQuery().taskId(taskId).singleResult().getName());
		
		//记录上个节点IdS
		//String preTaskIds = (String) articleVariables.get(WorkflowVariable.PRE_TASK_IDS);
		//log.setPreTaskIds(preTaskIds);
		Boolean isCountyPass = (Boolean) articleVariables.get("isCountyPass"); //县局审批是否通过
		if (isCountyPass != null) {
			if (isCountyPass) {
				log.setResult("县局审核通过，提交到市局审核");
			} else {
				log.setResult("县局退回");
			}
	    }
		Boolean isCityPass = (Boolean) articleVariables.get("isCityPass"); //市局审批是否通过
		if (isCityPass != null) {
			if (isCityPass) {
				log.setResult("市局审核通过，提交到省局审核");
			} else {
				log.setResult("市局退回");
			}
		}
		Boolean isProvincialPass = (Boolean) articleVariables.get("isProvincialPass"); //省局审批是否通过
		if (isProvincialPass != null) {
			if (isProvincialPass) {
				log.setResult("省局审核通过，转到用户确认");
			} else {
				log.setResult("省局退回");
			}
		}
	    this.save(log);
	}
}
