package com.article.recommend.quartz.job;

import com.article.recommend.threadpool.ThreadFactoryService;
import com.article.recommend.threadpool.businessTask.TestTask;
import com.article.recommend.util.SpringUtil;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class HelloJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	System.out.println("第一次进来");
        try {
			Thread.currentThread().sleep(230000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("第一次结束");

    }


}
