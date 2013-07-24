package com.xinyuan.haze.system.web.controller;

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

import com.xinyuan.haze.system.entity.Resource;
import com.xinyuan.haze.system.exception.UserNameExistException;
import com.xinyuan.haze.system.service.ResourceService;
import com.xinyuan.haze.system.utils.ResourceType;
import com.xinyuan.haze.web.message.WebMessage;
import com.xinyuan.haze.web.ui.bootstrap.BootStrapComponentUtils;
import com.xinyuan.haze.web.ui.bootstrap.css.AlertType;
import com.xinyuan.haze.web.ui.datatable.DataTablePage;
import com.xinyuan.haze.web.ui.datatable.DataTableParames;

/**
 * 资源操作Controller
 * @author Sofar
 *
 */
@Controller
@RequestMapping(value = "/system/resource")
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value = "view")
	public String list(Model model, ServletRequest request) {
		return "system/resource/resourceList";
	}
	
	@RequestMapping(value = "search")
	@ResponseBody
	public DataTablePage search(DataTableParames dataTableParames,ServletRequest request) {
		PageRequest p = dataTableParames.getPageRequest();
		Page<Resource> resourceList = this.resourceService.findPage(p);
		DataTablePage dtp = DataTablePage.newDataTablePage(resourceList, dataTableParames);
		List<String[]> list = new ArrayList<String[]>();
		for (Resource resource : resourceList.getContent()) {
			String id = resource.getId().toString();
			String resourceName = resource.getName();
			String permission = resource.getPermission();
			String resourceType = resource.getResourceType().getTypeName();
			String url = resource.getUrl();
			String operatorHTML = "";
			operatorHTML += BootStrapComponentUtils.createLink("edit_"+id,null,"operatorResource('"+id+"','edit')", "编辑").getHtml();
			operatorHTML += " | ";
			operatorHTML += BootStrapComponentUtils.createLink("delete_"+id,null,"operatorResource('"+id+"','delete')", "删除").getHtml();
			list.add(new String[]{id,resourceName,permission, resourceType,url,operatorHTML});
		}
		dtp.setAaData(list);
		return dtp;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model, ServletRequest request) {
		model.addAttribute("resourceTypes",ResourceType.values());
		List<Resource> resources = this.resourceService.findMenuResources();
		model.addAttribute("resources",resources);
		return "system/resource/addResource";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Resource resource, ServletRequest request, RedirectAttributes redirectAttributes) throws UserNameExistException {
		if (resource.getParent() != null && resource.getParent().getId() == null) {
			resource.setParent(null);
		}
		this.resourceService.saveOrUpdate(resource);
		WebMessage message = new WebMessage("资源添加成功", AlertType.SUCCESS);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/system/resource/view/";
	}
	
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model, ServletRequest request) {
		model.addAttribute("resourceTypes",ResourceType.values());
		List<Resource> menuResources = new ArrayList<Resource>();
		Resource resource = this.resourceService.findById(id);
		if (resource.getParent() != null) {
			menuResources = this.resourceService.findMenuResources();
		}
		model.addAttribute("resource",resource);
		model.addAttribute("menuResources",menuResources);
		return "system/resource/editResource";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Resource resource, ServletRequest request, RedirectAttributes redirectAttributes) throws UserNameExistException {
		if (resource.getParent() != null && resource.getParent().getId() == null) {
			resource.setParent(null);
		}
		this.resourceService.saveOrUpdate(resource);
		WebMessage message = new WebMessage("资源更新成功", AlertType.SUCCESS);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/system/resource/view/";
	}
	
	@RequestMapping(value = "delete/{ids}", method = RequestMethod.GET)
	public String delete(@PathVariable("ids") Long[] ids, ServletRequest request, RedirectAttributes redirectAttributes) {
		this.resourceService.batchDeleteResources(ids);
		WebMessage message = new WebMessage("资源删除成功", AlertType.SUCCESS);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/system/resource/view/";
	}
}
