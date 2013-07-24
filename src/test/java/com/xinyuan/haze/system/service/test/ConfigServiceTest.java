package com.xinyuan.haze.system.service.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyuan.haze.system.entity.Config;
import com.xinyuan.haze.system.service.ConfigService;
import com.xinyuan.haze.system.utils.ConfigType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-test.xml"})
@ActiveProfiles("test")
public class ConfigServiceTest {

	@Autowired
	private ConfigService configService;
	
	@Test
	public void testFindByConfigName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveOrUpdate() {
		Config c = new Config();
		c.setConfigName(Config.VALIDATE_CODE);
		c.setConfigType(ConfigType.S);
		c.setName("是否启用登录验证码");
		c.setValue("E");
		c.setDescription("设置在登录时是否开启验证码校验功能，‘E’表示开启，'D'表示禁用");
		configService.saveOrUpdate(c);
	}

	@Test
	public void testDeleteConfigLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteConfigs() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteConfigConfig() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateConfig() {
		fail("Not yet implemented");
	}

}
