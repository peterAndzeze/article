package com.article.recommend.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5加密工具类
 */
public class MD5Util {

	/**
	 * md5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] byteDigest = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < byteDigest.length; offset++) {
				i = byteDigest[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
			// 16位的加密
			// return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @Title: compare  
	 * @Description: 签名         
	 * @author sw
	 * @param vaStr
	 * @param mdStr
	 * @return
	 */
	public boolean compare(String vaStr,String mdStr) {
		byte [] data=vaStr.getBytes();	
		try {
			String md5Result=DigestUtils.md5Hex(data);
			if(null !=md5Result && !"".equals(md5Result) && md5Result.equals(mdStr)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
