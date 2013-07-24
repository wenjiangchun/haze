package com.xinyuan.haze.system.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.xinyuan.haze.core.entity.BaseEntity;
import com.xinyuan.haze.system.utils.ConfigType;

/**
 * 系统配置实体类
 * @author Sofar
 *
 */
@Entity
@Table(name="sec_config")
public class Config extends BaseEntity<Long> {

	/**
	 * 系统配置-验证码配置configName
	 */
	public static final String VALIDATE_CODE = "validateCode";
	
	private static final long serialVersionUID = 1L;
	
	@Column(unique=true)
	private String configName;
	
	private String name;
	
	private String value;
	
	@Enumerated(EnumType.STRING)
	private ConfigType configType;
	
	private String description;
	
	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ConfigType getConfigType() {
		return configType;
	}

	public void setConfigType(ConfigType configType) {
		this.configType = configType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Config [id=" + id + ", configName=" + configName + ", name="
				+ name + ", value=" + value + ", configType=" + configType.getTypeName()
				+ "]";
	}
	
}
