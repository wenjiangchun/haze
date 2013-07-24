package com.xinyuan.haze.schedule.quartz.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.quartz.JobKey;

/**
 * QrtzJobDetail主键定义类
 * @author sofar
 *
 */
@Embeddable
public class QrtzJobDetailPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="SCHED_NAME")
	private String schedName;
	
	@Column(name="JOB_NAME")
	private String jobName;
	
	@Column(name="JOB_GROUP")
	private String jobGroup;

	public QrtzJobDetailPK() {
	}
	
	public QrtzJobDetailPK(String schedName, String jobName, String jobGroup) {
		super();
		this.schedName = schedName;
		this.jobName = jobName;
		this.jobGroup = jobGroup;
	}



	public String getSchedName() {
		return this.schedName;
	}
	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}
	public String getJobName() {
		return this.jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return this.jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof QrtzJobDetailPK)) {
			return false;
		}
		QrtzJobDetailPK castOther = (QrtzJobDetailPK)other;
		return 
			this.schedName.equals(castOther.schedName)
			&& this.jobName.equals(castOther.jobName)
			&& this.jobGroup.equals(castOther.jobGroup);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.schedName.hashCode();
		hash = hash * prime + this.jobName.hashCode();
		hash = hash * prime + this.jobGroup.hashCode();
		return hash;
	}
	
	/**
	 * 将QrtzJobDetailPK对象转换成Quartz中的JobKey对象
	 * @return JobKey
	 */
	public JobKey convertToJobKey() {
	    return new JobKey(jobName, jobGroup);
	}
}