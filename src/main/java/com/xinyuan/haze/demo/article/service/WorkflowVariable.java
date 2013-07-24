package com.xinyuan.haze.demo.article.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.xinyuan.haze.HazeUtils;
import com.xinyuan.haze.common.exception.CannotAnonymousAccessException;

public class WorkflowVariable implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TASKID = "taskId";
	public static final String USERID = "userId";
	public static final String PRE_TASK_IDS = "preTaskIds";

	public static final String ARTICLE_ID = "articleId";
	public static final String CONTENT = "content";
	
	private Boolean condition = false;
	private String conditionName ;
	
	private String content ;
	private String contentName;
	
	private String userId;
	
	private String taskId;
	
	private String preTaskIds;
	
	public Boolean getCondition() {
		return condition;
	}

	public void setCondition(Boolean condition) {
		this.condition = condition;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getPreTaskIds() {
		return preTaskIds;
	}

	public void setPreTaskIds(String preTaskIds) {
		this.preTaskIds = preTaskIds;
	}

	public Map<String, Object> getParameterMap() throws CannotAnonymousAccessException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(conditionName)) {
			map.put(conditionName, condition);
		}
		if (StringUtils.isNotEmpty(contentName)) {
			map.put(contentName, content);
		}
		if (StringUtils.isNotEmpty(taskId)) {
			map.put(TASKID, taskId);
		}
		if (userId != null) {
			map.put(USERID, userId);
		} else {
			map.put(USERID, HazeUtils.getCurrentUser().getUserId().toString());
		}
		if (StringUtils.isNotEmpty(preTaskIds)) {
			map.put(PRE_TASK_IDS, preTaskIds);
		}
		return map;
	}
}
