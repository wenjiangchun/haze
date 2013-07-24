package com.xinyuan.haze.web.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.xinyuan.haze.system.entity.Resource;
import com.xinyuan.haze.system.service.ResourceService;

/**
 * 系统 Home Controller
 * 
 */
@Controller
public class HomeController {

	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value = "/changeLanguage", method = RequestMethod.POST)
	public String changeLanguage(String language, HttpServletRequest request,
			HttpServletResponse response) {
		String message = "";
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		if (localeResolver == null) {
			throw new IllegalStateException(
					"No LocaleResolver found: not in a DispatcherServlet request?");
		}
		LocaleEditor localeEditor = new LocaleEditor();
		localeEditor.setAsText(language);
		localeResolver.setLocale(request, response,
				(Locale) localeEditor.getValue());
		message = "Change Language Success!";
		return message;
	}
	
    @RequestMapping(value ="/changeTheme", method = RequestMethod.POST)  
    public void changeTheme(HttpServletRequest request, HttpServletResponse response, String theme) { 
    	ThemeResolver themeResolver = RequestContextUtils.getThemeResolver(request);
        themeResolver.setThemeName(request, response, theme);  
    }  
    
    @RequestMapping(value ="/left")
    public String getMenuResource(Model model) {
    	List<Resource> resources = resourceService.findMenuResources();
    	model.addAttribute("resources", resources);
    	return "left";
    }
}
