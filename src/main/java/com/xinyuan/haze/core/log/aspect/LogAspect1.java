package com.xinyuan.haze.core.log.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;


public class LogAspect1 {

	@Pointcut(value="execution(* com.xinyuan.haze.*.service.*.*(..))")  
    public void pointCutMethod() {  
		
    }  
	

	@Pointcut(value="within(com.xinyuan.haze.*.service..*)")  
    public void pointCutMethod1() {  
		
    }  
	
	//声明前置通知  
   // @Before("")  
    public void doBefore() {  
        System.out.println("前置通知");  
    }  
  
    //声明后置通知  
    @AfterReturning(pointcut = "pointCutMethod1()", returning = "result")  
    public void doAfterReturning(String result) {  
        System.out.println("后置通知");  
        System.out.println("---" + result + "---");  
    }  
  
    //声明例外通知  
    @AfterThrowing(pointcut = "pointCutMethod1()", throwing = "e")  
    public void doAfterThrowing(Exception e) {  
        System.out.println("例外通知");  
        System.out.println(e.getMessage());  
    }  
  
    //声明最终通知  
    @After("pointCutMethod1()")  
    public void doAfter() {  
        System.out.println("最终通知");  
    }  
  
    //声明环绕通知  
    //@Around("")  
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {  
        System.out.println("进入方法---环绕通知");  
        Object o = pjp.proceed();  
        System.out.println("退出方法---环绕通知");  
        return o;  
    }  
}
