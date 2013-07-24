package com.xinyuan.haze.schedule.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xinyuan.haze.schedule.job.entity.GroovyJob;
import com.xinyuan.haze.schedule.job.service.GroovyJobService;
import com.xinyuan.haze.web.message.WebMessage;
import com.xinyuan.haze.web.ui.bootstrap.BootStrapComponentUtils;
import com.xinyuan.haze.web.ui.bootstrap.css.AlertType;
import com.xinyuan.haze.web.ui.datatable.DataTablePage;
import com.xinyuan.haze.web.ui.datatable.DataTableParames;

/**
 * TODO 作业操作Controller
 * @author wenjiangchun
 * @date 2013-3-25下午1:40:03
 * 
 */
@Controller
@RequestMapping(value = "/schedule/groovyJob")
public class GroovyJobController {

	@Autowired
	private GroovyJobService groovyJobService;
	
	@RequestMapping(value = "view")
	public String list(Model model, ServletRequest request) {
		return "schedule/groovyJob/groovyJobList";
	}
	
	@RequestMapping(value = "search")
	@ResponseBody
	public DataTablePage search(DataTableParames dataTableParames,ServletRequest request) {
		PageRequest p = dataTableParames.getPageRequest(); //根据dataTableParames对象获取JPA分页查询使用的PageRequest对象
		Page<GroovyJob> groovyJobList = this.groovyJobService.findPage(p,dataTableParames.getQueryVairables());
		DataTablePage dtp = DataTablePage.newDataTablePage(groovyJobList, dataTableParames); //将查询结果封装成前台使用的DataTablePage对象
		List<String[]> list = new ArrayList<String[]>();
		for (GroovyJob u : groovyJobList.getContent()) {
			String id = u.getId().toString();
			String name = u.getName();
			String description = u.getDescription();
			String operatorHTML = "";
			String status = "未编译";
			operatorHTML += BootStrapComponentUtils.createLink("edit_"+id,null,"operatorgroovyJob('"+id+"','edit')", "编辑").getHtml();
			operatorHTML += " | ";
			operatorHTML += BootStrapComponentUtils.createLink("delete_"+id,null,"operatorgroovyJob('"+id+"','delete')", "删除").getHtml();operatorHTML += " | ";
				operatorHTML += " | ";
				operatorHTML += BootStrapComponentUtils.createLink("compile_"+id,"/haze/schedule/groovyJob/compile/"+id, "编译").getHtml();
			list.add(new String[]{id,name,description,status, operatorHTML});
		}
		dtp.setAaData(list);
		return dtp;
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model, ServletRequest request) {
		return "schedule/groovyJob/addGroovyJob";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(GroovyJob groovyJob, ServletRequest request, RedirectAttributes redirectAttributes) {
		this.groovyJobService.save(groovyJob);
		WebMessage message = new WebMessage("作业添加成功", AlertType.SUCCESS);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/schedule/groovyJob/view";
	}
	
	@RequestMapping(value = "delete/{ids}", method = RequestMethod.GET)
	public String delete(@PathVariable("ids") Long[] ids, ServletRequest request, RedirectAttributes redirectAttributes) {
		this.groovyJobService.batchDelete(ids);
		WebMessage alertMessage = new WebMessage("作业删除成功", AlertType.SUCCESS);
		redirectAttributes.addFlashAttribute("message", alertMessage);
		return "redirect:/schedule/groovyJob/view";
	}
	
	/**
	 * 进入作业编辑页面
	 * @param id 作业Id
	 * @param model
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model, ServletRequest request) {
		GroovyJob groovyJob = this.groovyJobService.findById(id);
		model.addAttribute("groovyJob", groovyJob);
		return "schedule/groovyJob/editgroovyJob";
	}
	
	/**
	 * 更新作业信息
	 * @param groovyJob 作业
	 * @param request
	 * @param redirectAttributes
	 * @return 返回作业列表页面
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(GroovyJob groovyJob, ServletRequest request, RedirectAttributes redirectAttributes) {
		GroovyJob u = this.groovyJobService.findById(groovyJob.getId());
		u.setName(groovyJob.getName());
		u.setCodeContent(groovyJob.getCodeContent());
		u.setDescription(groovyJob.getDescription());
		this.groovyJobService.save(u);
		WebMessage alertMessage = new WebMessage("作业信息更新成功", AlertType.SUCCESS);
		redirectAttributes.addFlashAttribute("message", alertMessage);
		return "redirect:/schedule/groovyJob/view";
	}
	
	/**
	 * 判断作业名是否存在
	 * @param name 作业名
	 * @return true/false
	 */
	@RequestMapping(value = "isNotExistName", method = RequestMethod.POST)
	@ResponseBody
	public Boolean isNotExistName(String name) {
		Boolean isExist = this.groovyJobService.isExistName(name);
		return !isExist;
	}
	
	@RequestMapping(value = "compile/{id}", method = RequestMethod.GET)
	public String compile(@PathVariable Long id, ServletRequest request, RedirectAttributes redirectAttributes) {
		this.groovyJobService.compileGroovyJob(id);
		return "redirect:/schedule/groovyJob/view";
	}
}
