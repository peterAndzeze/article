package com.article.recommend.service.quartzservice;

import com.article.recommend.constant.PageModel;
import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.entity.QuartzInfo;
import com.article.recommend.mapper.localMapper.QuartzMapper;
import com.article.recommend.quartz.QuartzManager;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 定时任务
 */
@Service
public class QuartzService {
    @Autowired
    private QuartzMapper quartzMapper;
    @Autowired
    private QuartzManager quartzManager;
    public List<QuartzInfo> getQuartzInfos(){
        return quartzMapper.getQuartzInfos();
    }

    public QuartzInfo getQuartzInfoById(Long id){
        return quartzMapper.getQuartzInfoById(id);
    }

    /**
     * 更新
     * @param quartzInfo
     */
    @Transactional(value = "localDataTransactionManager",propagation = Propagation.REQUIRED)
    public void updateQuartzInfo(QuartzInfo quartzInfo) throws SchedulerException {
    	QuartzInfo dbQuartzInfo=getQuartzInfoById(quartzInfo.getId());
    	//比对数据库状态
        quartzMapper.updateQuartz(quartzInfo);
        //相等
        if(!quartzInfo.getCron().equals(dbQuartzInfo.getCron())){//运行中的修改
        	quartzManager.reschedule(quartzInfo.getClassName(),dbQuartzInfo.getGroup(),quartzInfo.getCron());
        }
    }

    /**
     * 暂停
     * @param quartzInfo
     */
    @Transactional(value = "localDataTransactionManager",propagation = Propagation.REQUIRED)
    public  void  paused(QuartzInfo quartzInfo) throws SchedulerException {
    	quartzInfo.setState(RecommendConstant.QUARTZ_STOPING);
        quartzMapper.updateQuartz(quartzInfo);
        quartzManager.paused(quartzInfo.getClassName(),quartzInfo.getGroup());
    }


    /**
     * 恢复
     * @param quartzInfo
     */
    @Transactional(value = "localDataTransactionManager",propagation = Propagation.REQUIRED)
    public  void  resume(QuartzInfo quartzInfo) throws SchedulerException {
    	quartzInfo.setState(RecommendConstant.QUARTZ_RUNING);
        quartzMapper.updateQuartz(quartzInfo);
        quartzManager.resume(quartzInfo.getClassName(),quartzInfo.getGroup(),quartzInfo.getCron());
    }

    /**
     * 新增
     * @param quartzInfo
     * @throws Exception 
     */
    @Transactional(value = "localDataTransactionManager",propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public void addQuartzInfo(QuartzInfo quartzInfo) throws Exception{
        quartzMapper.inserQuartz(quartzInfo);
        if(quartzInfo.getState().equals(RecommendConstant.QUARTZ_RUNING)) {
        	quartzManager.frontJob(quartzInfo.getClassName(),quartzInfo.getGroup(),quartzInfo.getCron());
        }
    }

    /**
     * 删除
     * @param id
     */
    @Transactional(value = "localDataTransactionManager",propagation = Propagation.REQUIRED)
    public void deleteQuartzInfo(Long id) throws SchedulerException {
        QuartzInfo quartzInfo=getQuartzInfoById(id);
        quartzMapper.deleteQuartz(id);
        quartzManager.delete(quartzInfo.getClassName(),quartzInfo.getGroup());
    }
    /**
     * 
     * @Title: page  
     * @Description: 分页         
     * @author sw
     * @param pageModel
     * @param quartzInfo
     * @return
     */
    public PageModel page(PageModel pageModel,QuartzInfo quartzInfo) {
    	int count=quartzMapper.pageCount(quartzInfo);
    	pageModel.setRowCount(count);
    	List<QuartzInfo> quartzInfos=quartzMapper.page(pageModel, quartzInfo);
    	pageModel.setRecords(quartzInfos);
    	return pageModel;
    }
    
    
    
}
