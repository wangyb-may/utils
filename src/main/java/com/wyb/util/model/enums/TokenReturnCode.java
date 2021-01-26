package com.wyb.util.model.enums;

import jdk.nashorn.internal.parser.Token;

/**
 * 创建时间：2021/1/25 10:34
 * token相关返回码
 * @author wyb
 */
public enum  TokenReturnCode implements ReturnCode{

	/**
	 * token解析相关
	 * 成功
	 */
	SUCCESS(30001,"token resolved successfully"),
	/**
	 * 时间过期
	 */
	TIME_OUT(30002,"token has expired"),
	/**
	 * 解析失败
	 */
	RESOLVE_FAILED(30003,"token resolved failed or has expired");

	private final Integer code;

	private final String message;

	TokenReturnCode(int code, String message) {
		this.code = code;
		this.message = message;
	}


	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
