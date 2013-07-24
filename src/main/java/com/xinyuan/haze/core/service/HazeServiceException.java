package com.xinyuan.haze.core.service;

/**
 * Service层公用的Exception. Spring默认对RuntimeException类型的异常进行事务回滚
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 * @author Sofar
 */
public class HazeServiceException extends RuntimeException {

	private static final long serialVersionUID = 1401593546385403720L;

	public HazeServiceException() {
		super();
	}

	public HazeServiceException(String message) {
		super(message);
	}

	public HazeServiceException(Throwable cause) {
		super(cause);
	}

	public HazeServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
