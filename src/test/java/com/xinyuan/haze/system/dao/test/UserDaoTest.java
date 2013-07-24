package com.xinyuan.haze.system.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyuan.haze.system.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml",
		"/activiti/applicationContext-activiti.xml",
		"/security/applicationContext-shiro.xml" })
@ActiveProfiles("test")
public class UserDaoTest {
	@Autowired
	private UserService userService;

	@Test
	public void testFindByTest() {
		List<Long> ids = new ArrayList<Long>();
		ids.add(1l);
		ids.add(2l);
		userService.findAll();
	}

}
