package com.article.recommend.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.UUID;

public class CoreUtil {
	
	public static final int LINUX = 1;
	
	public static final int WINDOWS = 2;
	/**
	 * 无参构造方法
	 */
	private CoreUtil(){}
	/**
	 * 
	 * @Title: generateSortIdx  
	 * @Description: 排序号         
	 * @author sw
	 * @return
	 */
	public static Long generateSortIdx(){
		return System.currentTimeMillis();
	}
	/**
	 * 
	 * @Title: generateTimestamp  
	 * @Description: 当前时间戳         
	 * @author sw
	 * @return
	 */
	public static Timestamp generateTimestamp(){
		return new java.sql.Timestamp(System.currentTimeMillis());
	}
	/**
	 * 
	 * @Title: genUUIDString  
	 * @Description: 生成字符串         
	 * @author sw
	 * @return
	 */
	public static String genUUIDString(){
		return UUID.randomUUID().toString();
	}
	/**
	 * 
	 * @Title: getServerOSType  
	 * @Description: 系统类型         
	 * @author sw
	 * @return
	 */
	public static int getServerOSType(){
		int resultCode = 0;
		String osTag = System.getProperty("os.name");
		if(osTag.indexOf("Linux")!=-1||osTag.indexOf("Mac")!=-1){
			resultCode = LINUX;
		}else{
			resultCode = WINDOWS;
		}
		return resultCode ; 
	}
	
	/**
	 * 
	 * @Title: getServerUser  
	 * @Description: 当前系统名         
	 * @author sw
	 * @return
	 */
	public static String getServerUser(){
		return System.getProperty("user.name");
	}
	
	/**
	 * 
	 * @Title: getLocalIP  
	 * @Description: 系统ip         
	 * @author sw
	 * @return
	 */
	public static String getLocalIP(){  
		InetAddress addr = null;  
        try {  
            addr = InetAddress.getLocalHost();  
        } catch (UnknownHostException e) {  
            e.printStackTrace();  
                        return null;  
        }  
        byte[] ipAddr = addr.getAddress();  
        String ipAddrStr = "";  
        for (int i = 0; i < ipAddr.length; i++) {  
            if (i > 0) {  
                ipAddrStr += ".";  
            }  
            ipAddrStr += ipAddr[i] & 0xFF;  
        }  
        return ipAddrStr;  
	}
}