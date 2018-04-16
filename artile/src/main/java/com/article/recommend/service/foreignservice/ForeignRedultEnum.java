package com.article.recommend.service.foreignservice;

public enum ForeignRedultEnum {
	
	 /*******************接口交互code********************************************/
    /**成功 0 ，失败1,没有数据 2**/
	FOREIGN_SUCCESS_CODE("成功","0"),
	FOREIGN_FAILURE_CODE("失败","1"),
	FOREIGN_NODATA_CODE("没有数据","2");
	private String msg;
	private String code;
	private ForeignRedultEnum(String msg, String code) {
		this.msg = msg;
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public static String getMsg(int code) {
		for(ForeignRedultEnum foreignRedultEnum:ForeignRedultEnum.values()) {
			if(foreignRedultEnum.code.equals(code)) {
				return foreignRedultEnum.msg;
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	

}
