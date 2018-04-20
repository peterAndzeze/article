package com.article.recommend.controller;

import com.article.recommend.constant.PageModel;
import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.entity.QuartzInfo;
import com.article.recommend.framework.springmvc.BaseController;
import com.article.recommend.service.quartzservice.QuartzService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("quartz")
public class QuartzController extends BaseController {
    @Autowired
    private QuartzService quartzService;
    @RequestMapping("getQuartzInfo")
    @ResponseBody
    public String getQuartzInfo(Model model, Long id){
    	try {
            QuartzInfo quartzInfo=quartzService.getQuartzInfoById(id);
            return jsonStrData(quartzInfo);
		} catch (Exception e) {
			e.printStackTrace();
		    return jsonStrAndState(null, false, "操作失败");
		}
    }
    /**
     * 暂停
     *
     */
    @ResponseBody
    @RequestMapping("paused")
    public String paused( QuartzInfo quartzInfo){
        quartzInfo=quartzService.getQuartzInfoById(quartzInfo.getId());
        if(quartzInfo.getState().equals(RecommendConstant.QUARTZ_STOPING)) {
		    return jsonStrAndState(null, false, "已经暂停");
    	}
        try {
            quartzService.paused(quartzInfo);
		    return jsonStrAndState(null, true, "操作成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
		    return jsonStrAndState(null, false, "操作失败");
        }
    }
    //恢复
    @ResponseBody
    @RequestMapping("resume")
    public String resume( QuartzInfo quartzInfo){
        quartzInfo=quartzService.getQuartzInfoById(quartzInfo.getId());
        if(quartzInfo.getState().equals(RecommendConstant.QUARTZ_RUNING)) {//失效
            return jsonStrAndState(null, false,"已经在运行！");
        }
        try {
            quartzService.resume(quartzInfo);
            return jsonStrAndState(null, true, "操作成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
            return jsonStrAndState(null, false, "操作失败");

        }
    }
    @RequestMapping("saveOrUpdateQuartz")
    @ResponseBody
    public String saveOrUpdateQuartz(Model model,QuartzInfo quartzInfo,RedirectAttributes redirectAttributes){
       try {
	       if(null!=quartzInfo.getId()) {
	    	   quartzService.updateQuartzInfo(quartzInfo);
	       }else {
	    	   quartzService.addQuartzInfo(quartzInfo);
	       }
	       return jsonStrAndState(null, true, "操作成功");
	   	}catch (Exception e) {
	   		e.printStackTrace();
		    return jsonStrAndState(null, false, "操作失败");
		}
    }
    @RequestMapping("deleteQuartz")
    @ResponseBody
    public String deleteQuartz(Model model,Long id,RedirectAttributes redirectAttributes){
        try {
            quartzService.deleteQuartzInfo(id);
 	       return jsonStrAndState(null, true, "操作成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
		    return jsonStrAndState(null, true, "操作失败");
        }
    }
    @RequestMapping("page")
    @ResponseBody
    public String page(PageModel pageModel,QuartzInfo quartzInfo) {
    	try {
			pageModel=quartzService.page(pageModel, quartzInfo);
			return jsonStrData(pageModel);
		} catch (Exception e) {
			e.printStackTrace();
			return jsonStrAndState(pageModel, true, "查询失败");
		}
    }
    
    @RequestMapping("main")
	public String getPath(HttpServletRequest request) {
		return "quartz/main";
	}

}
