package com.article.recommend.mapper.localMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.article.recommend.entity.MenuInfo;
/**
 * 
 * @ClassName: MenuMapper  
 * @Description: 菜单操作  
 * @author sw  
 * @date 2018年4月17日
 */
@Mapper
public interface MenuMapper {
   /**
    * 
    * @Title: queryMenus  
    * @Description: 查询
    * @param parentId
    * @return       
    * @return List<MenuInfo>    
    * @author sw
    * @throws
    */
    public List<MenuInfo> queryMenus(@Param("parentId") Long parentId);
    /**
     * 
     * @Title: queryMenuInfoById  
     * @Description: 单个菜单信息  
     * @param id
     * @return       
     * @return MenuInfo    
     * @author sw
     * @throws
     */
    public MenuInfo queryMenuInfoById(Long id);
    /**
     * 
     * @Title: getMenuInfos  
     * @Description: 得到所有  
     * @return       
     * @return List<MenuInfo>    
     * @author sw
     * @throws
     */
    public List<MenuInfo> getMenuInfos();

   /**
    * 
    * @Title: updateMenu  
    * @Description: 修改  
    * @param MenuInfo
    * @return       
    * @return boolean    
    * @author sw
    * @throws
    */
    public boolean updateMenu(MenuInfo MenuInfo); 
    /**
     * 新增
     */
    public boolean addMenu(MenuInfo MenuInfo);
    
    /**
     * 
     * @Title: deleteMenu  
     * @Description: 删除  
     * @param menuInfo
     * @return       
     * @return boolean    
     * @author sw
     * @throws
     */
    public boolean deleteMenu(MenuInfo menuInfo);
}
