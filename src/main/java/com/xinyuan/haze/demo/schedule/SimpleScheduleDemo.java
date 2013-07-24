package com.xinyuan.haze.demo.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 简单任务调度演示类
 * @author sofar
 *
 */
@Component
public class SimpleScheduleDemo {

	protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 系统启动后每10秒执行一次
	 */
	@Scheduled(fixedDelay = 10000)
	public void testDelayRun() {
		logger.debug("task demo running at per/10s");
	}
	
	/**
	 * 每天下午4点到5点每分钟执行一次
	 */
	@Scheduled(cron="0 0-59 16 * * ?")
	public void testCronRun() {
		logger.debug("task demo running at per/1mininus of PM16-PM17");
	}
}
