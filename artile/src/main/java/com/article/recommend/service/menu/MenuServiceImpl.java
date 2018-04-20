package com.article.recommend.service.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONArray;
import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.entity.MenuInfo;
import com.article.recommend.mapper.localMapper.MenuMapper;
import com.article.recommend.util.MenuUtil;

/**
 * 
 * @ClassName: MenuServiceImpl
 * @Description: 菜单服务
 * @author sw
 * @date 2018年4月17日
 */
@Service
public class MenuServiceImpl {
	@Autowired
	private MenuMapper menuMapper;

	/**
	 * 
	 * @Title: queryMenus @Description: 获取菜单集合 @param parentId @return
	 * String @author sw @throws
	 */
	public String queryMenus(Long parentId) {
		List<MenuInfo> menuModels = menuMapper.queryMenus(parentId);
		String menuStr = MenuUtil.createMenus(menuModels, parentId);
		return menuStr;
	}

	/**
	 * 
	 * @Title: queryMenuInfoById
	 * @Description:获取菜单明细
	 * @author sw
	 * @param id
	 * @return
	 */
	public MenuInfo queryMenuInfoById(Long id) {
		return menuMapper.queryMenuInfoById(id);
	}

	/**
	 * 
	 * @Title: nenuTree  
	 * @Description: 得到所有         
	 * @author sw
	 * @param id
	 * @return
	 */
	public String nenuTree(Long id) {
		List<MenuInfo> menuModels = menuMapper.queryMenus(id);
		List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		for (MenuInfo menuModel : menuModels) {
			Map<String, Object> node = new HashMap<String, Object>();
			node.put("id", menuModel.getId());
			node.put("text", menuModel.getMenuName());
			node.put("parentId", menuModel.getParentId());
			node.put("path", menuModel.getPath());
			if (menuModel.getLeaf().equals(RecommendConstant.IS_LEAF)) {
				node.put("leaf", true);
			} else {
				node.put("leaf", false);

			}
			node.put("path", menuModel.getPath());
			tree.add(node);
		}
		return JSONArray.toJSONString(tree);
	}

	/**
	 * 
	 * @Title: updateMenu  
	 * @Description: 修改         
	 * @author sw
	 * @param menuModel
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void updateMenu(MenuInfo menuModel) throws Exception {
		try {
			menuMapper.updateMenu(menuModel);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("【" + menuModel.getId() + "】修改失败！！！");
		}
	}

	/**
	 * 
	 * @Title: addMenu  
	 * @Description: 修改         
	 * @author sw
	 * @param menuModel
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void addMenu(MenuInfo menuModel) throws Exception {
		try {
			menuModel.setLeaf(RecommendConstant.IS_LEAF);
			menuMapper.addMenu(menuModel);
			MenuInfo model = new MenuInfo();
			model.setId(menuModel.getParentId());
			model.setLeaf(RecommendConstant.IS_NOT_LEAF);//
			menuMapper.updateMenu(model);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("添加失败！！！");
		}
	}

	/**
	 * 
	 * @Title: deleteMenu  
	 * @Description: 删除         
	 * @author sw
	 * @param menuModel
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteMenu(MenuInfo menuModel) throws Exception {
		try {
			menuMapper.deleteMenu(menuModel);
			List<MenuInfo> menuModels = menuMapper.queryMenus(menuModel.getParentId());
			if (menuModels.size() == 0) {
				MenuInfo model = new MenuInfo();
				model.setId(menuModel.getParentId());
				model.setLeaf(RecommendConstant.IS_LEAF);
				menuMapper.updateMenu(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("【" + menuModel.getId() + "】删除失败");
		}
	}

}
