package com.article.recommend.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.entity.MenuInfo;

public class MenuUtil {
	private static List<MenuInfo> menus;

	public static String createMenus(List<MenuInfo> menuModels, Long parentId) {// 一级自定义节点0
		menus = new ArrayList<MenuInfo>();
		Iterator<MenuInfo> iterator = menuModels.iterator();
		MenuInfo menuModel = null;
		if (null == parentId) {
			parentId =RecommendConstant.PARENT_ID;
		}
		while (iterator.hasNext()) {
			menuModel = iterator.next();
			if (menuModel.getParentId().equals(parentId)) {// 二级节点
				menus.add(menuModel);// 放入
				iterator.remove();// 删除
				isHaveChilds(menuModels, menuModel);
			} else if (menuModel.getLeaf().equals(RecommendConstant.IS_NOT_LEAF)) {// 不是最后节点的
				isHaveChilds(menuModels, menuModel);
			}
		}
		return JSONArray.toJSONString(menus);
	}

	/**
	 * 
	 * @Title: isHaveChilds  
	 * @Description: 所有子节点         
	 * @author sw
	 * @param menuModels
	 * @param menuModel
	 */
	public static void isHaveChilds(List<MenuInfo> menuModels, MenuInfo menuModel) {
		Iterator<MenuInfo> iterator = menuModels.iterator();
		while (iterator.hasNext()) {
			MenuInfo childMenuModel = iterator.next();
			if (childMenuModel.getParentId().equals(menuModel.getId())) {
				List<MenuInfo> childModels = null;
				if (null == menuModel.getChildMenuModels()) {
					childModels = new ArrayList<MenuInfo>();
					childModels.add(childMenuModel);
					menuModel.setChildMenuModels(childModels);
				} else {
					menuModel.getChildMenuModels().add(childMenuModel);
				}
			}
		}
	}

	public static void main(String[] args) {
		MenuInfo menuModel = new MenuInfo();
		MenuInfo menuModel1 = new MenuInfo();
		menuModel1.setId(12121L);
		List<MenuInfo> list = new ArrayList<MenuInfo>();
		list.add(menuModel);
		list.add(menuModel1);
		System.out.println(list.size());
		Iterator<MenuInfo> iterators = list.iterator();
		while (iterators.hasNext()) {
			if (null != iterators.next().getId()) {
				iterators.remove();
			}
		}
		System.out.println(list.size() + "," + list.get(0).getId());
	}

}
