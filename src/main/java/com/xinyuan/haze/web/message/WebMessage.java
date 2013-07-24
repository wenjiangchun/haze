package com.xinyuan.haze.web.message;

import java.io.Serializable;

import com.xinyuan.haze.web.ui.bootstrap.css.AlertType;

/**
 * Web信息类，使用该类向页面输入系统信息
 * @author Sofar
 *
 */
public class WebMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 信息内容
	 */
	private String content;
	
	/**
	 * 信息类型
	 */
	private AlertType alertType;
	
	public WebMessage() {
		
	}
	public WebMessage(String content, AlertType alertType) {
		super();
		this.content = content;
		this.alertType = alertType;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public AlertType getAlertType() {
		return alertType;
	}
	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}
	
}
