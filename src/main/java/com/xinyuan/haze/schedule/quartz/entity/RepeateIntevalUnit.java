package com.xinyuan.haze.schedule.quartz.entity;

public enum RepeateIntevalUnit {

	HOUR("小时"),
	
	MINUTE("分钟"),
	
	SECOND("秒"),
	
	MILLISECOND("毫秒");
	
	private String unitName;
	
	private RepeateIntevalUnit(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	
}
