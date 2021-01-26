package com.wyb.util.aspect;

import com.wyb.util.service.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

/**
 * 创建时间：2021/1/21 14:21
 * token验证注解实现
 * @author wyb
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class JwtAspect {

	@Resource
	private TokenUtils tokenUtils;

	/**
	 * 需要忽略的地址，请在配置文件中配置
	 * 多个地址请用 ","分割
	 * 例如："/test,/example,/jwt
	 */
	@Value("#{'${token.ignore:}'.split(',')}")
	private List<String> ignoreUrl;

	/**
	 * token所在请求头，可以在配置中设置，也可以使用默认"token"
	 */
	@Value("${token.header:token}")
	private String tokenHeader;

	/**
	 * token验证主入口
	 * @throws Throwable 异常抛出
	 */
	@Before(value = " @within(com.wyb.util.annotation.TokenVerification) || @annotation(com.wyb.util.annotation.TokenVerification)")
	public void verifyTokenForClass() throws Throwable{
		checkToken();
	}

	private void checkToken() throws NoSuchAlgorithmException {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if(Objects.isNull(requestAttributes)){
			return;
		}
		//获取请求方法
		HttpServletRequest request = requestAttributes.getRequest();
		//请求地址中是否存在配置了得忽略地址，如果存在测判断其是否访问了该地址
		if(ignoreUrl.stream().anyMatch(item -> request.getRequestURL().toString().contains(item))){
			return;
		}

		//请求地址
		log.info("request url : {}",request.getRequestURL());
		//请求方法GET、POST、PUT、DELETE....or more
		log.info("request method : {}",request.getMethod());

		//进入token解析流程
		tokenUtils.signatureToken(request.getHeader(tokenHeader));
	}
}
