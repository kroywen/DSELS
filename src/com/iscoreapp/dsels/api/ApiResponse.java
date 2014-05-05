package com.iscoreapp.dsels.api;

import java.io.Serializable;

public class ApiResponse implements Serializable {
	
	private static final long serialVersionUID = 2298834586169426687L;
	
	private String requestName;
	private Object data;
	
	public ApiResponse() {}
	
	public String getRequestName() {
		return requestName;
	}
	
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
}
