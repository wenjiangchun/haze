package com.xinyuan.haze.schedule.quartz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xinyuan.haze.core.service.AbstractBaseService;
import com.xinyuan.haze.schedule.quartz.dao.QrtzTriggerDao;
import com.xinyuan.haze.schedule.quartz.entity.QrtzTrigger;
import com.xinyuan.haze.schedule.quartz.entity.QrtzTriggerPK;

/**
 * Quartz触发器业务操作类
 * @author sofar
 *
 */
@Component
@Transactional(readOnly = true)
public class QrtzTriggerService extends AbstractBaseService<QrtzTrigger, QrtzTriggerPK> {
	
	private QrtzTriggerDao qrtzTriggerDao;
	
	@Autowired
	private QrtzScheduleService qrtzScheduleService;
	
	@Autowired
	public void setTriggerDao(QrtzTriggerDao qrtzTriggerDao) {
		this.qrtzTriggerDao = qrtzTriggerDao;
		super.setDao(qrtzTriggerDao);
	}

	/**
	 * 获取Quartz中所有Triggers信息
	 * @return QrtzTrigger信息列表
	 */
	public List<Object[]> findAllQuartzTrigger() {
		List<Object[]> triggers = new ArrayList<Object[]>();
		List<Object[]> cronTriggers = qrtzTriggerDao.findCronTriggers();
		List<Object[]> simpleTriggers = qrtzTriggerDao.findSimpleTriggers();
		triggers.addAll(cronTriggers);
		triggers.addAll(simpleTriggers);
	    return triggers;
	}
	
	/**
	 * 判断触发器名称是否存在
	 * @param triggerName 触发器名称
	 * @param triggerGroup 触发器所在组
	 * @return 存在返回true否则返回false
	 */
	public boolean isExistTriggerName(String triggerName, String triggerGroup) {
		QrtzTrigger trigger = this.findById(new QrtzTriggerPK(triggerName, triggerGroup));
		return trigger != null;
	}
}
