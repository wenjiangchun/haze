package com.xinyuan.haze.system.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xinyuan.haze.system.entity.Role;
import com.xinyuan.haze.system.entity.User;
import com.xinyuan.haze.system.exception.UserNameExistException;
import com.xinyuan.haze.system.service.RoleService;
import com.xinyuan.haze.system.service.UserService;
import com.xinyuan.haze.system.utils.Sex;
import com.xinyuan.haze.system.utils.Status;
import com.xinyuan.haze.web.message.WebMessage;
import com.xinyuan.haze.web.ui.bootstrap.BootStrapComponentUtils;
import com.xinyuan.haze.web.ui.bootstrap.css.AlertType;
import com.xinyuan.haze.web.ui.bootstrap.css.SpanType;
import com.xinyuan.haze.web.ui.datatable.DataTablePage;
import com.xinyuan.haze.web.ui.datatable.DataTableParames;

/**
 * 用户操作Controller
 * @author sofar
 *
 */
@Controller
@RequestMapping(value = "/system/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "view")
	public String list(Model model, ServletRequest request) {
		return "system/user/userList";
	}
	
	/**
	 * 根据查询参数查询用户列表分页对象
	 * @param dataTableParames 包含分页对象和自定义查询对象的参数,其中PageSize
	 * @param request
	 * @return DataTablePage 前台DataTable组件使用的分页数据对象
	 */
	@RequestMapping(value = "search")
	@ResponseBody
	public DataTablePage search(DataTableParames dataTableParames,ServletRequest request) {
		PageRequest p = dataTableParames.getPageRequest(); //根据dataTableParames对象获取JPA分页查询使用的PageRequest对象
		Page<User> userList = this.userService.findPage(p,dataTableParames.getQueryVairables(),false); //过滤掉"admin"对象
		DataTablePage dtp = DataTablePage.newDataTablePage(userList, dataTableParames); //将查询结果封装成前台使用的DataTablePage对象
		List<String[]> list = new ArrayList<String[]>();
		for (User u : userList.getContent()) {
			String id = u.getId().toString();
			String loginName = u.getLoginName();
			String cname = u.getName();
			String email = u.getEmail();
			String rolename = u.getRoleNames();
			String sex = u.getSex().getSexName();
			String statusLabel = getStatusLabel(u.getStatus());
			String operatorHTML = "";
			operatorHTML += BootStrapComponentUtils.createLink("edit_"+id,null,"operatorUser('"+id+"','edit')", "编辑").getHtml();
			operatorHTML += " | ";
			operatorHTML += BootStrapComponentUtils.createLink("addRoles_"+id,null,"operatorUser('"+id+"','addRoles')", "授权").getHtml();
			operatorHTML += " | ";
			operatorHTML += BootStrapComponentUtils.createLink("delete_"+id,null,"operatorUser('"+id+"','delete')", "删除").getHtml();
			list.add(new String[]{id,loginName,cname,email,rolename,sex,statusLabel,operatorHTML});
		}
		dtp.setAaData(list);
		return dtp;
	}

	/**
	 * 根据用户状态生成Span HTML字符串
	 * @param status 用户状态
	 * @return HTML中Span字符串
	 */
	private String getStatusLabel(Status status) {
		String text = status.getStatusName();
		SpanType spanType = SpanType.getSpanTypeByStatus(status);
		return BootStrapComponentUtils.createSpan(null, spanType, text).getHtml();
	}
	
	/**
	 * 进入添加用户页面
	 * @param model
	 * @param request
	 * @return 添加用户页面
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model, ServletRequest request) {
		List<Role> roleList = this.roleService.findByStatus(Status.E); //查找所有启用状态的角色
		model.addAttribute("roleList", roleList);
		model.addAttribute("sexs", Sex.values());
		model.addAttribute("statuss", Status.values());
		return "system/user/addUser";
	}
	
	/**
	 * 保存用户
	 * @param user 用户对象
	 * @param roleIds 角色ID集合
	 * @param request
	 * @param redirectAttributes
	 * @return 返回用户列表页面
	 * @throws UserNameExistException 如果用户名称已存在，则抛出该异常
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(User user, Long[] roleIds, ServletRequest request, RedirectAttributes redirectAttributes) throws UserNameExistException {
		Set<Role> roles = new HashSet<Role>();
		if (roleIds != null) {
			for (Long roleId : roleIds) {
				Role role = new Role();
				role.setId(roleId);
				roles.add(role);
			}
			if (!roles.isEmpty()) {
				user.setRoles(roles);
			}
		}
		this.userService.saveOrUpdate(user);
		WebMessage message = new WebMessage("用户添加成功", AlertType.SUCCESS);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/system/user/view";
	}
	
	@RequestMapping(value = "delete/{ids}", method = RequestMethod.GET)
	public String delete(@PathVariable("ids") Long[] ids, ServletRequest request, RedirectAttributes redirectAttributes) {
		this.userService.batchDelete(ids);
		WebMessage alertMessage = new WebMessage("用户删除成功", AlertType.SUCCESS);
		redirectAttributes.addFlashAttribute("message", alertMessage);
		return "redirect:/system/user/view";
	}
	
	/**
	 * 进入用户添加角色页面
	 * @param id 用户ID
	 * @param model
	 * @return 用户添加角色页面
	 */
	@RequestMapping(value = "addRoles/{id}", method = RequestMethod.GET)
	public String addRoles(@PathVariable("id")Long id, Model model) {
		User user = this.userService.findById(id);
		List<Role> roleList = this.roleService.findByStatus(Status.E);
		model.addAttribute("user", user);
		roleList.removeAll(user.getRoles());
		model.addAttribute("roleList",roleList);
		return "/system/user/addUserRole";
	}
	
	/**
	 * 对用户进行角色授权
	 * @param roleIds 角色ID集合
	 * @param id 用户ID
	 * @param request
	 * @param redirectAttributes
	 * @return 返回用户列表页面
	 */
	@RequestMapping(value = "saveRoles", method = RequestMethod.POST)
	public String saveRoles(@RequestParam(value="roleIds",required=false) Long[] roleIds, @RequestParam("id") Long id, ServletRequest request, RedirectAttributes redirectAttributes) {
		Set<Role> roles = new HashSet<Role>();
		if (null != roleIds) {
			for (Long roleId :roleIds) {
				Role role = new Role();
				role.setId(roleId);
				roles.add(role);
			}
		}
		this.userService.addRoles(id, roles);
		WebMessage alertMessage = new WebMessage("用户授权成功", AlertType.SUCCESS);
		redirectAttributes.addFlashAttribute("message", alertMessage);
		return "redirect:/system/user/view";
	}
	
	/**
	 * 更改用户密码
	 * @param id 用户ID 
	 * @param password 用户密码
	 * @param request
	 * @return User 用户对象
	 */
	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public User updatePassword(Long id, String password, ServletRequest request) {
		return this.userService.updatePassword(id, password);
	}
	
	/**
	 * 进入用户编辑页面
	 * @param id 用户Id
	 * @param model
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model, ServletRequest request) {
		model.addAttribute("sexs", Sex.values());
		model.addAttribute("statuss", Status.values());
		User user = this.userService.findById(id);
		model.addAttribute("user", user);
		return "system/user/editUser";
	}
	
	/**
	 * 更新用户信息
	 * @param user 用户
	 * @param request
	 * @param redirectAttributes
	 * @return 返回用户列表页面
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(User user, ServletRequest request, RedirectAttributes redirectAttributes) {
		User u = this.userService.findById(user.getId());
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setSex(user.getSex());
		u.setStatus(user.getStatus());
		u.setGroup(user.getGroup());
		this.userService.saveOrUpdate(u);
		WebMessage alertMessage = new WebMessage("用户信息更新成功", AlertType.SUCCESS);
		redirectAttributes.addFlashAttribute("message", alertMessage);
		return "redirect:/system/user/view";
	}
	
	/**
	 * 判断登录名是否存在
	 * @param loginName 登录名
	 * @return true/false
	 */
	@RequestMapping(value = "isNotExistLoginName", method = RequestMethod.POST)
	@ResponseBody
	public Boolean isNotExistLoginName(String loginName) {
		Boolean isExist = this.userService.isExistLoginName(loginName);
		return !isExist;
	}
}
