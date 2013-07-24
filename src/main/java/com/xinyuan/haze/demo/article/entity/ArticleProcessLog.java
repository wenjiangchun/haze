package com.xinyuan.haze.demo.article.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xinyuan.haze.core.entity.BaseEntity;
import com.xinyuan.haze.system.entity.User;

@Entity
@Table(name = "arti_process_log")
public class ArticleProcessLog extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	private String taskId; 
	
	@ManyToOne(optional = true)  
    @JoinColumn(name="article_id")
	private Article article;
	
	private String content;
	private String taskName;
	private String result;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return getClass().getName() + "@{任务节点:"+taskName+",文章标题:"+",任务处理结果:"+result+"}";
	}
}
