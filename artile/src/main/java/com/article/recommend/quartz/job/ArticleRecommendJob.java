package com.article.recommend.quartz.job;

import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.hadoop.util.HdfsUtil;
import com.article.recommend.recommend.baseiterm.ItermRecommend;
import com.article.recommend.recommend.baseuser.UserRecommend;
import com.article.recommend.util.DateUtil;
import com.article.recommend.util.SpringUtil;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.hdfs.server.common.JspHelper.Url;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

import javax.sound.midi.Soundbank;

/**
 * 定时任务执行推荐job
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class ArticleRecommendJob implements BaseJob{
    private static Logger logger= LoggerFactory.getLogger(ArticleRecommendJob.class);
   


     @Override
    public void execute(JobExecutionContext jobExecutionContext)  {
    	 //用户行为数据
    	 String path= RecommendConstant.BASEPATH+RecommendConstant.USERPREFS_PATH;
     	 String dataPath=path+File.separatorChar+RecommendConstant.USERPREFS_DATA_PATH;
    	 String tmp= path+File.separatorChar+"tmp.txt";
    	 logger.info("推荐job作业启动");
        //1.删除过期数据
        try {
            deleteLoseData(path,dataPath);
            //2.合并文件 tmp下
             HdfsUtil.copyMerge(dataPath,tmp);
            //3.下载下来,执行推荐操作
             String local=ClassUtils.getDefaultClassLoader().getResource("data/tmp/tmp.txt").getPath();
             File file=new File(local);
             if(file.exists()) {
                 file.delete();
             }
            HdfsUtil.downFile(tmp, local);
            //用户
            UserRecommend userRecommend= SpringUtil.getBean(UserRecommend.class);
            userRecommend.evaluateRecommend(local);
            //物品
            ItermRecommend itermRecommend=SpringUtil.getBean(ItermRecommend.class);
            itermRecommend.evaluateRecommend(local);
            //规则
         } catch (Exception e) {
             e.printStackTrace();
         }
        logger.info("推荐job作业结束");

     }

    /**
     * 删除目录下无效的行为数据（转移到别的地方）
     * @param path
     */
    public void deleteLoseData(String path,String dataPath) throws IOException, ParseException {
  	  String movePath=path+File.separatorChar+RecommendConstant.USERPREFS_LOSEDATA_PATH;
        logger.info("行为数据路径：{}",dataPath);
        FileStatus[] fileStatuse= HdfsUtil.getFiles(dataPath);
        String businessDate= DateUtil.getBeforeDate(30,DateUtil.DATE);
        logger.info("有效行为数据的截止日期:{}",businessDate);
        String dateName=null;
        String fileName=null;
        for(int i=0;i<fileStatuse.length;i++){
            fileName=fileStatuse[i].getPath().getName();
            dateName =fileName.substring(0,fileName.lastIndexOf("."));
            int compareResult=DateUtil.compareDateStr(dateName,businessDate);
            if(compareResult<1){//满足条件，将数据转移到失效文件夹下
                   HdfsUtil.moveFile(dataPath+"/"+fileName,movePath+"/"+fileName);
            }
        }
    }
    public static void main(String[] args) {
    	String path= ClassUtils.getDefaultClassLoader().getResource("data/tmp/tmp.txt").getPath();
    	System.out.println(path);
	}
}
