package com.article.recommend.framework.springmvc;
/**
 * 
 * @ClassName: ReturnMsgModel  
 * @Description: 前后台交互model  
 * @author sw  
 * @date 2018年4月17日
 */
public class ReturnMsgModel {
    private boolean isSuccess;
    private String msg;
    private Object data;
    public boolean isSuccess() {
        return isSuccess;
    }
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public ReturnMsgModel() {
	super();
	// TODO Auto-generated constructor stub
    }
    public ReturnMsgModel(boolean isSuccess, String msg, Object data) {
	super();
	this.isSuccess = isSuccess;
	this.msg = msg;
	this.data = data;
    }
    
    
    
}
