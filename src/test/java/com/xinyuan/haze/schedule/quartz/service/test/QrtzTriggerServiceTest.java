package com.xinyuan.haze.schedule.quartz.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyuan.haze.schedule.quartz.entity.QrtzTrigger;
import com.xinyuan.haze.schedule.quartz.service.QrtzTriggerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-test.xml"})
@ActiveProfiles("test")
public class QrtzTriggerServiceTest {

	@Autowired
	private QrtzTriggerService qrtzTriggerService;
	@Test
	public void testFindAllQuartzTrigger() {
		List<Object[]> triggers = qrtzTriggerService.findAllQuartzTrigger();
	    System.out.println(triggers.size());
	}

}
