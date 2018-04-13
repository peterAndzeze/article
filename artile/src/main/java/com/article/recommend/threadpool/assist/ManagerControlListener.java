package com.article.recommend.threadpool.assist;


import com.article.recommend.threadpool.task.Task;

/**
 * 
 * 外层服务控制监听
 */
public interface ManagerControlListener {
	/**
	 *终止任务
	 * @param currentTask 当前执行任务
	 */
	public void interrupt(Task currentTask);
	
	/**
	 * getter 
	 * @return MakeReportContext
	 */
	public ManagerContext getManagerContext();
	/**
	 * 是否关闭线程池
	 * @return boolean
	 */
	public boolean isShutDown();
	/**
	 * 关闭线程池
	 */
	public void shutDown();
	/**
	 * 通知ReportManage终止任务
	 * @param currentTask 当前任务
	 */
	public void noticeInterrupt(Task currentTask);
}
