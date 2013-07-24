package com.xinyuan.haze.demo.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinyuan.haze.core.service.AbstractBaseService;
import com.xinyuan.haze.demo.article.dao.EquipmentInfoDao;
import com.xinyuan.haze.demo.article.entity.EquipmentInfo;

@Service
@Transactional(readOnly = true)
public class EquipmentInfoService extends AbstractBaseService<EquipmentInfo, String> {
	
	private EquipmentInfoDao equipmentInfoDao;
	
	@Autowired
	public void setEquipmentInfo(EquipmentInfoDao equipmentInfoDao) {
		this.equipmentInfoDao = equipmentInfoDao;
		super.setDao(equipmentInfoDao);
	}

}
