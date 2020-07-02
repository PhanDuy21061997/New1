package com.example.demo.util;

import java.security.MessageDigest;

import ch.qos.logback.classic.Logger;

public class CryptWithMD5 {

	private static MessageDigest md;
	private static final String KEY="MD5";
	public static String cryptWithMD5(String pass) {
		
		try {
			md=MessageDigest.getInstance(KEY);
			byte [] passByte=pass.getBytes();
			md.reset();
			byte []  digested=md.digest(passByte);
			StringBuffer buffer=new StringBuffer();
			for(int i=0;i<digested.length;i++){ 
				buffer.append(Integer.toHexString(0xff & digested[i])); 
			     } 
			return buffer.toString();
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.getErrorLogger().error("cryptWithMD5(String pass)",e);
		}
		return null;
	}
}
