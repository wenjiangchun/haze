package com.xinyuan.haze.core.log.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.xinyuan.haze.core.log.annotation.HazeLog;


@Aspect
@Component
public class LogAspect {

    @AfterReturning(value="@annotation(needLog)",returning="result")
    public void needTestFun(JoinPoint jointPoint,HazeLog needLog,Object result) {
    	System.out.println("jp方法名称="+jointPoint.getSignature().getName());
    	System.out.println(needLog.value());
    	System.out.println("result返回值="+result);
        System.out.println("完成日志方法调用");
    }

}
