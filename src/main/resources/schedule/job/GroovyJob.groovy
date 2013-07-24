package com.xinyuan.haze.schedule.job
import org.quartz.Job
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
class GroovyHelloWordJob implements HazeJob {
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("groovy 定时调用");
	}
}
