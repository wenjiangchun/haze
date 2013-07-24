package com.xinyuan.haze.demo.article.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activity.ActivityCompletedException;
import javax.servlet.ServletRequest;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xinyuan.haze.HazeUtils;
import com.xinyuan.haze.common.exception.CannotAnonymousAccessException;
import com.xinyuan.haze.common.utils.HazeDataUtils;
import com.xinyuan.haze.demo.article.entity.Article;
import com.xinyuan.haze.demo.article.entity.ArticleProcessLog;
import com.xinyuan.haze.demo.article.service.ArticleProcessLogService;
import com.xinyuan.haze.demo.article.service.ArticleService;
import com.xinyuan.haze.demo.article.service.ArticleWorkflowService;
import com.xinyuan.haze.demo.article.service.ArticleWorkflowVariable;
import com.xinyuan.haze.demo.article.service.WorkflowVariable;
import com.xinyuan.haze.workflow.service.ProcessDefinitionService;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleProcessLogService articleProcessLogService;
	@Autowired
	private ArticleWorkflowService articleWorkflowService;
	
	@Autowired
	private ProcessDefinitionService processDefinitionService;
	
	@RequestMapping(value = "view")
	public String list(Model model, ServletRequest request) {
		List<Article> articleList;
		try {
			articleList = articleWorkflowService.findTodoTasks(HazeUtils.getCurrentUser().getUserId().toString());
			model.addAttribute("articleList", articleList);
		} catch (CannotAnonymousAccessException e) {
			return "redirect:/login";
		}
		return "article/articleList";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model, ServletRequest request) {
		
		//查询系统中流程定义
		List<ProcessDefinition> processDefinitions =  processDefinitionService.getActiveProcessDefinitons();
		model.addAttribute("processDefinitions", processDefinitions);
		return "article/articleForm";
	}
	
	@RequestMapping(value = "saveAndStartWorkflow", method = RequestMethod.POST)
	public String saveAndStartWorkflow(Article article, @RequestParam String processKey, ServletRequest request, RedirectAttributes redirectAttributes) throws CannotAnonymousAccessException {
		Map<String, Object> variables = new HashMap<String, Object>();
		article.setCreateTime(HazeDataUtils.getCurrentDate());
		variables.put("isCountyUser", false);
		articleWorkflowService.startWorkflow(article, processKey, variables);
		redirectAttributes.addFlashAttribute("message", "add.article.success");
		return "redirect:/article/view";
	}
	
	@RequestMapping(value = "claimArticle", method = RequestMethod.GET)
	public String claimArticle(@RequestParam Map<String, Object> paramVariables) throws ActivityCompletedException {
		try {
			String userId = HazeUtils.getCurrentUser().getUserId().toString();
			paramVariables.put(WorkflowVariable.USERID, userId);
		} catch (CannotAnonymousAccessException e) {
			e.printStackTrace();
		}
		articleWorkflowService.claimArticle(paramVariables);
		return "redirect:/article/view";
	}
	
	@RequestMapping(value = "handle", method = RequestMethod.GET)
	public String handle(Model model, @RequestParam String tkey, @RequestParam String tid, @RequestParam Long articleId) throws CannotAnonymousAccessException {
		String currentUserId = HazeUtils.getCurrentUser().getUserId().toString();
		model.addAttribute("taskId", tid);
		model.addAttribute("articleId", articleId);
		model.addAttribute("currentUserId", currentUserId);
		List<ArticleProcessLog> resultList= articleProcessLogService.getArticleProcessLogsByArticleId(articleId);
		model.addAttribute("resultList", resultList);
		return "article/"+tkey;
	}
	
	/**
	 * 办理流程
	 * @return
	 * @throws CannotAnonymousAccessException 
	 * @throws Exception
	 */
	@RequestMapping(value = "handleArticle", method = RequestMethod.POST)
	public String handleArticle(ArticleWorkflowVariable paramVariables) throws CannotAnonymousAccessException {
		try {
			paramVariables.setUserId(HazeUtils.getCurrentUser().getUserId().toString()); //设置当前办理人
			articleWorkflowService.handleArticle(paramVariables.getParameterMap());
		} catch (ActivityCompletedException e) {
			e.printStackTrace();
		}
		return "redirect:/article/view";
	}
	
}
