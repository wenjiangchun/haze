package com.xinyuan.haze.workflow.exception;

import com.xinyuan.haze.common.exception.HazeException;

/**
 * 流程部署失败异常定义类
 * @author Sofar
 *
 */
public class DeployFailureException extends HazeException{

	private static final long serialVersionUID = 1L;

	public DeployFailureException(String message) {
		super(message);
	}

}
