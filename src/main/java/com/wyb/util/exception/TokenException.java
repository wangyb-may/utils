package com.wyb.util.exception;

import com.wyb.util.model.enums.ReturnCode;

/**
 * 创建时间：2021/1/25 10:58
 * token异常抛出
 * @author wyb
 */
public class TokenException extends RuntimeException{

	private final ReturnCode returnCode;

	public TokenException(ReturnCode returnCode) {
		super(returnCode.getMessage());
		this.returnCode = returnCode;
	}

	ReturnCode getReturnCode() {
		return this.returnCode;
	}
}
