package com.xinyuan.haze.script.support.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinyuan.haze.core.service.AbstractBaseService;
import com.xinyuan.haze.script.support.dao.DynamicScriptDao;
import com.xinyuan.haze.script.support.entity.DynamicScript;

/**
 * 动态脚本业务操作类
 * @author Sofar
 *
 */
@Service
@Transactional(readOnly = true)
public class DynamicScriptService extends AbstractBaseService<DynamicScript, Long> {

	private DynamicScriptDao dynamicScriptDao;
	
	@Autowired 
	public void setDynamicScriptDao(DynamicScriptDao dynamicScriptDao) {
		this.dynamicScriptDao = dynamicScriptDao;
		super.setDao(dynamicScriptDao);
	}

	/**
	 * 根据脚本名称查找脚本对象
	 * @param scriptName 脚本名称
	 * @return 脚本对象
	 */
	public DynamicScript findByScriptName(String scriptName) {
		return this.dynamicScriptDao.findByScriptName(scriptName);
	}
}
