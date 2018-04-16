package com.article.recommend.controller;

import com.article.recommend.entity.DictionaryInfo;
import com.article.recommend.service.dictionary.DictionaryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;
    @RequestMapping("getDictionayById")
    public String getDictionayById(Model model, Long id){
        DictionaryInfo dictionaryInfo=dictionaryService.getDictionayById(id);
        model.addAttribute("dictionary",dictionaryInfo);
        return "detailDictionary";
    }

    /**
     *
     * @param dictionaryInfo
     * @return
     */
    @RequestMapping("updateDictionary")
    public String updateDictionary(RedirectAttributes redirectAttributes, DictionaryInfo dictionaryInfo){
        dictionaryService.updateDictionary(dictionaryInfo);
        redirectAttributes.addAttribute("password","admin");
        redirectAttributes.addAttribute("userName","admin");
        return "redirect:/login";
    }
    @RequestMapping("dictionary/main")
    public String main() {
    	return "dictionary/main";
    }
    
}
