package com.article.recommend.threadpool.businessTask;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sound.midi.Soundbank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.entity.DictionaryInfo;
import com.article.recommend.recommend.RecommendQueueVo;
import com.article.recommend.redis.RedisService;
import com.article.recommend.service.dictionary.DictionaryService;
import com.article.recommend.threadpool.task.BaseTask;
import com.article.recommend.threadpool.task.ThreadContext;
import com.article.recommend.util.SpringUtil;
import com.article.recommend.vo.DataVo;


/**
 *推荐计算评分任务队列
 */
public class TestTask extends BaseTask {
	private static Logger logger=LoggerFactory.getLogger(TestTask.class);
	private RedisService redisService;
	
    @Override
    public Object getSyncObject() {
        return null;
    }

    @Override
    public void init() {
    	redisService=SpringUtil.getBean(RedisService.class);
    }

    @Override
    public void run(ThreadContext context) throws InterruptedException {
      	Map<String, List<DataVo>> map=(Map<String, List<DataVo>>) context.take();
     	if(!map.isEmpty()) {
      		for(Entry<String, List<DataVo>> entry:map.entrySet()) {
      			System.out.println("key************"+entry.getKey()+"-->value****"+entry.getValue().size());
      	      	redisService.putList(entry.getKey(), entry.getValue(), 0L, null);
      		}
      	}
    }


}
