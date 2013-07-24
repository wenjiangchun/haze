package com.xinyuan.haze.security.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyuan.haze.system.entity.Role;
import com.xinyuan.haze.system.exception.RoleExistException;
import com.xinyuan.haze.system.service.RoleService;
import com.xinyuan.haze.system.utils.Status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
@ActiveProfiles("test")
public class RoleServiceTest {

	@Autowired
	private RoleService roleService;
	
	public void testFindAll() {
		fail("Not yet implemented");
	}

	public void testFindByStatus() {
		fail("Not yet implemented");
	}

	public void testFindAllRoleNameByStatus() {
		fail("Not yet implemented");
	}

	public void testSave() {
		Role role = new Role();
		role.setRoleName("user");
		role.setName("普通用户角色");
		role.setStatus(Status.E);
		try {
			roleService.saveOrUpdate(role);
		} catch (RoleExistException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddResource() {
		Long id = 2l;
		Long[] ids = new Long[]{108l};
		this.roleService.addResources(id, ids);
	}
}
