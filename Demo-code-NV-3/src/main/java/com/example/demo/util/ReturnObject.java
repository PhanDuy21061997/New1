package com.example.demo.util;

public class ReturnObject {

	  private static final int SUCCESS = 0;
	    public static final int ERROR = 1;
	    public static final String DESC_SUCCESS = "SUCCESS";
	    public static final String DESC_ERROR = "ERROR";
	    public static final String DESC_INVALID_TOKEN = "INVALID_TOKEN";
	    public static final String DESC_DUPLICATE_ENTRY = "DUPLICATE_ENTRY";
	    public static final String DESC_NOT_FOUND = "NOT_FOUND";
	    private int code = SUCCESS;
	    private String desc = DESC_SUCCESS;
	    private Object data;
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		public ReturnObject(int code, String desc, Object data) {
			super();
			this.code = code;
			this.desc = desc;
			this.data = data;
		}
		public ReturnObject() {
			
		}
	    
	    
}
