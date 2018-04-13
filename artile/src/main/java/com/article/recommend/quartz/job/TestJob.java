package com.article.recommend.quartz.job;


import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class TestJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	System.out.println("testJob");
    }


}
