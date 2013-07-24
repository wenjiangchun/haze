package com.xinyuan.haze.demo.article.service;


import java.util.List;
import java.util.Map;

import com.xinyuan.haze.common.exception.CannotAnonymousAccessException;

public class ArticleWorkflowVariable extends WorkflowVariable {

	private static final long serialVersionUID = 1L;
	private String articleId; 
	private String deptDutyUser; 
	
	private List<String> countersignUsers;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getDeptDutyUser() {
		return deptDutyUser;
	}
	public void setDeptDutyUser(String deptDutyUser) {
		this.deptDutyUser = deptDutyUser;
	}
	
	public List<String> getCountersignUsers() {
		return countersignUsers;
	}
	public void setCountersignUsers(List<String> countersignUsers) {
		this.countersignUsers = countersignUsers;
	}
	@Override
	public Map<String, Object> getParameterMap() throws CannotAnonymousAccessException {
		Map<String, Object> map = super.getParameterMap();
		map.put("articleId", articleId);
		map.put("deptDutyUser", deptDutyUser);
		map.put("countersignUsers", countersignUsers);
		return map;
	}
	
	
}
