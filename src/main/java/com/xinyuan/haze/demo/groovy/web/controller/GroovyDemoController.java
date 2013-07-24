package com.xinyuan.haze.demo.groovy.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xinyuan.haze.demo.groovy.BookingService;
import com.xinyuan.haze.script.support.entity.DynamicScript;
import com.xinyuan.haze.script.support.service.DynamicScriptService;

@Controller
@RequestMapping(value = "/demo/groovy")
public class GroovyDemoController {

	@Autowired
	private BookingService bookingService;
	@Autowired
	private DynamicScriptService dynamicScriptService;
	@RequestMapping(value = "view")
	public String list(Model model, ServletRequest request) {
		String message = bookingService.booking();
		String groovyContent = dynamicScriptService.findByScriptName("GroovyMessenger.groovy").getScriptSource();
	    model.addAttribute("groovyString", groovyContent);
		model.addAttribute("message", message);
		return "demo/groovyDemo";
	}
	
	@RequestMapping(value = "saveGroovy", method = RequestMethod.POST)
	public String saveGroovy(@RequestParam("groovyContent") String groovyContent,ServletRequest request, RedirectAttributes redirectAttributes) {
		DynamicScript ds = dynamicScriptService.findByScriptName("GroovyMessenger.groovy");
		ds.setScriptSource(groovyContent);
		ds.setLastUpdated(new Date());
		dynamicScriptService.save(ds);
		return "redirect:/demo/groovy/view";
	}
	@RequestMapping(value = "text", method = RequestMethod.POST)
	@ResponseBody
	public String test(ServletRequest request, RedirectAttributes redirectAttributes) {
		return "测试中文";
	}
	
	@RequestMapping(value = "text1", method = RequestMethod.POST)
	public void test1(ServletResponse response) throws IOException {
		//response.setCharacterEncoding("utf-8");
		response.getWriter().print("中文车市");
	}
}
