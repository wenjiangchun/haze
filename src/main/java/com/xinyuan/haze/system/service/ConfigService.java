package com.xinyuan.haze.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.xinyuan.haze.core.service.AbstractBaseService;
import com.xinyuan.haze.system.dao.ConfigDao;
import com.xinyuan.haze.system.entity.Config;
import com.xinyuan.haze.system.exception.ConfigCannotDeleteException;
import com.xinyuan.haze.system.exception.ConfigNameExistException;
import com.xinyuan.haze.system.utils.ConfigType;

/**
 * 系统配置业务操作类
 * @author Sofar
 *
 */
@Service
@Transactional(readOnly=true)
public class ConfigService extends AbstractBaseService<Config, Long> {

	private ConfigDao configDao;
	
	@Autowired
	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
		super.setDao(configDao);
	}
	
	/**
	 * 根据配置名称查找配置对象 并将查询结果加入缓存
	 * @param configName 配置名称
	 * @return 配置对象
	 */
	@Cacheable(value="configCache",key="#configName")
	public Config findByConfigName(String configName) {
		return this.configDao.findByConfigName(configName);
	}

	/**
	 * 保存或更新配置对象 并更新缓存对象
	 * @param config 配置对象
	 * @throws ConfigNameExistException 配置名称已存在异常
	 */
	@CachePut(value="configCache",key="#config.configName")
	@Transactional(readOnly=false)
	public Config saveOrUpdate(Config config) throws ConfigNameExistException {
		Assert.notNull(config);
		Config c = this.configDao.findByConfigName(config.getConfigName());
		if (c != null && config.isNew()) {
			logger.error("配置名称{}已存在，请重试！",config.getConfigName());
			throw new ConfigNameExistException("配置名称" + config.getConfigName() + "已存在，请重试");
		} else {
			if (config.isNew()) { //保存配置对象
				logger.info("添加配置信息，配置信息为：{}", config);
			} else { //更新配置对象
				logger.info("更新配置信息，配置信息为：{}", config);
			}
		}
		return this.save(config);
	}
	
	/**
	 * 删除配置对象 
	 * @param id 配置对象Id
	 */
	@Transactional(readOnly=false)
	public Config deleteConfig(Long id) {
		Assert.notNull(id);
		Config config = this.findById(id);
		return this.deleteConfig(config);
	}
	
	/**
	 * 批量删除配置信息 并清空缓存
	 * @param ids 配置信息Id数组
	 * @throws ConfigCannotDeleteException
	 */
	@CacheEvict(value="configCache",allEntries=true)
	@Transactional(readOnly=false)
	public void deleteConfigs(Long[] ids) {
		for (Long id : ids) {
			this.deleteConfig(id);
		}
	}
	
	/**
	 * 删除配置对象 并将缓存中该对象清空 如果配置类型为系统配置则不允许删除
	 * @param config 配置对象
	 * @return 配置对象
	 */
	@CacheEvict(value="configCache",key="#config.configName")
	@Transactional(readOnly=false)
	public Config deleteConfig(Config config) {
		if (config.getConfigType() == ConfigType.S) {
			logger.error("配置信息{}为系统配置，不允许被删除", config);
			throw new ConfigCannotDeleteException("Config can not be deleted Because it is the System's Config！");
		} else {
			logger.debug("删除配置信息，配置信息为：{}",config);
			this.delete(config);
		}
		 return config;
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(Config t) {
		deleteConfig(t);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		deleteConfig(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void batchDelete(Long[] ids) {
		deleteConfigs(ids);
	}
	
	/**
	 * 更新Config对象 并将缓存中该对象清空
	 * @param config Config实例
	 * @throws ConfigNameExistException 配置名称已存在异常
	 */
	@CacheEvict(value="configCache",key="#config.configName")
	@Transactional(readOnly=false)
	public Config updateConfig(Config config) throws ConfigNameExistException {
		Assert.notNull(config);
		Long id = config.getId();
		Config cfg = this.findById(id);
		cfg.setValue(config.getValue());
		this.saveOrUpdate(cfg);
		return cfg;
	}

	/**
	 * 判断配置名称是否存在
	 * @param configName 配置名称
	 * @return true/false
	 */
	public Boolean isExistConfigName(String configName) {
		Config config = this.findByConfigName(configName);
		return config != null;
	}
	
}
