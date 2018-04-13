package com.article.recommend.threadpool;

import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.service.dictionary.DictionaryService;
import com.article.recommend.threadpool.assist.ManagerContext;
import com.article.recommend.threadpool.manager.AbstractManage;
import com.article.recommend.threadpool.manager.ManagerDataFactory;
import com.article.recommend.threadpool.task.Task;
import com.article.recommend.threadpool.task.TaskFactory;
import com.article.recommend.util.SpringUtil;

import org.springframework.stereotype.Service;

/**
 * 线程池服务实现
 */
@Service
public class ThreadFactoryServiceImpl implements ThreadFactoryService  {
	@Override
	public AbstractManage execInteractiveTask(Task workerTask, Task consumorTask) {
		ManagerDataFactory mdf = ManagerDataFactory.createDataFactory();
		ManagerContext context = mdf.getManagerContext();
		context.setInteractive(true);
		context.setWorkerTask(workerTask);
		context.setConsumorTask(consumorTask);
		AbstractManage manage = mdf.getAbstractManage(context);
		try {
			manage.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return manage;
	}
	@Override
	public  AbstractManage execSingleTasks(Task... osTasks) {
		ManagerDataFactory managerDataFactory = ManagerDataFactory.createDataFactory();
		ManagerContext context = managerDataFactory.getManagerContext();
		context.setTasks(osTasks);
		context.setInteractive(false);
		AbstractManage manage = managerDataFactory.getAbstractManage(context);
		try {
			manage.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return manage;
	}


	@SuppressWarnings("rawtypes")
	public AbstractManage executeSingleTask(Object data, Class...classNames) throws InstantiationException, IllegalAccessException{
		TaskFactory taskFactory=TaskFactory.createTaskFactory();
		Task [] tasks=new Task[classNames.length];
		for (int i=0;i<classNames.length;i++){
			String keyName=classNames.getClass().getName();
			int num=RecommendConstant.TASK_DEFAULT_NUM;
			try {
				DictionaryService dictionaryService=SpringUtil.getBean(DictionaryService.class);
				num=Integer.parseInt(dictionaryService.getDictionaryByKey(keyName).getValue());
			}catch (Exception e) {
			}
			@SuppressWarnings("unchecked")
			Task task=taskFactory.get(classNames[i],num);
			task.getThreadContext().setTaskData(data);
			tasks[i]=task;
		}
		return execSingleTasks(tasks);
	}

}
