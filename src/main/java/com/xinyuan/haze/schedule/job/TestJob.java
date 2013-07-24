package com.xinyuan.haze.schedule.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob  implements Job {

	public void sayHello() {
		System.out.println("Test:Hello Quartz");
	}

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		sayHello();
	}
	
}
