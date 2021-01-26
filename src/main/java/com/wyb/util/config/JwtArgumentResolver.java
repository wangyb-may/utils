package com.wyb.util.config;

import com.wyb.util.model.dto.JwtBody;
import com.wyb.util.service.TokenUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 创建时间：2021/1/25 17:24
 * 实现拦截并注入参数
 * @author wyb
 */
public class JwtArgumentResolver implements HandlerMethodArgumentResolver {

	private TokenUtils tokenUtils;

	private String tokenHeader;

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterType().equals(JwtBody.class);
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
		//将解析后的jwtBody作为全局参数，所有接口均可调用
		return tokenUtils.signatureToken(nativeWebRequest.getHeader(tokenHeader));
	}

	/**
	 * 构造方法，因为使用注解注入的形式不生效
	 * @param tokenUtils token工具bean
	 * @param tokenHeader 请求头名字
	 */
	JwtArgumentResolver(TokenUtils tokenUtils, String tokenHeader){
		this.tokenHeader = tokenHeader;
		this.tokenUtils = tokenUtils;
	}
}
