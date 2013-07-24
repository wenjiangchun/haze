package com.xinyuan.haze.security.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyuan.haze.system.entity.Config;
import com.xinyuan.haze.system.service.ConfigService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
@ActiveProfiles("test")

public class ConfigServiceTest {

	@Autowired
	private ConfigService configService;
	@Test
	public void testFindByConfigName() {
		List<Config> configs = this.configService.findAll();
		System.out.print(configs.size());
		configService.deleteById(2l);
		Config con = this.configService.findById(2l);
		System.out.println(con.getValue());
		List<Config> configs1 = this.configService.findAll();
		System.out.print(configs1.size());
		Config config = configService.findByConfigName("validateCode");
		System.out.println(config.getValue());
		Config config1 = configService.findByConfigName("validateCode");
		System.out.println(config1.getValue());
	}

	
	public void testSave() {
		Config config = new Config();
		config.setConfigName("validateCode");
		config.setName("是否启用验证码");
		config.setValue("D");
		this.configService.save(config);
	}

}
