package com.xinyuan.haze.script.support.dao;

import org.springframework.stereotype.Repository;

import com.xinyuan.haze.core.jpa.repository.BaseRepository;
import com.xinyuan.haze.script.support.entity.DynamicScript;

/**
 * 动态语言接口定义类
 * @author Sofar
 *
 */
@Repository
public interface DynamicScriptDao extends BaseRepository<DynamicScript, Long> {

	/**
	 * 根据脚本名称查找脚本对象
	 * @param scriptName 脚本名称
	 * @return 脚本对象
	 */
	DynamicScript findByScriptName(String scriptName);
}
