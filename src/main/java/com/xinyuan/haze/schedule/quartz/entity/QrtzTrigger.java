package com.xinyuan.haze.schedule.quartz.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="QRTZ_TRIGGERS")
public class QrtzTrigger implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QrtzTriggerPK id;

	private String description;

	private String endTime;

	@Lob
	private byte[] jobData;

	private short misfireInstr;

	private String nextFireTime;

	private String prevFireTime;

	private int priority;

	private String startTime;

	private String triggerState;

	private String triggerType;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="JOB_GROUP", referencedColumnName="JOB_GROUP"),
		@JoinColumn(name="JOB_NAME", referencedColumnName="JOB_NAME"),
		@JoinColumn(name="SCHED_NAME", referencedColumnName="SCHED_NAME")
		})
	private QrtzJobDetail qrtzJobDetail;

	public QrtzTrigger() {
	}

	public QrtzTriggerPK getId() {
		return this.id;
	}

	public void setId(QrtzTriggerPK id) {
		this.id = id;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public byte[] getJobData() {
		return this.jobData;
	}

	public void setJobData(byte[] jobData) {
		this.jobData = jobData;
	}

	public short getMisfireInstr() {
		return this.misfireInstr;
	}

	public void setMisfireInstr(short misfireInstr) {
		this.misfireInstr = misfireInstr;
	}

	public String getNextFireTime() {
		return this.nextFireTime;
	}

	public void setNextFireTime(String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public String getPrevFireTime() {
		return this.prevFireTime;
	}

	public void setPrevFireTime(String prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTriggerState() {
		return this.triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}

	public String getTriggerType() {
		return this.triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public QrtzJobDetail getQrtzJobDetail() {
		return this.qrtzJobDetail;
	}

	public void setQrtzJobDetail(QrtzJobDetail qrtzJobDetail) {
		this.qrtzJobDetail = qrtzJobDetail;
	}

	/**
	 * 调度表达式
	 */
	@Transient
	private String cronExpression;
	
	/**
	 * 重复次数
	 */
	@Transient
	private Integer repeatCount;
	
	/**
	 * 重复间隔时间
	 */
	@Transient
	private Integer repeatInteval;
	
	/**
	 * 重复间隔时间单位
	 */
	@Transient
	private RepeateIntevalUnit repeatIntevalUnit;
	
	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Integer getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}

	public Integer getRepeatInteval() {
		return repeatInteval;
	}

	public void setRepeatInteval(Integer repeatInteval) {
		this.repeatInteval = repeatInteval;
	}

	public RepeateIntevalUnit getRepeatIntevalUnit() {
		return repeatIntevalUnit;
	}

	public void setRepeatIntevalUnit(RepeateIntevalUnit repeatIntevalUnit) {
		this.repeatIntevalUnit = repeatIntevalUnit;
	}

	
}