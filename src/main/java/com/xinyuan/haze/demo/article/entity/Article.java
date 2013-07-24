package com.xinyuan.haze.demo.article.entity;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.format.annotation.DateTimeFormat;

import com.xinyuan.haze.common.utils.HazeDataUtils;
import com.xinyuan.haze.core.entity.BaseEntity;
import com.xinyuan.haze.system.entity.User;

@Entity
@Table(name = "arti_article")
public class Article extends BaseEntity<Long> {
	
	private static final long serialVersionUID = 1L;

	private String title;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Lob
	private String content;
	
	@DateTimeFormat(pattern = HazeDataUtils.pattern)
	private Date createTime; 
	
	private String processInstanceId;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Transient
	private Task task;
	
	@Transient
	private Map<String, Object> variables;
	
	// 运行中的流程实例
	@Transient
	private ProcessInstance processInstance;

	// 历史的流程实例
	@Transient
	private HistoricProcessInstance historicProcessInstance;

	// 流程定义
	@Transient
	private ProcessDefinition processDefinition;
	
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public ProcessInstance getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	public HistoricProcessInstance getHistoricProcessInstance() {
		return historicProcessInstance;
	}

	public void setHistoricProcessInstance(
			HistoricProcessInstance historicProcessInstance) {
		this.historicProcessInstance = historicProcessInstance;
	}

	public ProcessDefinition getProcessDefinition() {
		return processDefinition;
	}

	public void setProcessDefinition(ProcessDefinition processDefinition) {
		this.processDefinition = processDefinition;
	}

	@Override
	public String toString() {
		return "Article:{id:" + id + ",title: " + title + "}";
	}
}