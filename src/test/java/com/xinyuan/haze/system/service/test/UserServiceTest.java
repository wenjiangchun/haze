package com.xinyuan.haze.system.service.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyuan.haze.system.entity.Group;
import com.xinyuan.haze.system.entity.Role;
import com.xinyuan.haze.system.entity.User;
import com.xinyuan.haze.system.exception.UserNameExistException;
import com.xinyuan.haze.system.service.UserService;
import com.xinyuan.haze.system.utils.Sex;
import com.xinyuan.haze.system.utils.Status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-test.xml"})
@ActiveProfiles("test")
public class UserServiceTest {

	@Autowired 
	private UserService userService;
	
	public void testSave() {
			User user = new User();
			user.setName("admin");
			user.setLoginName("admin");
			user.setPassword("123456");
			user.setEmail("admin@hotmail.com");
			user.setSex(Sex.M);
			user.setStatus(Status.E);
			try {
				userService.saveOrUpdate(user);
			} catch (UserNameExistException e) {
				e.printStackTrace();
			}
		
	}
	
	public void testAddRole() {
		Long id = 2l;
		Role role = new Role();
		role.setId(2L);
		this.userService.addRole(id, role);
	}
	
	public void testAddOrUpdateGroup() {
		Long id = 0l;
		Group group = new Group();
		group.setId(1l);
		this.userService.addOrUpdateGroup(id, group);
	}
	@Test
	public void testFindBySex() {
		Map<String, Object> paramVirables = new HashMap<String, Object>();
		/*paramVirables.put("loginName", "admin");
		paramVirables.put("cname", "管理员");*/
		Group g = new Group();
		g.setName("总机构");
		paramVirables.put("group1.name", "总机构");
		Page<User> p = this.userService.findPage(new PageRequest(0, 10), paramVirables);
		System.out.println(p.getNumber());
	}
	
	public void testBatchDelete() {
		Long[] ids = new Long[]{6l};
		this.userService.batchDelete(ids);
	}
	
	public void testDeleteById() {
		Long id = 2l;
		this.userService.deleteById(id);
	}
	
}
