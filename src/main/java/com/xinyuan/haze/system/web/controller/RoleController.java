package com.xinyuan.haze.system.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.xinyuan.haze.system.entity.Role;
import com.xinyuan.haze.system.exception.RoleExistException;
import com.xinyuan.haze.system.service.ResourceService;
import com.xinyuan.haze.system.service.RoleService;
import com.xinyuan.haze.system.utils.Status;
import com.xinyuan.haze.web.message.WebMessage;
import com.xinyuan.haze.web.ui.bootstrap.BootStrapComponentUtils;
import com.xinyuan.haze.web.ui.bootstrap.css.AlertType;
import com.xinyuan.haze.web.ui.bootstrap.css.SpanType;
import com.xinyuan.haze.web.ui.datatable.DataTablePage;
import com.xinyuan.haze.web.ui.datatable.DataTableParames;

/**
 * 角色管理Controller
 * @author sofar
 *
 */
@Controller
@RequestMapping(value = "/system/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value = "view")
	public String list(Model model, ServletRequest request) {
		return "system/role/roleList";
	}
	
	@RequestMapping(value = "search")
	@ResponseBody
	public DataTablePage search(DataTableParames dataTableParames,ServletRequest request) {
		PageRequest p = dataTableParames.getPageRequest();
		Page<Role> roleList = this.roleService.findPage(p);
		DataTablePage dtp = DataTablePage.newDataTablePage(roleList, dataTableParames);
		List<String[]> list = new ArrayList<String[]>();
		for (Role role : roleList.getContent()) {
			String id = role.getId().toString();
			String roleName = role.getRoleName();
			String name = role.getName();
			String statusLabel = getStatusLabel(role.getStatus());
			String operatorHTML = "";
			operatorHTML += BootStrapComponentUtils.createLink("edit_"+id,null,"operatorRole('"+id+"','edit')", "编辑").getHtml();
			operatorHTML += " | ";
			operatorHTML += BootStrapComponentUtils.createLink("addResources_"+id,null,"operatorRole('"+id+"','addResources')", "授权").getHtml();
			operatorHTML += " | ";
			operatorHTML += BootStrapComponentUtils.createLink("delete_"+id,null,"operatorRole('"+id+"','delete')", "删除").getHtml();
			list.add(new String[]{id,name,roleName, statusLabel,operatorHTML});
		}
		dtp.setAaData(list);
		return dtp;
	}
	private String getStatusLabel(Status status) {
		String text = status.getStatusName();
		SpanType spanType = SpanType.getSpanTypeByStatus(status);
		return BootStrapComponentUtils.createSpan(null, spanType, text).getHtml();
	}
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model, ServletRequest request) {
		model.addAttribute("statuss", Status.values());
		return "system/role/addRoleForm";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Role role, Long[] roleIds, RedirectAttributes redirectAttributes) {
		try {
			this.roleService.saveOrUpdate(role);
			WebMessage message = new WebMessage("角色保存成功", AlertType.SUCCESS);
			redirectAttributes.addFlashAttribute("message", message);
		} catch (RoleExistException e) {
			WebMessage message = new WebMessage("角色保存失败，当前角色已存在", AlertType.ERROR);
			redirectAttributes.addFlashAttribute("message", message);
		}
		return "redirect:/system/role/view";
	}
	
	@RequestMapping(value = "delete/{ids}", method = RequestMethod.GET)
	public String delete(@PathVariable("ids") Long[] ids, RedirectAttributes redirectAttributes) {
		this.roleService.batchDelete(ids);
		WebMessage message = new WebMessage("角色删除成功", AlertType.SUCCESS);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/system/role/view";
	}
	
	/**
	 * 进入对角色添加资源管理权限页面
	 * @param id 角色Id
	 * @param model
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "addResources/{id}", method = RequestMethod.GET)
	public String addResources(@PathVariable("id") Long id, Model model, ServletRequest request) {
		Role role = this.roleService.findById(id);
		List<Resource> menus = this.resourceService.findMenuResources();
		List<Resource> newMenus = new ArrayList<Resource>();
		for (Resource r : menus) {
			if (StringUtils.isEmpty(r.getUrl())) {
				newMenus.add(r);
			}
		}
		model.addAttribute("role",role);
		model.addAttribute("menus", newMenus);
		return "system/role/addResource";
	}
	
	/**
	 * 对角色添加资源管理权限
	 * @param id 角色Id
	 * @param resourceIds 系统资源Id数组
	 * @param redirectAttributes
	 * @return 返回列表页面
	 */
	@RequestMapping(value = "saveResources/{roleId}/{resourceIds}", method = RequestMethod.GET)
	public String saveResources(@PathVariable("roleId") Long id,@PathVariable("resourceIds") Long[] resourceIds, RedirectAttributes redirectAttributes) {
		this.roleService.addResources(id, resourceIds);
		WebMessage message = new WebMessage("角色授权成功", AlertType.SUCCESS);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/system/role/view";
	}
	
	/**
	 * 进入角色编辑页面
	 * @param id 用户Id
	 * @param model
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model, ServletRequest request) {
		model.addAttribute("statuss", Status.values());
		Role role = this.roleService.findById(id);
		model.addAttribute("role", role);
		return "system/role/editRoleForm";
	}
	
	/**
	 * 更新角色信息
	 * @param role 角色信息
	 * @param request
	 * @param redirectAttributes
	 * @return 返回角色列表页面
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Role role, ServletRequest request, RedirectAttributes redirectAttributes) {
		Role r = this.roleService.findById(role.getId());
		r.setName(role.getName());
		r.setStatus(role.getStatus());
		this.roleService.saveOrUpdate(r);
		WebMessage alertMessage = new WebMessage("角色信息更新成功", AlertType.SUCCESS);
		redirectAttributes.addFlashAttribute("message", alertMessage);
		return "redirect:/system/role/view";
	}
	
	/**
	 * 判断角色英文名是否存在
	 * @param roleName 角色英文名称
	 * @return true/false 不存在返回true,否则返回false
	 */
	@RequestMapping(value = "isExistRoleName", method = RequestMethod.POST)
	@ResponseBody
	public Boolean isNotExistRoleName(String roleName) {
		Boolean isExist = this.roleService.isExistRoleName(roleName);
		return !isExist;
	}
}
