package com.xinyuan.haze.common.exception;

/**
 * 不能匿名访问异常定义类
 * @author Sofar
 *
 */
public class CannotAnonymousAccessException extends HazeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CannotAnonymousAccessException() {
		super();
	}

	public CannotAnonymousAccessException(String message) {
		super("message");
	}

	
}
