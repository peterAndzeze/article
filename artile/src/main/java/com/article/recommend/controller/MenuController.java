package com.article.recommend.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.article.recommend.constant.PageModel;
import com.article.recommend.entity.MenuInfo;
import com.article.recommend.framework.springmvc.BaseController;
import com.article.recommend.service.menu.MenuServiceImpl;
import com.article.recommend.util.CoreUtil;

/**
 * 
 * @ClassName: MenuController
 * @Description: 界面菜单操作
 * @author sw
 * @date 2018年4月17日
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
	@Autowired
	private MenuServiceImpl menuService;
	@RequestMapping(value = "/getMenusTree", method = RequestMethod.POST)
	@ResponseBody
	public String getMenusTree(HttpServletRequest request, HttpServletResponse response, Long id) {
		String menuModels = menuService.nenuTree(id);
		return menuModels;
	}

	@RequestMapping("/main")
	@Override
	public String getPath(HttpServletRequest request) {
		request.getServletContext().setAttribute("uuid", CoreUtil.genUUIDString());
		request.getServletContext().setAttribute("timeStr", System.currentTimeMillis());
		return "page/menu/main";
	}

	@RequestMapping("/getPages")
	@ResponseBody
	public String getMenuPage(HttpServletRequest request, HttpServletResponse response, PageModel pageModel,
			MenuInfo param) {
		MenuInfo menuModel = menuService.queryMenuInfoById(param.getId());
		List<MenuInfo> menuModels = new ArrayList<MenuInfo>();
		menuModels.add(menuModel);
		PageModel model = createPage(1, menuModels, 0, 0);
		return jsonStrData(model);
	}

	@RequestMapping("/menuInfo")
	@ResponseBody
	public String getMenuInfo(Long id) {
		MenuInfo menuModel = menuService.queryMenuInfoById(id);
		return jsonStrData(menuModel);
	}

	/**
	 * 
	 * @Title: tree  
	 * @Description: 返回菜单树         
	 * @author sw
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/menuTree")
	@ResponseBody
	public String tree(HttpServletRequest request, HttpServletResponse response, Long id) {
		if (null == id) {// 一级菜单
			id = 0L;
		}
		return menuService.nenuTree(id);
	}

	/**
	 * 
	 * @Title: updateMenu  
	 * @Description: 修改         
	 * @author sw
	 * @param request
	 * @param response
	 * @param menuModel
	 * @return
	 */
	@RequestMapping("/updateMenu")
	@ResponseBody
	public String updateMenu(HttpServletRequest request, HttpServletResponse response, MenuInfo menuModel) {
		try {
			if (null == menuModel.getId()) {// 新增
				menuService.addMenu(menuModel);
			} else {// 修改
				menuService.updateMenu(menuModel);
			}
			return jsonStrAndState("", true, "操作成功");
		} catch (Exception e) {
			return jsonStrAndState("", false, e.getMessage());
		}
	}

	@RequestMapping("/deleteMenu")
	@ResponseBody
	public String deleteMenu(HttpServletRequest httpServletRequest, HttpServletResponse response, MenuInfo menuModel) {
		try {
			menuService.deleteMenu(menuModel);
			return jsonStrAndState("", true, "操作成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return jsonStrAndState("", false, e.getMessage());
		}

	}
}
