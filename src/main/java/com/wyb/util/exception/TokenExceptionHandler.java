package com.wyb.util.exception;

import com.wyb.util.model.enums.ResponseErrorBody;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 创建时间：2021/1/26 11:44
 * token自定义异常抛出捕获handler
 * @author wyb
 */
@ResponseBody
@ControllerAdvice
@Order(1201)
public class TokenExceptionHandler {

	public TokenExceptionHandler(){
	}

	@ExceptionHandler({TokenException.class})
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	public ResponseErrorBody exceptionHandler(TokenException ex){
		return ResponseErrorBody.of(ex.getReturnCode());
	}
}
