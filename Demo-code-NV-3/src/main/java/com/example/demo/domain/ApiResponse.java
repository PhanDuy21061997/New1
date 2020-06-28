package com.example.demo.domain;

public class ApiResponse {

	public static final int CODE_SUCESS = 1;
    public static final int CODE_ERROR = 0;
    
    public static final String MSG_SUCCESS = "Cập nhật thành công";
    public static final String MSG_ERROR = "Có lỗi xảy ra!";
    
   private int code = CODE_SUCESS;
    private String message = MSG_SUCCESS;
  
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiResponse() {
		
		
	}

	public ApiResponse(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	
    
    
}

