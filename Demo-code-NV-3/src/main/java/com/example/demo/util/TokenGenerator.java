package com.example.demo.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;


public class TokenGenerator {

	public static final String KEY_VALUE="abcd123";
	private static Logger log = Logger.getLogger(TokenGenerator.class);
	
	public static String md5String(String sourceString) throws NoSuchAlgorithmException {
		
		  MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(sourceString.getBytes());
	        byte[] encryptDatas = md.digest();
	        String returnData = new BigInteger(1, encryptDatas).toString(16);
	        while (returnData.length() < 32) {
	            returnData = "0" + returnData;
	        }
	        return returnData;
		
	}
	 public static String generateToken(String... valueRequests) {
	        StringBuilder valueRequest = new StringBuilder();
	        for (String value : valueRequests) {
	            if (value != null) {
	                valueRequest.append(value);
	            }
	        }

	        try {
	            return md5String(valueRequest.toString() + KEY_VALUE);
	        } catch (NoSuchAlgorithmException ex) {
	            log.error("", ex);
	        }
	        return null;
	    }
	    
	    public static String generateToken(List<String> valueRequests) {
	        StringBuilder valueRequest = new StringBuilder();
	        for (String value : valueRequests) {
	            if (value != null) {
	                valueRequest.append(value);
	            }
	        }

	        try {
	            return md5String(valueRequest.toString() + KEY_VALUE);
	        } catch (NoSuchAlgorithmException ex) {
	            log.error("", ex);
	        }
	        return null;
	    }
	    
	    public String generateToken() {
	        try {
	            return md5String(KEY_VALUE);
	        } catch (NoSuchAlgorithmException ex) {
	            log.error("", ex);
	        }
	        return null;
	    }
}
			