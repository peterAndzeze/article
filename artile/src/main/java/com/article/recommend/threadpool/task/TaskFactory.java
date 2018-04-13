package com.article.recommend.threadpool.task;

import org.springframework.stereotype.Component;

@Component
public class TaskFactory {
	private static TaskFactory taskFactory;
	//单例 交给spring管理
	public static TaskFactory createTaskFactory() {
		if (taskFactory == null) {
			taskFactory = new TaskFactory();
		}
		return taskFactory;
	}
	public Task get(Class<? extends Task> taskClass,int threadNum) throws InstantiationException, IllegalAccessException{
		Task taskInstance = taskClass.newInstance();
		ThreadContext context = ThreadContext.getInstance(taskInstance);
		taskInstance.setThreadContext(context);
		taskInstance.setThreadNum(threadNum);
		return taskInstance;
	}
	
}
