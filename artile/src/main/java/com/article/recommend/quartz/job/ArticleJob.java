package com.article.recommend.quartz.job;


import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.article.recommend.service.importdataservice.ImportDataService;
import com.article.recommend.util.SpringUtil;
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class ArticleJob implements BaseJob {
	private static Logger logger=LoggerFactory.getLogger(ArticleJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	logger.info("导入文章数据作业 start****");
    	ImportDataService importDataService=SpringUtil.getBean(ImportDataService.class);
    	try {
    		importDataService.importArticleData();
		} catch (Exception e) {
			e.printStackTrace();
	    	logger.error("导入文章数据作业 异常:{}",e.getMessage());
		}
    	logger.info("导入文章数据作业 end****");

    }


}
