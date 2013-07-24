package com.xinyuan.haze.security.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyuan.haze.system.entity.Resource;
import com.xinyuan.haze.system.service.ResourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
@ActiveProfiles("test")
public class ResourceServiceTest {

	@Autowired
	private ResourceService resourceService;
	
	
	public void testSave() {
		Resource resource = new Resource();
		resource.setName("角色管理菜单");
		resource.setPermission("system:role");
		resource = this.resourceService.saveOrUpdate(resource);
		Assert.assertSame(1l, resource.getId());
	}

	public void testFindAllPermission() {
		List<String> resources = resourceService.findAllPermission();
		System.out.println(resources);
	}
	@Test
	public void testBatchDeleteResource() {
		Long[] ids = new Long[]{1l,34l,65l,33l};
		this.resourceService.batchDeleteResources(ids);
	}
}
