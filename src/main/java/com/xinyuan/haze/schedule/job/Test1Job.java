package com.xinyuan.haze.schedule.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Test1Job  implements Job {

	public void sayHello() {
		System.out.println("Test1:Hello Quartz");
	}

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		sayHello();
		String triggerKey = context.getTrigger().getKey().toString();
		System.out.println("triggerKey="+triggerKey);
	}
	
}
