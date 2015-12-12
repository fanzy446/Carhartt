package com.carhartt.man.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CarharttConfig {
	public static String URL = "http://222.201.132.35:8080/Carhartt/";
	public static String USERID = "userId";
	public static String USERNAME = "userName";	
	public static String SUPER_MANAGER = "superMan";
	public static String COM_MANAGER = "comMan";
	public static String ORDER_MANAGER = "orderMan";
	public static String SYN_MANAGER = "synMan";
	public static String USER_MANAGER = "userMan";
	
	public static String Md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plainText;
	}
	public static void main(String[] args){
		System.out.println(CarharttConfig.Md5("admin"));
	}
}

