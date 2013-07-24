package com.xinyuan.haze.schedule.quartz.service.test;

import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.io.IOException;

import org.codehaus.groovy.control.CompilationFailedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-test.xml"})
@ActiveProfiles("test")
public class ScheduleServiceTest {

	@Test
	public void testAddJobDetail() throws CompilationFailedException, IOException, ClassNotFoundException {
		//schedulerService.addJobDetail();
		File f = new File("src/main/resources/schedule/job/GroovyHelloWordJob.groovy");
		ClassLoader cl = getClass().getClassLoader();
		GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
		Class c = groovyCl.parseClass(f);
		groovyCl.close();
		//scheduleService.addJobDetail1("groovy作业测试", c);
	}

}
