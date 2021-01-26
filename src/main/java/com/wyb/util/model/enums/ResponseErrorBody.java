package com.wyb.util.model.enums;

/**
 * 创建时间：2021/1/26 11:47
 * 异常抛出错误码及描述类
 * @author wyb
 */
public class ResponseErrorBody {
	private int error;

	private String message;

	public ResponseErrorBody(int error, String message) {
		this.error = error;
		this.message = message;
	}

	public static ResponseErrorBody of(ReturnCode returnCode){
		return new ResponseErrorBody(returnCode.getCode(),returnCode.getMessage());
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
