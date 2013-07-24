package com.xinyuan.haze.schedule.quartz.service;

import java.util.Set;

import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xinyuan.haze.core.service.AbstractBaseService;
import com.xinyuan.haze.schedule.quartz.dao.QrtzJobDetailDao;
import com.xinyuan.haze.schedule.quartz.entity.QrtzJobDetail;
import com.xinyuan.haze.schedule.quartz.entity.QrtzJobDetailPK;

@Component
@Transactional(readOnly = true)
public class QrtzJobDetailService extends AbstractBaseService<QrtzJobDetail,QrtzJobDetailPK> {
	
	private QrtzJobDetailDao qrtzJobDetailDao;
	
	@Autowired
	public void setJobDetailDao(QrtzJobDetailDao qrtzJobDetailDao) {
		this.qrtzJobDetailDao = qrtzJobDetailDao;
		super.setDao(qrtzJobDetailDao);
	}

}
