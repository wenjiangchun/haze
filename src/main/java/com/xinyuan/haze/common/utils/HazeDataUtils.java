package com.xinyuan.haze.common.utils;

import java.util.Date;

/**
 * 日期时间辅助功能类
 * @author Sofar
 *
 */
public class HazeDataUtils {

	public static final String pattern = "yyyy年MM月dd日  HH:mm";

	/**
	 * 获取当前日期时间
	 * @return Date 当前日期时间
	 */
	public static Date getCurrentDate() {
		Date date = new Date();
		return date;
	}
}
