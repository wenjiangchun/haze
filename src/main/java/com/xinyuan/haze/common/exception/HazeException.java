package com.xinyuan.haze.common.exception;

/**
 * 系统异常定义类
 * @author Sofar
 *
 */
public class HazeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HazeException() {
		super();
	}

	public HazeException(String message) {
		super("message");
	}

	
}
