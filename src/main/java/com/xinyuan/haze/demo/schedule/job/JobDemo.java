package com.xinyuan.haze.demo.schedule.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobDemo implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		String triggerKey = context.getTrigger().getKey().toString();
		System.out.println("triggerKey="+triggerKey);

	}

}
