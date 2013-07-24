package com.xinyuan.haze.script.support.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xinyuan.haze.core.entity.BaseEntity;
import com.xinyuan.haze.script.support.utils.DynamicScriptType;

/**
 * 脚本语言实体类
 * @author Sofar
 *
 */
@Entity
@Table(name = "dynamic_script")
public class DynamicScript extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	/**
	 * 脚本名称
	 */
	private String scriptName; 
	
	/**
	 * 脚本内容
	 */
	private String scriptSource;
	
	/**
	 * 最后修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;

	/**
	 * 脚本类型
	 */
	@Enumerated(EnumType.STRING)
	private DynamicScriptType dynamicScriptType;
	
	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public String getScriptSource() {
		return scriptSource;
	}

	public void setScriptSource(String scriptSource) {
		this.scriptSource = scriptSource;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public DynamicScriptType getDynamicScriptType() {
		return dynamicScriptType;
	}

	public void setDynamicScriptType(DynamicScriptType dynamicScriptType) {
		this.dynamicScriptType = dynamicScriptType;
	}

	@Override
	public String toString() {
		return "ScriptGroovy[scriptName=" + scriptName+",lastUpdated="+lastUpdated+"]";
	}

}