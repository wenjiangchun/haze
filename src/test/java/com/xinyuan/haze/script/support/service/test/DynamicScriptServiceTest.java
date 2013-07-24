package com.xinyuan.haze.script.support.service.test;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinyuan.haze.script.support.entity.DynamicScript;
import com.xinyuan.haze.script.support.service.DynamicScriptService;
import com.xinyuan.haze.script.support.utils.DynamicScriptType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-test.xml"})
@ActiveProfiles("test")
public class DynamicScriptServiceTest {

	@Autowired
	private DynamicScriptService dynamicScriptService;
	public void testFindByScriptName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		DynamicScript ds = new DynamicScript();
		ds.setDynamicScriptType(DynamicScriptType.GROOVY);
		ds.setScriptName("groovy/GroovyPdfGenerator.groovy");
		ds.setScriptSource("import com.xinyuan.haze.demo.groovy.Messenger;class GroovyMessenger implements Messenger {String message;public String getMessage() {return this.message+\"新的Groovy信息\";}}");
		ds.setLastUpdated(new Date());
		dynamicScriptService.save(ds);
	}

}
