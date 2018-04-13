package com.article.recommend.threadpooltest;

import com.article.recommend.ArticlerecommendApplicationTests;
import com.article.recommend.threadpool.ThreadFactoryService;
import com.article.recommend.threadpool.ThreadFactoryServiceImpl;
import com.article.recommend.threadpool.businessTask.RecommendValueTask;
import com.article.recommend.threadpool.businessTask.TestTask;
import com.article.recommend.vo.DataVo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskTest extends ArticlerecommendApplicationTests {
    @Autowired
    ThreadFactoryService threadFactoryService ;
    @Test
    public void task() throws Exception {
        /*ThreadFactoryService threadFactoryService=new ThreadFactoryServiceImpl();
        Task testTask=new RecommendValueTask();
        ThreadContext threadContext=new ThreadContext(testTask);

        threadContext.setTaskData("1233");
        testTask.setThreadContext(threadContext);
        testTask.setThreadNum(10);
        threadFactoryService.execSingleTasks(testTask).start();*/
        List<DataVo> data =new ArrayList<>();
        Map<String, List<DataVo>> map=new HashMap<>();
        DataVo dataVo=null;
        for (int i=0;i<200;i++) {
        	dataVo=new DataVo();
        	dataVo.setCount(i);
        	dataVo.setMaxId(1+1);
           data.add(dataVo);
        }
        List<Map<String, List<DataVo>>> datas=new ArrayList<>();
        datas.add(map);
        Map<String, List<DataVo>> map2;
        for(int i=0;i<200;i++) {
        	map2=new HashMap<>();
        	map2.put("userId:"+1, data);
        	datas.add(map2);
        }
        threadFactoryService.executeSingleTask(datas, TestTask.class).start();;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
    	List<DataVo> data =new ArrayList<>();
        Map<String, List<DataVo>> map=new HashMap<>();
        DataVo dataVo=null;
        for (int i=0;i<20000;i++) {
        	dataVo=new DataVo();
        	dataVo.setCount(i);
        	dataVo.setMaxId(1+1);
           data.add(dataVo);
        }
    	ThreadFactoryService threadFactoryService=new ThreadFactoryServiceImpl();
        try {
			threadFactoryService.executeSingleTask(data, TestTask.class).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

    }
}
