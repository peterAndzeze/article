package com.article.recommend.service.dictionary;

import com.article.recommend.constant.PageModel;
import com.article.recommend.entity.DictionaryInfo;
import com.article.recommend.mapper.localMapper.DictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;

    public List<DictionaryInfo> queryDictionarys(){
        return dictionaryMapper.queryDictionarys();
    }

    public DictionaryInfo getDictionayById(Long id){
        return dictionaryMapper.getDictionayById(id);
    }

    public  void updateDictionary(DictionaryInfo dictionaryInfo){
        dictionaryMapper.updateDictionary(dictionaryInfo);
    }

    /**
     * 根据key获取
     * @param key
     * @return
     */
    public DictionaryInfo getDictionaryByKey(String key){
        return dictionaryMapper.getDictionaryByKey(key);
    }
    /**
     * 
     * @Title: getPages  
     * @Description: 分页数据      
     * @author sw
     * @param pageModel
     * @param dictionaryInfo
     * @return
     */
    public PageModel getPages(PageModel pageModel,DictionaryInfo dictionaryInfo) {
    	int count=dictionaryMapper.pageCount(dictionaryInfo);
    	pageModel.setRowCount(count);
    	List<DictionaryInfo> dictionaryInfos=dictionaryMapper.page(dictionaryInfo, pageModel);
    	pageModel.setRecords(dictionaryInfos);
    	return pageModel;
    }
    /**
     * 
     * @Title: addDictionary  
     * @Description: 新增         
     * @author sw
     * @param dictionaryInfo
     */
    public void addDictionary(DictionaryInfo dictionaryInfo) {
    	dictionaryMapper.addDictionary(dictionaryInfo);
    }
    /**
     * 
     * @Title: deleteDictionary  
     * @Description: 删除         
     * @author sw
     * @param id
     */
    public void deleteDictionary(Long id) {
    	dictionaryMapper.deleteDictionary(id);
    }
    
}
