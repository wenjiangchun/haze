package com.xinyuan.haze.system.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xinyuan.haze.system.entity.Config;
import com.xinyuan.haze.system.exception.ConfigCannotDeleteException;
import com.xinyuan.haze.system.exception.ConfigNameExistException;
import com.xinyuan.haze.system.exception.UserNameExistException;
import com.xinyuan.haze.system.service.ConfigService;
import com.xinyuan.haze.system.utils.ConfigType;
import com.xinyuan.haze.web.message.WebMessage;
import com.xinyuan.haze.web.ui.bootstrap.BootStrapComponentUtils;
import com.xinyuan.haze.web.ui.bootstrap.css.AlertType;
import com.xinyuan.haze.web.ui.datatable.DataTablePage;
import com.xinyuan.haze.web.ui.datatable.DataTableParames;

/**
 * 系统配置Controller
 * @author Sofar
 *
 */
@Controller
@RequestMapping(value = "/system/config")
public class ConfigController {

	@Autowired
	private ConfigService configService;
	
	@RequestMapping(value = "view")
	public String list(Model model, ServletRequest request) {
		List<Config> configs = this.configService.findAll();
		model.addAttribute("configs", configs);
		return "system/config/configList";
	}
	
	@RequestMapping(value = "search")
	@ResponseBody
	public DataTablePage search(DataTableParames dataTableParames,ServletRequest request) {
		PageRequest p = dataTableParames.getPageRequest();
		Page<Config> configList = this.configService.findPage(p);
		DataTablePage dtp = DataTablePage.newDataTablePage(configList, dataTableParames);
		List<String[]> list = new ArrayList<String[]>();
		for (Config config : configList.getContent()) {
			String id = config.getId().toString();
			String configName = config.getConfigName();
			String name = config.getName();
			String value = config.getValue();
			String configType = "";
			String operatorHTML = "";
			operatorHTML += BootStrapComponentUtils.createLink("edit_"+id,null,"operatorResource('"+id+"','edit')", "编辑").getHtml();
			operatorHTML += " | ";
			operatorHTML += BootStrapComponentUtils.createLink("delete_"+id,null,"operatorResource('"+id+"','delete')", "删除").getHtml();
			list.add(new String[]{id,configName,name, value,configType,operatorHTML});
		}
		dtp.setAaData(list);
		return dtp;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model, ServletRequest request) {
		return "system/config/addConfig";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Config config, RedirectAttributes redirectAttributes) throws UserNameExistException {
		config.setConfigType(ConfigType.B);
		try {
			this.configService.saveOrUpdate(config);
			WebMessage message = new WebMessage("配置信息添加成功", AlertType.SUCCESS);
			redirectAttributes.addFlashAttribute("message", message);
		} catch (ConfigNameExistException e) {
			WebMessage message = new WebMessage("配置信息添加失败", AlertType.SUCCESS);
			redirectAttributes.addFlashAttribute("message", message);
		}
		return "redirect:/system/config/view";
	}
	
	@RequestMapping(value = "delete/{ids}", method = RequestMethod.GET)
	public String delete(@PathVariable("ids") Long[] ids, RedirectAttributes redirectAttributes) {
		try {
			this.configService.deleteConfigs(ids);
			WebMessage message = new WebMessage("配置信息删除成功", AlertType.SUCCESS);
			redirectAttributes.addFlashAttribute("message",message);
		} catch (ConfigCannotDeleteException e) {
			WebMessage message = new WebMessage(e.getMessage(), AlertType.ERROR);
			redirectAttributes.addFlashAttribute("message",message);
		}
		return "redirect:/system/config/view";
	}
	
	@RequestMapping(value="updateConfigValue",method=RequestMethod.GET)  
    @ResponseBody 
	public Config updateConfigValue(@RequestParam(value="id")Long id,@RequestParam(value="value") String value) {
		Config config = new Config();
		config.setId(id);
		config.setValue(value);
		return this.configService.updateConfig(config);
	}
	
	/**
	 * 判断配置名称是否存在
	 * @param configName 配置名称
	 * @return 不存在返回 true / 存在返回 false
	 */
	@RequestMapping(value = "isNotExistConfigName", method = RequestMethod.POST)
	@ResponseBody
	public Boolean isNotExistConfigName(String configName) {
		Boolean isExist = this.configService.isExistConfigName(configName);
		return !isExist;
	}
}
