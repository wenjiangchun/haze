package com.xinyuan.haze.core.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.xinyuan.haze.system.utils.ResourceType;

/**
 * Haze核心功能日志注解，对需要添加日志功能的方法上添加该注解
 * @author Sofar
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HazeLog { 
	boolean value() default true; 
	String type() default "";
	ResourceType resourceType() default ResourceType.M;
}
