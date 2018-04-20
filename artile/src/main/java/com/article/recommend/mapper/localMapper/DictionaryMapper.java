package com.article.recommend.mapper.localMapper;

import com.article.recommend.constant.PageModel;
import com.article.recommend.entity.DictionaryInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictionaryMapper {
    /**
     * 根据key获取字典值
     * @param key
     * @return
     */
    public DictionaryInfo getDictionaryByKey(String key);

    /**
     * 查询所有的
     * @return
     */
    public List<DictionaryInfo> queryDictionarys();

    /**
     * 根据id
     * @param id
     * @return
     */
    public DictionaryInfo getDictionayById(Long id);

    /**
     * 更新
     * @param dictionaryInfo
     */
    public void updateDictionary(DictionaryInfo dictionaryInfo);
    /**
     * 
     * @Title: page  
     * @Description: 获取分页数据         
     * @author sw
     * @param dictionaryInfo
     * @param pageModel
     * @return
     */
    public List<DictionaryInfo> page(@Param("dictionaryInfo")DictionaryInfo dictionaryInfo,@Param("pageModel")PageModel pageModel);
    /**
     * 
     * @Title: pageCount  
     * @Description:分页总数         
     * @author sw
     * @param dictionaryInfo
     * @return
     */
    public int pageCount(DictionaryInfo dictionaryInfo);
    /**
     * 
     * @Title: addDictionary  
     * @Description: 新增         
     * @author sw
     * @param dictionaryInfo
     * @return
     */
    public int addDictionary(DictionaryInfo dictionaryInfo);
    
    /**
     * 
     * @Title: deleteDictionary  
     * @Description: 删除         
     * @author sw
     * @param id
     */
    public void deleteDictionary(Long id);
    
}

