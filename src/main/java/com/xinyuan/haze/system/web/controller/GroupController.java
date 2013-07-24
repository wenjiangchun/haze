package com.xinyuan.haze.system.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinyuan.haze.system.entity.Group;
import com.xinyuan.haze.system.service.GroupService;
import com.xinyuan.haze.web.ui.bootstrap.BootStrapComponentUtils;
import com.xinyuan.haze.web.ui.datatable.DataTablePage;
import com.xinyuan.haze.web.ui.datatable.DataTableParames;

/**
 * 组织机构Controller
 * @author wenjiangchun
 *
 */
@Controller
@RequestMapping(value = "/system/group")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "view")
	public String list(Model model, ServletRequest request) {
		return "system/group/groupList";
	}
	
	@RequestMapping(value = "search")
	@ResponseBody
	public DataTablePage search(DataTableParames dataTableParames,ServletRequest request) {
		PageRequest p = dataTableParames.getPageRequest(); //根据dataTableParames对象获取JPA分页查询使用的PageRequest对象
		Map<String, Object> map = dataTableParames.getQueryVairables();
		if(map != null && map.get("parent") != null){
			Group g = new Group();
			g.setId(Long.valueOf((String) map.get("parent")));
			map.put("parent", g);
		}
		Page<Group> groupList = this.groupService.findPage(p,map); 
		DataTablePage dtp = DataTablePage.newDataTablePage(groupList, dataTableParames); //将查询结果封装成前台使用的DataTablePage对象
		List<String[]> list = new ArrayList<String[]>();
		for (Group group : groupList.getContent()) {
			String id = group.getId().toString();
			String name = group.getName();
			String operatorHTML = "";
			operatorHTML += BootStrapComponentUtils.createLink("edit_"+id,null,"operatorGroup('"+id+"','edit')", "编辑").getHtml();
			operatorHTML += " | ";
			operatorHTML += BootStrapComponentUtils.createLink("delete_"+id,null,"operatorGroup('"+id+"','delete')", "删除").getHtml();
			list.add(new String[]{id,name,operatorHTML});
		}
		dtp.setAaData(list);
		return dtp;
	}
	
	@RequestMapping(value = "getTopGroups")
	@ResponseBody
	public List<Group> getTopGroups(ServletRequest request, ServletResponse response) {
		List<Group> groups =  groupService.findAll();
		Set<Group> newGroup = new HashSet<Group>();
		for (Group g : groups) {
			g.setUsers(null);
			g.setChilds(null);
			newGroup.add(g);
		}
		return new ArrayList<Group>(newGroup);
	}
}
