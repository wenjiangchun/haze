package com.xinyuan.haze.schedule.quartz.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="QRTZ_JOB_DETAILS")
public class QrtzJobDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QrtzJobDetailPK id;

	private String description;

	private boolean isDurable;

	private boolean isNonconcurrent;

	private boolean isUpdateData;

	private String jobClassName;

	@Lob
	private byte[] jobData;

	private boolean requestsRecovery;

	@OneToMany(mappedBy="qrtzJobDetail")
	private Set<QrtzTrigger> qrtzTriggers;

	public QrtzJobDetail() {
	}

	public QrtzJobDetail(QrtzJobDetailPK id) {
		this.id = id;
	}

	public QrtzJobDetailPK getId() {
		return this.id;
	}

	public void setId(QrtzJobDetailPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsDurable() {
		return this.isDurable;
	}

	public void setIsDurable(boolean isDurable) {
		this.isDurable = isDurable;
	}

	public boolean getIsNonconcurrent() {
		return this.isNonconcurrent;
	}

	public void setIsNonconcurrent(boolean isNonconcurrent) {
		this.isNonconcurrent = isNonconcurrent;
	}

	public boolean getIsUpdateData() {
		return this.isUpdateData;
	}

	public void setIsUpdateData(boolean isUpdateData) {
		this.isUpdateData = isUpdateData;
	}

	public String getJobClassName() {
		return this.jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public byte[] getJobData() {
		return this.jobData;
	}

	public void setJobData(byte[] jobData) {
		this.jobData = jobData;
	}

	public boolean getRequestsRecovery() {
		return this.requestsRecovery;
	}

	public void setRequestsRecovery(boolean requestsRecovery) {
		this.requestsRecovery = requestsRecovery;
	}

	public Set<QrtzTrigger> getQrtzTriggers() {
		return this.qrtzTriggers;
	}

	public void setQrtzTriggers(Set<QrtzTrigger> qrtzTriggers) {
		this.qrtzTriggers = qrtzTriggers;
	}

	public QrtzTrigger addQrtzTrigger(QrtzTrigger qrtzTrigger) {
		getQrtzTriggers().add(qrtzTrigger);
		qrtzTrigger.setQrtzJobDetail(this);
		return qrtzTrigger;
	}

	public QrtzTrigger removeQrtzTrigger(QrtzTrigger qrtzTrigger) {
		getQrtzTriggers().remove(qrtzTrigger);
		qrtzTrigger.setQrtzJobDetail(null);
		return qrtzTrigger;
	}
	
}