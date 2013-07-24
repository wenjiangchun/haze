package com.xinyuan.haze.demo.article.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyuan.haze.demo.article.service.EquipmentInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-test.xml"})
@ActiveProfiles("test")
public class EquipmentInfoServiceTest {

	@Autowired 
	private EquipmentInfoService equipmentInfoService;
	
	@Test
	public void testPage() {
		Map<String,Object> queryVirables = new HashMap<String,Object>();
		equipmentInfoService.findPage(new PageRequest(0, 1), queryVirables);
	}
	
}
