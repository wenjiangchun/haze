package com.xinyuan.haze.system.exception;

import com.xinyuan.haze.core.service.HazeServiceException;

public class UserNameExistException extends HazeServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNameExistException() {
		super();
	}

	public UserNameExistException(String message) {
		super(message);
	}

}
