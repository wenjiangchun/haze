package com.xinyuan.haze.system.dao;

import org.springframework.stereotype.Repository;

import com.xinyuan.haze.core.jpa.repository.BaseRepository;
import com.xinyuan.haze.system.entity.Config;

/**
 * 系统配置Dao接口定义类
 * @author Sofar
 *
 */
@Repository
public interface ConfigDao extends BaseRepository<Config, Long> {

	/**
	 * 根据配置名称查找配置对象
	 * @param configName 配置名称
	 * @return 配置对象
	 */
	Config findByConfigName(String configName);

}
