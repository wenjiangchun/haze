package com.xinyuan.haze.log.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.haze.core.entity.BaseEntity;

/**
 * 系统配置实体类
 * @author Sofar
 *
 */
@Entity
@Table(name="sec_config")
public class Log extends BaseEntity<Long> {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
