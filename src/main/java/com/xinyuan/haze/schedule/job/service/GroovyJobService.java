package com.xinyuan.haze.schedule.job.service;

import java.io.IOException;

import groovy.lang.GroovyClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xinyuan.haze.core.service.AbstractBaseService;
import com.xinyuan.haze.schedule.job.dao.GroovyJobDao;
import com.xinyuan.haze.schedule.job.entity.GroovyJob;

/**
 * 自定义Groovy作业信息业务操作类
 * @author Sofar
 *
 */
@Component
@Transactional(readOnly = true)
public class GroovyJobService extends AbstractBaseService<GroovyJob,Long> {
	
	private GroovyJobDao groovyJobDao;

	@Autowired
	public void setGroovyJobDao(GroovyJobDao groovyJobDao) {
		this.groovyJobDao = groovyJobDao;
		super.setDao(groovyJobDao);
	}

	public Boolean isExistName(String name) {
		GroovyJob groovyJob = this.groovyJobDao.findByName(name);
		return groovyJob != null;
	}
	
	@Transactional(readOnly = false)
	public void compileGroovyJob(Long id) {
		GroovyJob groovyJob = this.findById(id);
		ClassLoader cl = getClass().getClassLoader();
		GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
		Class<?> c = groovyCl.parseClass(groovyJob.getCodeContent());
		try {
			groovyCl.loadClass(c.getName());
			groovyCl.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		groovyJob.setJobClassName(c.getName());
		groovyJob.setIsCompile(true);
	}
}
