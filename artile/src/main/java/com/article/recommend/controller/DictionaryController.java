package com.article.recommend.controller;

import com.article.recommend.constant.PageModel;
import com.article.recommend.entity.DictionaryInfo;
import com.article.recommend.framework.springmvc.BaseController;
import com.article.recommend.service.dictionary.DictionaryService;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DictionaryController extends BaseController {
    @Autowired
    private DictionaryService dictionaryService;
    @RequestMapping("dictionary/getDictionayById")
    @ResponseBody
    public String getDictionayById(Model model, Long id){
    	try {
    		DictionaryInfo dictionaryInfo=dictionaryService.getDictionayById(id);
    		return jsonStrData(dictionaryInfo);
    	}catch (Exception e) {
    		e.printStackTrace();
    		return jsonStrAndState("", false, "查询异常！！");
		}
    }

    /**
     *
     * @param dictionaryInfo
     * @return
     */
    @RequestMapping("dictionary/update")
    @ResponseBody
    public String updateDictionary(DictionaryInfo dictionaryInfo){
    	try {
    		dictionaryService.updateDictionary(dictionaryInfo);
    		return jsonStrAndState(null, true, "更新成功");
		} catch (Exception e) {
			e.printStackTrace();
    		return jsonStrAndState(null, false, "更新失败");
		}
    }
    @RequestMapping("dictionary/main")
	@Override
	public String getPath(HttpServletRequest request) {
		return "dictionary/main";
	}
    
    @RequestMapping("dictionary/page")
    @ResponseBody
    public String getPage(PageModel pageModel,DictionaryInfo dictionaryInfo) {
    	pageModel=dictionaryService.getPages(pageModel, dictionaryInfo);
    	return jsonStrData(pageModel);
    }
    @RequestMapping("dictionary/add")
    @ResponseBody
    public String addDictionary(DictionaryInfo dictionaryInfo) {
    	try {
        	dictionaryService.addDictionary(dictionaryInfo);
        	return jsonStrAndState(null, true, "新增成功");
		} catch (Exception e) {
    		e.printStackTrace();
			return jsonStrAndState(null, false, "新增失败"); 
		}
    }
    @RequestMapping("dictionary/delete")
    @ResponseBody
    public String deleteDictionary(Long id) {
    	if(null ==id) {
    		return jsonStrAndState(null, false, "参数不存在");
    	}
    	try {
    		dictionaryService.deleteDictionary(id);
        	return jsonStrAndState(null, true, "删除成功");
    	}catch (Exception e) {
    		e.printStackTrace();
			return jsonStrAndState(null, false, "删除失败"); 
		}
    }
    
}
