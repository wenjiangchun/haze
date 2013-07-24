package com.xinyuan.haze.schedule.job.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.haze.core.entity.BaseEntity;

@Entity
@Table(name = "sched_groovy_job")
public class GroovyJob extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	private String name;
	private String codeContent;
	private String description;

	private String jobClassName;
	
	private Boolean isCompile;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeContent() {
		return codeContent;
	}

	public void setCodeContent(String codeContent) {
		this.codeContent = codeContent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public Boolean getIsCompile() {
		return isCompile;
	}

	public void setIsCompile(Boolean isCompile) {
		this.isCompile = isCompile;
	}

	@Override
	public String toString() {
		return "GroovyJob [name=" + name + "]";
	}

}
