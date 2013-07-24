package com.xinyuan.haze.schedule.quartz.dao;

import org.springframework.stereotype.Repository;

import com.xinyuan.haze.core.jpa.repository.BaseRepository;
import com.xinyuan.haze.schedule.quartz.entity.QrtzJobDetail;
import com.xinyuan.haze.schedule.quartz.entity.QrtzJobDetailPK;

@Repository
public interface QrtzJobDetailDao extends BaseRepository<QrtzJobDetail, QrtzJobDetailPK> {

}
