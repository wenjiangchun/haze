package com.xinyuan.haze.system.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyuan.haze.system.entity.Group;
import com.xinyuan.haze.system.exception.UserNameExistException;
import com.xinyuan.haze.system.service.GroupService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-test.xml"})
@ActiveProfiles("test")
public class GroupServiceTest {

	@Autowired 
	private GroupService groupService;
	
	@Test
	public void testSave() {
			Group group = new Group();
			group.setId(0l);
			group.setName("总机构");
			try {
				groupService.save(group);
			} catch (UserNameExistException e) {
				e.printStackTrace();
			}
		
	}
}
