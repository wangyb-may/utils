package com.wyb.util.config;

import com.wyb.util.service.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * 创建时间：2021/1/26 10:55
 * 创建拦截器
 * @author wyb
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Autowired
	private TokenUtils tokenUtils;

	@Value("${token.header:token}")
	private String tokenHeader;

	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new JwtArgumentResolver(tokenUtils,tokenHeader));
	}
}
