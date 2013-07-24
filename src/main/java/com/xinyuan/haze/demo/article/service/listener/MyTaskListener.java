package com.xinyuan.haze.demo.article.service.listener;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xinyuan.haze.demo.article.service.ArticleProcessLogService;
import com.xinyuan.haze.demo.article.service.WorkflowVariable;

@Component
@Transactional
public class MyTaskListener implements TaskListener{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ArticleProcessLogService articleProcessLogService;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		Map<String, Object> articleVariables = delegateTask.getVariables();
		delegateTask.setVariable(WorkflowVariable.PRE_TASK_IDS, articleVariables.get(WorkflowVariable.TASKID));
		articleProcessLogService.saveArticleProcessLog(articleVariables);
	}

}
