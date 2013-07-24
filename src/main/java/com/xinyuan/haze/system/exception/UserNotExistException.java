package com.xinyuan.haze.system.exception;

import org.apache.shiro.authc.AuthenticationException;

public class UserNotExistException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotExistException() {
		super();
	}

	public UserNotExistException(String message) {
		super(message);
	}
}
