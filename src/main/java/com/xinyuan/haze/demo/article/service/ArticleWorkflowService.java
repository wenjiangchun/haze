package com.xinyuan.haze.demo.article.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.activity.ActivityCompletedException;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinyuan.haze.HazeUtils;
import com.xinyuan.haze.common.exception.CannotAnonymousAccessException;
import com.xinyuan.haze.demo.article.entity.Article;
import com.xinyuan.haze.system.entity.Role;
import com.xinyuan.haze.system.service.UserService;

@Service
@Transactional(readOnly = true)
public class ArticleWorkflowService {

	private static Logger logger = LoggerFactory
			.getLogger(ArticleWorkflowService.class);

	@Autowired
	private ArticleService articleService;

	@Autowired(required=false)
	private RuntimeService runtimeService;

	@Autowired(required=false)
	protected TaskService taskService;

	@Autowired(required=false)
	protected HistoryService historyService;

	@Autowired(required=false)
	protected RepositoryService repositoryService;

	@Autowired(required=false)
	private IdentityService identityService;

	@Autowired
	private UserService userService;

	/**
	 * 启动流程
	 * 
	 * @param entity
	 * @throws CannotAnonymousAccessException
	 */
	@Transactional(readOnly = false)
	public ProcessInstance startWorkflow(Article article, String processKey,
			Map<String, Object> variables)
			throws CannotAnonymousAccessException {

		if (StringUtils.isEmpty(processKey)) {
			logger.error("启动流程出错，流程不能为空");
			return null;
		}
		articleService.save(article);
		// article.setUserId(HazeUtils.getCurrentUser().getUserId());
		Long userId = Long.valueOf(HazeUtils.getCurrentUser().getUserId());
		article.setUser(userService.findById(userId));
		logger.debug("save article: {}", article);

		String businessKey = article.getId().toString();

		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		identityService.setAuthenticatedUserId(article.getUser().getId()
				.toString());

		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(processKey, businessKey, variables);
		String processInstanceId = processInstance.getId();
		article.setProcessInstanceId(processInstanceId);
		logger.debug(
				"start process of {key={}, bkey={}, pid={}, variables={}}",
				new Object[] { "leave", businessKey, processInstanceId,
						variables });
		return processInstance;
	}

	public List<Article> findTodoTasks(String userId) {
		List<Article> results = new ArrayList<Article>();
		List<org.activiti.engine.task.Task> tasks = new ArrayList<Task>();

		// 根据当前人的ID查询
		org.activiti.engine.task.TaskQuery todoQuery = taskService
				.createTaskQuery().processDefinitionKey("article")
				.taskAssignee(userId).active().orderByTaskId().desc()
				.orderByTaskCreateTime().desc();
		// 根据当前人未签收的任务
		TaskQuery claimQuery = taskService.createTaskQuery()
				.processDefinitionKey("article").taskCandidateUser(userId)
				.active().orderByTaskId().desc().orderByTaskCreateTime().desc();

		// 合并
		tasks.addAll(todoQuery.list());
		tasks.addAll(claimQuery.list());

		// 根据用户角色查询用户当前任务信息
		Set<Role> roles = userService.findById(Long.valueOf(userId)).getRoles();
		List<String> roleName = new ArrayList<String>();
		for (Role role : roles) {
			roleName.add(role.getRoleName());
		}
		if (roleName.size() > 0) {
			TaskQuery roleQuery = taskService.createTaskQuery()
					.processDefinitionKey("article")
					.taskCandidateGroupIn(roleName).active().orderByTaskId()
					.desc().orderByTaskCreateTime().desc();
			tasks.addAll(roleQuery.list());
		}

		// 根据流程的业务ID查询实体并关联
		for (Task task : tasks) {
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).active()
					.singleResult();
			String businessKey = processInstance.getBusinessKey();
			Article leave = articleService.findById(new Long(businessKey));
			leave.setTask(task);
			leave.setProcessInstance(processInstance);
			leave.setProcessDefinition(getProcessDefinition(processInstance
					.getProcessDefinitionId()));
			results.add(leave);
		}

		return results;
	}

	protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		return processDefinition;
	}

	/**
	 * 签收文章
	 * 
	 * @param map 绑定任务ID和用户ID的Map变量
	 * @throws ActivityCompletedException
	 */
	@Transactional(readOnly = false)
	public void claimArticle(Map<String, Object> map)
			throws ActivityCompletedException {
		String taskId = (String) map.get("taskId");
		if (StringUtils.isBlank(taskId)) {
			throw new ActivityCompletedException("taskId不能为空！");
		}
		String userId = (String) map.get("userId");
		taskService.claim(taskId, userId);
	}
	
	/**
	 * 办理公文
	 * @param map 封装流程处理过程中参数的Map对象
	 * @throws ActivityCompletedException
	 */
	public void handleArticle(Map<String, Object> map) throws ActivityCompletedException {
		String taskId = (String) map.get(WorkflowVariable.TASKID);
		if (StringUtils.isBlank(taskId)) {
			throw new ActivityCompletedException("taskId不能为空！");
		}
		taskService.complete(taskId, map);
	}
}
