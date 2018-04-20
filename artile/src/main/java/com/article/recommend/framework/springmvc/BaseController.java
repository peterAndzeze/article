package com.article.recommend.framework.springmvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.recommend.constant.PageModel;
import com.article.recommend.util.JsonUtil;

/**
 * 
 * @ClassName: BaseController
 * @Description: controller基类
 * @author sw
 * @date 2018年4月17日
 */
public abstract class BaseController {
	/**
	 * 
	 * @Title: redirct  
	 * @Description: 重定向         
	 * @author sw
	 * @param url
	 * @return
	 */
	protected String redirct(String url) {
		return "redirct:" + url;
	}

	/**
	 * 
	 * @Title: forward  
	 * @Description: 服务跳转         
	 * @author sw
	 * @param url
	 * @return
	 */
	protected String forward(String url) {
		return url;
	}

	/**
	 * 
	 * @Title: resopnseMsg  
	 * @Description: 响应信息         
	 * @author sw
	 * @param response
	 * @param isSuccess
	 * @param msg
	 */
	protected void resopnseMsg(HttpServletResponse response, boolean isSuccess, String msg) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("success", isSuccess);
		responseMap.put("msg", msg);
		try {
			response.getWriter().print(JsonUtil.objectToJson(responseMap));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void forward(HttpServletResponse response, String url, HttpServletRequest request) {
		request.getRequestDispatcher(url);
	}

	/**
	 * 
	 * @Title: jsonStrAndState  
	 * @Description: 返回页面和信息         
	 * @author sw
	 * @param data
	 * @param isSuccess
	 * @param msg
	 * @return
	 */
	protected String jsonStrAndState(Object data, boolean isSuccess, String msg) {
		ReturnMsgModel returnMsgModel = new ReturnMsgModel(isSuccess, msg, data);
		String str = JsonUtil.objectToJsonExcludeProperty(returnMsgModel);
		return str;
	}

	/**
	 * 
	 * @Title: jsonStrData  
	 * @Description: 数据         
	 * @author sw
	 * @param data
	 * @return
	 */
	protected String jsonStrData(Object data) {
		String str = JsonUtil.objectToJsonExcludeProperty(data);
		return str;
	}
	/**
	 * 
	 * @Title: jsonStrListData  
	 * @Description：数据转json         
	 * @author sw
	 * @param data
	 * @return
	 */
	protected String jsonStrListData(Object data) {
		String str = JsonUtil.objectToJson(data);
		return str;
	}

	/**
	 * 
	 * @Title: getPath
	 * @Description:
	 * @author:sunwei
	 * @createTime:2017年5月27日上午9:19:24
	 * @return
	 */
	public abstract String getPath(HttpServletRequest request);

	/**
	 * 
	 * @Title: createPage
	 * @Description:
	 * @author:sunwei
	 * @createTime:2017年6月1日上午11:09:43
	 * @param total
	 * @param data
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	protected PageModel createPage(int total, List<?> data, int currentPage, int pageSize) {
		PageModel pageModel = new PageModel();
		pageModel.setPageSize(0);
		pageModel.setRowCount(total);
		pageModel.setRecords(data);
		return pageModel;
	}
}
