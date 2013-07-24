package com.xinyuan.haze.system.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinyuan.haze.core.service.AbstractBaseService;
import com.xinyuan.haze.system.dao.GroupDao;
import com.xinyuan.haze.system.entity.Group;

/**
 * 组织机构业务操作类
 * @author Sofar
 *
 */
@Service
@Transactional
public class GroupService extends AbstractBaseService<Group, Long> {

	private GroupDao groupDao;
	
	@Autowired
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
		super.setDao(groupDao);
	}
	
	@Transactional(readOnly=true)
	public List<Group> getTopGroups() {
		return groupDao.getTopGroups();
	}
}
