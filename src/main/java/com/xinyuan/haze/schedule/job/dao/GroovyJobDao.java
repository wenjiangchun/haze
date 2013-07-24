package com.xinyuan.haze.schedule.job.dao;

import org.springframework.stereotype.Repository;

import com.xinyuan.haze.core.jpa.repository.BaseRepository;
import com.xinyuan.haze.schedule.job.entity.GroovyJob;

/**
 * 自定义作业信息Dao接口定义类
 * @author sofar
 *
 */
@Repository
public interface GroovyJobDao extends BaseRepository<GroovyJob, Long> {

	GroovyJob findByName(String name);

}
